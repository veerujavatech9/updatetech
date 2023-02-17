package com.gorica.controller.web;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.gorica.SessionData;
import com.gorica.controller.rest.JobRestController;
import com.gorica.dao.model.Employee;
import com.gorica.dao.model.JobDetails;
import com.gorica.dao.model.ScannedTimeLog;
import com.gorica.dao.model.User;
import com.gorica.service.TimeLogDetailsService;
import com.gorica.service.UserDetailsService;

@Controller
public class LoginController {
	
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

	@Autowired
	private SessionData sessionobj;

	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private SimpleDateFormat simpleDateFormat;

	@Autowired
	private TimeLogDetailsService timeLogDetailsService;

	@GetMapping("/")
	public String LoginLaunch(ModelMap model) {
		User user = new User();
		model.put("user", user);
		return "login";
	}

	@PostMapping("/validate")
	public String LoginLaunch(@ModelAttribute("user") User user, ModelMap model) {
		logger.info("user infromation :" + user);
		logger.info("Info level log message");
		logger.debug("Debug level log message");
		logger.error("Error level log message");
		logger.warn(" warn level log message");
		User validUser = userDetailsService.getUserByUserName(user.getUserName());
		if (validUser != null) {
			if (passwordEncoder.matches(user.getPassword(), validUser.getPassword())) {
				sessionobj.setValidLogin(true);
				sessionobj.setUser(validUser);

				if (validUser.getRole().getRole().equalsIgnoreCase("Admin")) {
					return "redirect:/admin_Dashboard";
				} else if (validUser.getRole().getRole().equalsIgnoreCase("PRD")) {
					return "redirect:/prd_Dashboard";
				} else if (validUser.getRole().getRole().equalsIgnoreCase("Accounts")) {
					return "redirect:/accounts_Dashboard";
				}
			} else {
				sessionobj.setStatusMessage("Invalid Password, Please try again..");
				return "redirect:/";
			}

		} else {
			sessionobj.setStatusMessage("Invalid Username, Please try again..");
			return "redirect:/";
		}
		return "redirect:/";
	}

	@GetMapping("/admin_Dashboard")
	public String adminDashboard(ModelMap model) {
		if (sessionobj != null && sessionobj.getIsValidLogin()
				&& sessionobj.getUser().getRole().getRole().equalsIgnoreCase("Admin")) {
			try {
				String postDate = simpleDateFormat.format(new Date());

				List<ScannedTimeLog> todaysScannedList = timeLogDetailsService.getScannedTimeLogByPostingDate(postDate);

				List<User> scannedUsersList = timeLogDetailsService.scanningUsersListByPostingDate(postDate);
				List<Employee> idleEmpList = timeLogDetailsService.getIdleEmployeesByPostingDate(postDate);
				List<Employee> activeEmpList = timeLogDetailsService.getScannedEmployeesByPostingDate(postDate);
				List<JobDetails> activeJobs = timeLogDetailsService.getScannedJobsByPostingDate(postDate);

				sessionobj.setIdleEmpsList(idleEmpList);
				sessionobj.setScanningUsersList(scannedUsersList);
				sessionobj.setScannedEmpList(activeEmpList);
				sessionobj.setScannedJobList(activeJobs);

				sessionobj.setActiveEmployeesCount(activeEmpList.size());
				sessionobj.setIdleEmployeesCount(idleEmpList.size());
				sessionobj.setScanningUsersCount(scannedUsersList.size());
				sessionobj.setActiveJobsCount(activeJobs.size());

				model.put("todaysActions", todaysScannedList);
				model.put("selectedDate", new SimpleDateFormat("MM/dd/yyyy").format(new Date()));
				return "admin_Dashboard";
			} catch (Exception e) {
				e.printStackTrace();
				return "redirect:/";
			}
		} else
			return "redirect:/";
	}

