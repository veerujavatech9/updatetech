package com.gorica.controller.rest;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gorica.dao.model.Employee;
import com.gorica.dao.model.IdleTimeReason;
import com.gorica.dao.model.ScannedTimeLog;
import com.gorica.dao.model.User;
import com.gorica.service.EmployeeDetailsService;
import com.gorica.service.TimeLogDetailsService;

@RestController
@RequestMapping("/rest/timeLog")
@SuppressWarnings({ "rawtypes", "unchecked" })
public class TimeLogRestController {

	private static final Logger logger = LoggerFactory.getLogger(TimeLogRestController.class);
	
	@Autowired
	private TimeLogDetailsService timeLogDetailsService;

	@Autowired
	private SimpleDateFormat simpleDateFormat;

	@Autowired 
	private EmployeeDetailsService employeeService;

	@PostMapping("/logActivity")
	public ResponseEntity<?> trackTime(@RequestBody ScannedTimeLog timeLog) {

		ResponseEntity<?> response = null;
		JSONObject resp = new JSONObject();
		System.out.println("timeLog:" + timeLog);
		try {
			Employee emp = employeeService.getEmployeeByEmpNo(timeLog.getEmployee().getEmpNo());
			timeLog.setEmployee(emp);
			ScannedTimeLog empTodaysLog = timeLogDetailsService
					.getTimeLogDetailsByEmpPostDate(timeLog.getEmployee().getId(), simpleDateFormat.format(new Date()));

			/*
			 * ScannedTimeLog existedLog =
			 * timeLogDetailsService.getTimeLogDetailsByJobActivityEmpPostDate(
			 * timeLog.getJob().getId(), timeLog.getActivity().getId(),
			 * timeLog.getEmployee().getId(), simpleDateFormat.format(new Date()));
			 */
			/*
			 * ScannedTimeLog existedLog =
			 * timeLogDetailsService.getTimeLogDetailsByJobEmpPostDate(
			 * timeLog.getJob().getId(), timeLog.getEmployee().getId(),
			 * simpleDateFormat.format(new Date()));
			 */
             
			ScannedTimeLog existedLog = timeLogDetailsService.getTimeLogDetailsByJobWorkStationEmpPostDate(
					timeLog.getJob().getId(), timeLog.getActivity().getId(), timeLog.getEmployee().getId(),
					simpleDateFormat.format(new Date()));
			
			ScannedTimeLog existedLogWithTwoDaysVariation = timeLogDetailsService.getTimeLogDetailsByJobWorkStationEmpPostDateForNightShift(
					timeLog.getJob().getId(), timeLog.getActivity().getId(), timeLog.getEmployee().getId(),
					simpleDateFormat.format(new Date()));

			/************** New Time Log **************/
			if (empTodaysLog == null && existedLog == null) {
				timeLog.setPostingDate(new Date());
				timeLog.setCreatedDate(new Date());

				if (timeLog.getCheckInTime() != null) {
					timeLog.setCheckInTime(timeLog.getCheckInTime());
					timeLogDetailsService.saveTimeLog(timeLog);

					resp.put("statusMessage", "Success");
					resp.put("statusCode", 1);
				} else if (timeLog.getCheckOutTime() != null || timeLog.getIdealInTime() != null
						|| timeLog.getIdealOutTime() != null) {
                    //new code
					if(existedLogWithTwoDaysVariation!=null) {
						//timeLog.setCheckOutTime(timeLog.getCheckOutTime());
						existedLogWithTwoDaysVariation.setCheckOutTime(timeLog.getCheckOutTime());
						timeLogDetailsService.updateTimeLog(existedLogWithTwoDaysVariation);
						
						resp.put("statusMessage", "Success");
						resp.put("statusCode", 1);
					}else {
						resp.put("statusMessage", "Check In not done");
						resp.put("statusCode", 0);
					}
				}
				/*
				 * else if (timeLog.getCheckOutTime() != null)
				 * timeLog.setCheckOutTime(timeLog.getCheckOutTime()); else if
				 * (timeLog.getIdealInTime() != null)
				 * timeLog.setIdealInTime(timeLog.getIdealInTime()); else if
				 * (timeLog.getIdealOutTime() != null)
				 * timeLog.setIdealOutTime(timeLog.getIdealOutTime());
				 */

				/************** Existing Log update **************/
			} else if (existedLog != null) {
				// existedLog.setPostingDate(new Date());
				existedLog.setCreatedDate(new Date());
				existedLog.setActivity(timeLog.getActivity());
				existedLog.setUser(timeLog.getUser());
				existedLog.setScanned(timeLog.isScanned());

				/*
				 * if (timeLog.getCheckInTime() != null)
				 * existedLog.setCheckInTime(timeLog.getCheckInTime());
				 */
				if (timeLog.getCheckOutTime() != null)
					existedLog.setCheckOutTime(timeLog.getCheckOutTime());
				else if (timeLog.getIdealInTime() != null) {
					existedLog.setIdealInTime(timeLog.getIdealInTime());
					existedLog.setIdealReason(timeLog.getIdealReason());
				} else if (timeLog.getIdealOutTime() != null && existedLog.getIdealInTime() != null) {
					existedLog.setIdealOutTime(timeLog.getIdealOutTime());
				} else if (timeLog.getIdealOutTime() != null && existedLog.getIdealInTime() == null) {
					resp.put("statusMessage", "Ideal In time is not enetered");
					resp.put("statusCode", 0);
					response = new ResponseEntity<String>(resp.toString(), HttpStatus.OK);
					return response;
				}

				timeLogDetailsService.updateTimeLog(existedLog);

				resp.put("statusMessage", "Success");
				resp.put("statusCode", 1);

				/************** Checkout from Old job and Check-in to New Job **************/
			} else if (existedLog == null && empTodaysLog != null) {
				if ((timeLog.getEmployee().getId().equals(empTodaysLog.getEmployee().getId())
						&& timeLog.getJob().getId() != empTodaysLog.getJob().getId())
						|| (timeLog.getEmployee().getId().equals(empTodaysLog.getEmployee().getId())
								&& timeLog.getJob().getId() == empTodaysLog.getJob().getId()
								&& timeLog.getActivity().getId() != empTodaysLog.getActivity().getId())) {
					empTodaysLog.setUser(timeLog.getUser());
					if (timeLog.getCheckInTime() != null) {
						empTodaysLog.setCheckOutTime(timeLog.getCheckInTime());

						if (empTodaysLog.getIdealInTime() != null && empTodaysLog.getIdealOutTime() == null)
							empTodaysLog.setIdealOutTime(timeLog.getCheckInTime());

						timeLog.setCheckInTime(timeLog.getCheckInTime());
						timeLog.setIdealReason(null);
					} else if (timeLog.getIdealInTime() != null) {
						empTodaysLog.setIdealOutTime(timeLog.getIdealInTime());
					}

					empTodaysLog.setScanned(timeLog.isScanned());

					timeLog.setPostingDate(new Date());
					timeLog.setCreatedDate(new Date());

					timeLogDetailsService.updateTimeLog(empTodaysLog);
					timeLogDetailsService.saveTimeLog(timeLog);

					resp.put("statusMessage", "Success");
					resp.put("statusCode", 1);

				}

			}

		} catch (Exception e) {
			// e.printStackTrace();
			System.out.println(e.getMessage());
			resp.put("statusMessage", e.getMessage());
			resp.put("statusCode", 0);
		}

		response = new ResponseEntity<String>(resp.toString(), HttpStatus.OK);
		return response;
	}

