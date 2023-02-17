package com.gorica.controller.web;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gorica.SessionData;
import com.gorica.dao.model.IdleTimeReason;
import com.gorica.dao.model.ScannedTimeLog;
import com.gorica.service.TimeLogDetailsService;
import com.gorica.util.DataExportComponent;

@Controller
@RequestMapping("/timeLog")
public class TimeLogController {

	private static final Logger logger = LoggerFactory.getLogger(TimeLogController.class);
	
	@Autowired
	private SessionData sessionobj;

	@Autowired
	private TimeLogDetailsService timeLogDetailsService;

	/*
	 * @Autowired private FinalActivityService finalSctivityService;
	 */

	@Autowired
	private DataExportComponent exportComponent;

	@Autowired
	private SimpleDateFormat simpleDateFormat;

	@GetMapping("/dailyAttendeanceReport")
	public String dailyAttendeanceReport(ModelMap map) {
		if (sessionobj != null && sessionobj.getIsValidLogin()) {
			try {
				Instant yesterday = Instant.now().minus(1, ChronoUnit.DAYS);
				Date postDate = Date.from(yesterday);
				List<ScannedTimeLog> attendenaceList = timeLogDetailsService
						.getScannedTimeLogByPostingDate(simpleDateFormat.format(postDate));
				map.put("attendenaceList", attendenaceList);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
			return "daily_attendanceReport";
		} else
			return "redirect:/";
	}

	@GetMapping("/exportDailyAttendanceReport")
	public String exportDailyAttendanceReport(ModelMap map, HttpServletResponse response) {
		if (sessionobj != null && sessionobj.getIsValidLogin()) {
			try {
				Instant yesterday = Instant.now().minus(1, ChronoUnit.DAYS);
				Date postDate = Date.from(yesterday);
				List<ScannedTimeLog> attendenaceList = timeLogDetailsService
						.getScannedTimeLogByPostingDate(simpleDateFormat.format(postDate));
				String fileName = "Daily-AttendanceReport.xls";
				exportComponent.doExport(fileName, attendenaceList, response);

			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
			return "redirect:/timeLog/dailyAttendeanceReport";
		} else
			return "redirect:/";
	}

	@GetMapping("/weeklyAttendeanceReport")
	public String weeklyAttendeanceReport(ModelMap map) {
		if (sessionobj != null && sessionobj.getIsValidLogin()) {
			try {
				Date yesterday = Date.from(Instant.now().minus(1, ChronoUnit.DAYS));
				Date weekDay = Date.from(Instant.now().minus(7, ChronoUnit.DAYS));
				// System.out.println("yesterday=" + yesterday + ",weekDay=" + weekDay);

				List<ScannedTimeLog> attendenaceList = timeLogDetailsService
						.getScannedTimeLogForWeek(simpleDateFormat.format(weekDay), simpleDateFormat.format(yesterday));
				map.put("attendenaceList", attendenaceList);

			} catch (Exception e) {
				e.printStackTrace();
			}
			return "weekly_attendanceReport";
		} else
			return "redirect:/";
	}

	@GetMapping("/exportWeeklyAttendanceReport")
	public String exportWeeklyAttendanceReport(ModelMap map, HttpServletResponse response) {
		if (sessionobj != null && sessionobj.getIsValidLogin()) {
			try {
				Date yesterday = Date.from(Instant.now().minus(1, ChronoUnit.DAYS));
				Date weekDay = Date.from(Instant.now().minus(7, ChronoUnit.DAYS));
				// System.out.println("yesterday=" + yesterday + ",weekDay=" + weekDay);

				List<ScannedTimeLog> attendenaceList = timeLogDetailsService
						.getScannedTimeLogForWeek(simpleDateFormat.format(weekDay), simpleDateFormat.format(yesterday));
				String fileName = "Weekly-AttendanceReport.xls";
				exportComponent.doExport(fileName, attendenaceList, response);

			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
			return "redirect:/timeLog/weeklyAttendeanceReport";
		} else
			return "redirect:/";
	}

	@GetMapping("/monthlyAttendeanceReport")
	public String monthlyAttendeanceReport(ModelMap map) {
		if (sessionobj != null && sessionobj.getIsValidLogin()) {
			try {
				Date yesterday = Date.from(Instant.now().minus(1, ChronoUnit.DAYS));
				Date weekDay = Date.from(Instant.now().minus(30, ChronoUnit.DAYS));
				// System.out.println("yesterday=" + yesterday + ",weekDay=" + weekDay);

				List<ScannedTimeLog> attendenaceList = timeLogDetailsService
						.getScannedTimeLogForWeek(simpleDateFormat.format(weekDay), simpleDateFormat.format(yesterday));
				map.put("attendenaceList", attendenaceList);

			} catch (Exception e) {
				e.printStackTrace();
			}
			return "monthly_attendanceReport";
		} else
			return "redirect:/";
	}

	@GetMapping("/exportMonthlyAttendanceReport")
	public String exportMonthlyAttendanceReport(ModelMap map, HttpServletResponse response) {
		if (sessionobj != null && sessionobj.getIsValidLogin()) {
			try {
				Date yesterday = Date.from(Instant.now().minus(1, ChronoUnit.DAYS));
				Date weekDay = Date.from(Instant.now().minus(30, ChronoUnit.DAYS));
				// System.out.println("yesterday=" + yesterday + ",weekDay=" + weekDay);

				List<ScannedTimeLog> attendenaceList = timeLogDetailsService
						.getScannedTimeLogForWeek(simpleDateFormat.format(weekDay), simpleDateFormat.format(yesterday));
				String fileName = "Monthly-AttendanceReport.xls";
				exportComponent.doExport(fileName, attendenaceList, response);

			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
			return "redirect:/timeLog/monthlyAttendeanceReport";
		} else
			return "redirect:/";
	}

	@GetMapping("/createReason")
	public String createJob(ModelMap model) {
		if (sessionobj != null && sessionobj.getIsValidLogin()) {
			try {
				IdleTimeReason reason = new IdleTimeReason();
				model.put("reason", reason);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return "createReason";
		} else {
			return "redirect:/";
		}
	}

	@PostMapping("/saveReason")
	public String saveJob(@ModelAttribute IdleTimeReason reason, BindingResult result, ModelMap map) {
		if (sessionobj != null && sessionobj.getIsValidLogin()) {
			try {

				timeLogDetailsService.saveIdleReason(reason);
				return "redirect:/timeLog/allReasons";

			} catch (Exception e) {
				e.printStackTrace();
				return "redirect:/";
			}
		} else
			return "redirect:/";
	}

	@GetMapping("editReason/{id}")
	public String editJob(@PathVariable Long id, ModelMap model) {
		if (sessionobj != null && sessionobj.getIsValidLogin()) {
			try {
				IdleTimeReason reason = timeLogDetailsService.getIdleTimeReasonById(id);
				model.put("reason", reason);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return "editReasonPage";
		} else {
			return "redirect:/";
		}
	}

	@PostMapping("/updateReason")
	public String updateJob(@ModelAttribute IdleTimeReason reason, BindingResult result, ModelMap map) {
		if (sessionobj != null && sessionobj.getIsValidLogin()) {
			try {
				timeLogDetailsService.updateIdleReason(reason);
				return "redirect:/timeLog/allReasons";

			} catch (Exception e) {
				e.printStackTrace();
				return "redirect:/";
			}
		} else
			return "redirect:/";
	}

	@GetMapping("/allReasons")
	public String allJobs(ModelMap map) {
		if (sessionobj != null && sessionobj.getIsValidLogin()) {
			try {
				List<IdleTimeReason> reasonsList = timeLogDetailsService.getIdealTimeResons();
				map.put("reasonsList", reasonsList);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return "reasonsList";
		} else
			return "redirect:/";
	}

	@GetMapping("deleteReason/{id}")
	public String deleteReason(@PathVariable Long id) {
		if (sessionobj != null && sessionobj.getIsValidLogin()) {
			try {
				timeLogDetailsService.deleteReasonById(id);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return "redirect:/timeLog/allReasons";
		} else {
			return "redirect:/";
		}
	}

}