	@GetMapping("/admin_Dashboard_Date")
	public String admin_Dashboard_Date(ModelMap model, @RequestParam Date postDate) {
		if (sessionobj != null && sessionobj.getIsValidLogin()
				&& sessionobj.getUser().getRole().getRole().equalsIgnoreCase("Admin")) {
			try {
				String strPostDate = simpleDateFormat.format(postDate);

				List<ScannedTimeLog> todaysScannedList = timeLogDetailsService
						.getScannedTimeLogByPostingDate(strPostDate);

				List<User> scannedUsersList = timeLogDetailsService.scanningUsersListByPostingDate(strPostDate);
				List<Employee> idleEmpList = timeLogDetailsService.getIdleEmployeesByPostingDate(strPostDate);
				List<Employee> activeEmpList = timeLogDetailsService.getScannedEmployeesByPostingDate(strPostDate);
				List<JobDetails> activeJobs = timeLogDetailsService.getScannedJobsByPostingDate(strPostDate);

				sessionobj.setIdleEmpsList(idleEmpList);
				sessionobj.setScanningUsersList(scannedUsersList);
				sessionobj.setScannedEmpList(activeEmpList);
				sessionobj.setScannedJobList(activeJobs);

				sessionobj.setActiveEmployeesCount(activeEmpList.size());
				sessionobj.setIdleEmployeesCount(idleEmpList.size());
				sessionobj.setScanningUsersCount(scannedUsersList.size());
				sessionobj.setActiveJobsCount(activeJobs.size());

				model.put("todaysActions", todaysScannedList);
				model.put("selectedDate", new SimpleDateFormat("MM/dd/yyyy").format(postDate));

				return "admin_Dashboard_Date";
			} catch (Exception e) {
				e.printStackTrace();
				return "redirect:/";
			}
		} else
			return "redirect:/";
	}

	@GetMapping("/prd_Dashboard")
	public String prd_Dashboard(ModelMap model) {
		if (sessionobj != null && sessionobj.getIsValidLogin()
				&& sessionobj.getUser().getRole().getRole().equalsIgnoreCase("PRD")) {
			try {
				String postDate = simpleDateFormat.format(new Date());

				List<ScannedTimeLog> todaysScannedList = timeLogDetailsService.getScannedTimeLogByPostingDate(postDate);

				List<User> scannedUsersList = timeLogDetailsService.scanningUsersListByPostingDate(postDate);
				List<Employee> idleEmpList = timeLogDetailsService.getIdleEmployeesByPostingDate(postDate);
				List<Employee> activeEmpList = timeLogDetailsService.getScannedEmployeesByPostingDate(postDate);
				List<JobDetails> activeJobs = timeLogDetailsService.getScannedJobsByPostingDate(postDate);

				sessionobj.setIdleEmpsList(idleEmpList);
				sessionobj.setScanningUsersList(scannedUsersList);
				sessionobj.setScannedEmpList(activeEmpList);
				sessionobj.setScannedJobList(activeJobs);

				sessionobj.setActiveEmployeesCount(activeEmpList.size());
				sessionobj.setIdleEmployeesCount(idleEmpList.size());
				sessionobj.setScanningUsersCount(scannedUsersList.size());
				sessionobj.setActiveJobsCount(activeJobs.size());

				model.put("todaysActions", todaysScannedList);
				model.put("selectedDate", new SimpleDateFormat("MM/dd/yyyy").format(new Date()));
				return "prd_Dashboard";
			} catch (Exception e) {
				e.printStackTrace();
				return "redirect:/";
			}
		} else
			return "redirect:/";
	}

	@GetMapping("/prd_Dashboard_Date")
	public String prd_Dashboard_Date(ModelMap model, @RequestParam Date postDate) {
		if (sessionobj != null && sessionobj.getIsValidLogin()
				&& sessionobj.getUser().getRole().getRole().equalsIgnoreCase("PRD")) {
			try {
				String strPostDate = simpleDateFormat.format(postDate);

				List<ScannedTimeLog> todaysScannedList = timeLogDetailsService
						.getScannedTimeLogByPostingDate(strPostDate);

				List<User> scannedUsersList = timeLogDetailsService.scanningUsersListByPostingDate(strPostDate);
				List<Employee> idleEmpList = timeLogDetailsService.getIdleEmployeesByPostingDate(strPostDate);
				List<Employee> activeEmpList = timeLogDetailsService.getScannedEmployeesByPostingDate(strPostDate);
				List<JobDetails> activeJobs = timeLogDetailsService.getScannedJobsByPostingDate(strPostDate);

				sessionobj.setIdleEmpsList(idleEmpList);
				sessionobj.setScanningUsersList(scannedUsersList);
				sessionobj.setScannedEmpList(activeEmpList);
				sessionobj.setScannedJobList(activeJobs);

				sessionobj.setActiveEmployeesCount(activeEmpList.size());
				sessionobj.setIdleEmployeesCount(idleEmpList.size());
				sessionobj.setScanningUsersCount(scannedUsersList.size());
				sessionobj.setActiveJobsCount(activeJobs.size());

				model.put("todaysActions", todaysScannedList);
				model.put("selectedDate", new SimpleDateFormat("MM/dd/yyyy").format(postDate));

				return "prd_Dashboard_Date";
			} catch (Exception e) {
				e.printStackTrace();
				return "redirect:/";
			}
		} else
			return "redirect:/";
	}