	@GetMapping("/idealTimeReasons")
	public ResponseEntity getIdealTimeReasons() {
		ResponseEntity response = null;
		try {
			List<IdleTimeReason> reasonsList = timeLogDetailsService.getIdealTimeResons();
			response = new ResponseEntity(reasonsList, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			response = new ResponseEntity(HttpStatus.EXPECTATION_FAILED);
		}

		return response;
	}

	@GetMapping("/todaysScannedActions")
	public ResponseEntity getTodaysScannedActions() {
		ResponseEntity response = null;
		try {
			List<ScannedTimeLog> todaysActionsList = timeLogDetailsService
					.getScannedTimeLogByPostingDate(simpleDateFormat.format(new Date()));
			response = new ResponseEntity(todaysActionsList, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			response = new ResponseEntity(HttpStatus.EXPECTATION_FAILED);
		}

		return response;
	}

	@PostMapping("/todaysScannedActionsByUser")
	public ResponseEntity getTodaysScannedActionsByUser(@RequestBody User user) {
		ResponseEntity response = null;
		try {
			List<ScannedTimeLog> todaysActionsList = timeLogDetailsService
					.getScannedTimeLogByPostingDateAndUser(simpleDateFormat.format(new Date()), user.getId());
			response = new ResponseEntity(todaysActionsList, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			response = new ResponseEntity(HttpStatus.EXPECTATION_FAILED);
		}

		return response;
	}

	@PostMapping("/empScannedActions")
	public ResponseEntity empScannedActions(@RequestBody ScannedTimeLog req) {
		ResponseEntity response = null;
		JSONObject resp = new JSONObject();
		try {
			List<ScannedTimeLog> todaysActionsList = null;

			if (req.getEmployee() != null && req.getJob() != null && req.getActivity() != null) {
				todaysActionsList = timeLogDetailsService.getEmpScannActionByJobAndActivity(
						simpleDateFormat.format(new Date()), req.getEmployee().getId(), req.getJob().getId(),
						req.getActivity().getId());

			} else if (req.getEmployee() != null && req.getJob() != null && req.getActivity() == null) {
				todaysActionsList = timeLogDetailsService.getEmpScannActionByJob(simpleDateFormat.format(new Date()),
						req.getEmployee().getId(), req.getJob().getId());

			} else if (req.getEmployee() != null && req.getJob() == null && req.getActivity() == null) {
				todaysActionsList = timeLogDetailsService.getEmpScannAction(simpleDateFormat.format(new Date()),
						req.getEmployee().getId());
			}

			if (todaysActionsList != null && todaysActionsList.size() != 0)
				response = new ResponseEntity(todaysActionsList.get(0), HttpStatus.OK);
			else {
				resp.put("statusMessage", "No Data found");
				response = new ResponseEntity(resp.toString(), HttpStatus.OK);
			}
		} catch (

		Exception e) {
			e.printStackTrace();
			response = new ResponseEntity(HttpStatus.EXPECTATION_FAILED);
		}

		return response;
	}

}