	@GetMapping("/accounts_Dashboard")
	public String accounts_Dashboard(ModelMap model) {
		if (sessionobj != null && sessionobj.getIsValidLogin()
				&& sessionobj.getUser().getRole().getRole().equalsIgnoreCase("Accounts")) {
			try {
				List<ScannedTimeLog> todaysScannedLits = timeLogDetailsService
						.getScannedTimeLogByPostingDate(simpleDateFormat.format(new Date()));
				model.put("todaysActions", todaysScannedLits);
				model.put("selectedDate", new SimpleDateFormat("MM/dd/yyyy").format(new Date()));
				return "accounts_Dashboard";
			} catch (Exception e) {
				e.printStackTrace();
				return "redirect:/";
			}

		} else
			return "redirect:/";
	}

	@GetMapping("/accounts_Dashboard_Date")
	public String accounts_Dashboard_Date(ModelMap model, @RequestParam Date postDate) {
		if (sessionobj != null && sessionobj.getIsValidLogin()
				&& sessionobj.getUser().getRole().getRole().equalsIgnoreCase("Accounts")) {
			try {
				String strPostDate = simpleDateFormat.format(postDate);
				List<ScannedTimeLog> todaysScannedLits = timeLogDetailsService
						.getScannedTimeLogByPostingDate(strPostDate);
				model.put("todaysActions", todaysScannedLits);
				model.put("selectedDate", new SimpleDateFormat("MM/dd/yyyy").format(postDate));
				return "accounts_Dashboard_Date";
			} catch (Exception e) {
				e.printStackTrace();
				return "redirect:/";
			}

		} else
			return "redirect:/";
	}

	@GetMapping("/sign_out")
	public String logout(ModelMap model, HttpServletRequest req) {
		sessionobj.setValidLogin(false);
		req.getSession(false).invalidate();
		return "redirect:/";
	}

	@GetMapping("/scannedEmps")
	public String scannedEmps(ModelMap map) {
		if (sessionobj != null && sessionobj.getIsValidLogin()) {
			try {
				List<Employee> empList = sessionobj.getScannedEmpList();
				map.put("empList", empList);
				map.put("title", "Scanned Employees List");
			} catch (Exception e) {
				e.printStackTrace();
			}
			return "employeesList";
		} else
			return "redirect:/";
	}

	@GetMapping("/scannedIdleEmps")
	public String idleEmps(ModelMap map) {
		if (sessionobj != null && sessionobj.getIsValidLogin()) {
			try {
				List<Employee> empList = sessionobj.getIdleEmpsList();
				map.put("empList", empList);
				map.put("title", "Scanned Idle Employees List");
			} catch (Exception e) {
				e.printStackTrace();
			}
			return "employeesList";
		} else
			return "redirect:/";
	}

	@GetMapping("/scannedJobs")
	public String scannedJobs(ModelMap map) {
		if (sessionobj != null && sessionobj.getIsValidLogin()) {
			try {
				List<JobDetails> jobList = sessionobj.getScannedJobList();
				map.put("jobList", jobList);
				map.put("title", "Scanned Jobs List");
			} catch (Exception e) {
				e.printStackTrace();
			}
			return "jobsList";
		} else
			return "redirect:/";
	}

	@GetMapping("/scannedUsers")
	public String scanningUsers(ModelMap map) {
		if (sessionobj != null && sessionobj.getIsValidLogin()) {
			try {
				List<User> usersList = sessionobj.getScanningUsersList();
				map.put("usersList", usersList);
				map.put("title", "Scanned Users List");
			} catch (Exception e) {
				e.printStackTrace();
			}
			return "usersList";
		} else
			return "redirect:/";
	}
	
	@GetMapping("/remove_Duplicates")
	public String removeDuplicates(ModelMap model) {
		if (sessionobj != null && sessionobj.getIsValidLogin()
				&& sessionobj.getUser().getRole().getRole().equalsIgnoreCase("Admin")) {
			try {
				String postDate = simpleDateFormat.format(new Date());
				Date sixMonthAgoDate = Date.from(Instant.now().minus(180, ChronoUnit.DAYS));

				timeLogDetailsService.removeDuplicateEntries(postDate, simpleDateFormat.format(sixMonthAgoDate));
				
				return "redirect:/admin_Dashboard";
			} catch (Exception e) {
				e.printStackTrace();
				return "redirect:/";
			}
		} else
			return "redirect:/";
	}

}
