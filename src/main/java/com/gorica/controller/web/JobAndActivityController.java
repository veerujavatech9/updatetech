package com.gorica.controller.web;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

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
import org.springframework.web.bind.annotation.RequestParam;

import com.gorica.SessionData;
import com.gorica.dao.model.Activity;
import com.gorica.dao.model.BreakTimings;
import com.gorica.dao.model.JobActivity;
import com.gorica.dao.model.JobDetails;
import com.gorica.dao.model.User;
import com.gorica.service.ActivityDetailsService;
import com.gorica.service.JobDetailsService;

@Controller
@RequestMapping("/jobActivity")
public class JobAndActivityController {

	private static final Logger logger = LoggerFactory.getLogger(JobAndActivityController.class);
	
	@Autowired
	private SessionData sessionobj;

	@Autowired
	private JobDetailsService jobDetailsService;

	@Autowired
	private ActivityDetailsService activityDetailsService;

	@GetMapping("/createJob")
	public String createJob(ModelMap model) {
		if (sessionobj != null && sessionobj.getIsValidLogin()) {
			try {
				JobDetails job = new JobDetails();
				model.put("job", job);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return "createJob";
		} else {
			return "redirect:/";
		}
	}

	@PostMapping("/saveJob")
	public String saveJob(@ModelAttribute JobDetails job, BindingResult result, ModelMap map) {
		if (sessionobj != null && sessionobj.getIsValidLogin()) {
			try {
 
				Long jobNo = job.getJobNo();
				JobDetails existJob = jobDetailsService.getJobDetailsByJobNo(jobNo);
				
				if(existJob!=null) {
					job.setErrorMsg(" is already Exist");
					map.put("job", job);
					return "createJob";
				}
				job.setCreatedDate(new Date());
				jobDetailsService.saveJob(job);
				return "redirect:/jobActivity/allJobs";

			} catch (Exception e) {
				e.printStackTrace();
				return "redirect:/";
			}
		} else
			return "redirect:/";
	}

	@GetMapping("editJob/{id}")
	public String editJob(@PathVariable Long id, ModelMap model) {
		if (sessionobj != null && sessionobj.getIsValidLogin()) {
			try {
				SimpleDateFormat formate = new SimpleDateFormat("dd/MM/yyyy");
				JobDetails job = jobDetailsService.getJobDetailsById(id);
				if (job.getStartDate() != null)
					job.setStrStartDate(formate.format(job.getStartDate()));
				if (job.getEndDate() != null)
					job.setStrEndDate(formate.format(job.getEndDate()));
				model.put("job", job);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return "editJobPage";
		} else {
			return "redirect:/";
		}
	}

	@PostMapping("/updateJob")
	public String updateJob(@ModelAttribute JobDetails job, BindingResult result, ModelMap map) {
		if (sessionobj != null && sessionobj.getIsValidLogin()) {
			try {
				job.setCreatedDate(new Date());
				jobDetailsService.updateJob(job);
				return "redirect:/jobActivity/allJobs";

			} catch (Exception e) {
				e.printStackTrace();
				return "redirect:/";
			}
		} else
			return "redirect:/";
	}

	@GetMapping("/allJobs")
	public String allJobs(ModelMap map) {
		if (sessionobj != null && sessionobj.getIsValidLogin()) {
			try {
				List<JobDetails> jobList = jobDetailsService.getAllJobs();
				map.put("jobList", jobList);
				map.put("title", "Jobs List");
			} catch (Exception e) {
				e.printStackTrace();
			}
			return "jobsList";
		} else
			return "redirect:/";
	}

	@GetMapping("deleteJob/{id}")
	public String deleteJob(@PathVariable Long id) {
		if (sessionobj != null && sessionobj.getIsValidLogin()) {
			try {
				jobDetailsService.deleteJobById(id);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return "redirect:/jobActivity/allJobs";
		} else {
			return "redirect:/";
		}
	}

	@GetMapping("/createWorkStation")
	public String createWorkStation(ModelMap model) {
		if (sessionobj != null && sessionobj.getIsValidLogin()) {
			try {
				Activity activity = new Activity();
				model.put("activity", activity);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return "createWorkStation";
		} else {
			return "redirect:/";
		}
	}

	@PostMapping("/saveWorkStation")
	public String saveWorkStation(@ModelAttribute Activity activity, BindingResult result, ModelMap map) {
		if (sessionobj != null && sessionobj.getIsValidLogin()) {
			try {

				activity.setCreatedDate(new Date());
				activityDetailsService.saveAtivity(activity);
				return "redirect:/jobActivity/allWorkStations";

			} catch (Exception e) {
				e.printStackTrace();
				return "redirect:/";
			}
		} else
			return "redirect:/";
	}

	@GetMapping("editWorkStation/{id}")
	public String editWorkStation(@PathVariable Long id, ModelMap model) {
		if (sessionobj != null && sessionobj.getIsValidLogin()) {
			try {
				Activity activity = activityDetailsService.getActivityById(id);
				model.put("activity", activity);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return "editWorkStationPage";
		} else {
			return "redirect:/";
		}
	}

	@PostMapping("/updateWorkStation")
	public String updateWorkStation(@ModelAttribute Activity activity, BindingResult result, ModelMap map) {
		if (sessionobj != null && sessionobj.getIsValidLogin()) {
			try {
				activity.setCreatedDate(new Date());
				activityDetailsService.updateActivity(activity);
				return "redirect:/jobActivity/allWorkStations";

			} catch (Exception e) {
				e.printStackTrace();
				return "redirect:/";
			}
		} else
			return "redirect:/";
	}

	@GetMapping("/allWorkStations")
	public String allWorkStations(ModelMap map) {
		if (sessionobj != null && sessionobj.getIsValidLogin()) {
			try {
				List<Activity> activityList = activityDetailsService.getAllActivities();
				map.put("activityList", activityList);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return "workStationsList";
		} else
			return "redirect:/";
	}

	@GetMapping("deleteWorkStation/{id}")
	public String deleteWorkStation(@PathVariable Long id) {
		if (sessionobj != null && sessionobj.getIsValidLogin()) {
			try {
				activityDetailsService.deleteAtivityById(id);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return "redirect:/jobActivity/allWorkStations";
		} else {
			return "redirect:/";
		}
	}

	@GetMapping("/ActiveJobsToWorkStation")
	public String asignWorkStationsToJob(ModelMap map) {
		if (sessionobj != null && sessionobj.getIsValidLogin()) {
			try {
				List<JobDetails> jobList = jobDetailsService.getActiveJobs();
				map.put("jobList", jobList);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return "jobsForWorkStation";
		} else
			return "redirect:/";
	}

	/*
	 * @GetMapping(value = "/workStationsForJob/{jobId}") public String
	 * workStationsForJob(ModelMap model, @PathVariable Long jobId) { if (sessionobj
	 * != null && sessionobj.getIsValidLogin()) {
	 * 
	 * JobDetails job = jobDetailsService.getJobDetailsById(jobId); List<Activity>
	 * allActivities = activityDetailsService.getAllActivities(); List<Activity>
	 * assignedActivities = activityDetailsService.getActivitiesByJob(jobId); for
	 * (Activity activity : allActivities) { if (assignedActivities == null ||
	 * assignedActivities.isEmpty()) { activity.setStatus(false); } else { for
	 * (Activity activity1 : assignedActivities) { if (activity.getId() ==
	 * activity1.getId()) { activity.setStatus(true); break; } else
	 * activity.setStatus(false); } } }
	 * 
	 * model.put("activityList", allActivities); model.put("jobId", jobId);
	 * model.put("job", job); return "assignWorkStationsToJob"; } else { return
	 * "redirect:/"; } }
	 * 
	 * @PostMapping(value = "/assignWorkStationsToJob") public String
	 * assignWorkStationsToJob(ModelMap model, HttpServletRequest req,
	 * 
	 * @RequestParam(value = "selected[]") List<Long> selected, @RequestParam(value
	 * = "jobId") Long jobId) { if (sessionobj != null &&
	 * sessionobj.getIsValidLogin()) {
	 * 
	 * JobDetails job = jobDetailsService.getJobDetailsById(jobId); List<Activity>
	 * workStationList = activityDetailsService.getAllActivities(); JobActivity
	 * jobActivity = null; activityDetailsService.deleteActivitiesByJob(jobId);
	 * 
	 * for (Long id : selected) { for (Activity activity : workStationList) { if
	 * (id.equals(activity.getId())) { jobActivity = new JobActivity();
	 * jobActivity.setJob(job); jobActivity.setActivity(activity);
	 * jobActivity.setCreatedDate(new Date()); jobActivity.setStatus(true);
	 * activityDetailsService.saveJobActivity(jobActivity); } } }
	 * 
	 * return "redirect:/jobActivity/workStationsForJob/" + jobId; } else { return
	 * "redirect:/"; } }
	 */

	@GetMapping(value = "/workStationsForJob/{jobId}")
	public String workStationsForJob(ModelMap model, @PathVariable Long jobId) {
		if (sessionobj != null && sessionobj.getIsValidLogin()) {

			Activity activity = new Activity();
			model.put("activity", activity);

			JobDetails job = jobDetailsService.getJobDetailsById(jobId);
			List<Activity> assignedActivities = activityDetailsService.getActivitiesByJob(jobId);

			model.put("activityList", assignedActivities);
			model.put("jobId", jobId);
			model.put("job", job);
			return "addWorkStationsToJob";
		} else {
			return "redirect:/";
		}
	}

	@PostMapping(value = "/assignWorkStationsToJob")
	public String assignWorkStationsToJob(@ModelAttribute Activity activity, BindingResult result, ModelMap model,
			HttpServletRequest req, @RequestParam(value = "jobId") Long jobId) {
		if (sessionobj != null && sessionobj.getIsValidLogin()) {
			activity.setCreatedDate(new Date());
			activityDetailsService.saveAtivity(activity);
			JobDetails job = jobDetailsService.getJobDetailsById(jobId);
			JobActivity jobActivity = new JobActivity();
			jobActivity.setJob(job);
			jobActivity.setActivity(activity);
			jobActivity.setStatus(true);
			jobActivity.setCreatedDate(new Date());
			activityDetailsService.saveJobActivity(jobActivity);
			return "redirect:/jobActivity/workStationsForJob/" + jobId;
		} else {
			return "redirect:/";
		}
	}

	@GetMapping("/createBreakTiming")
	public String createBreakTiming(ModelMap model) {
		if (sessionobj != null && sessionobj.getIsValidLogin()) {
			try {
				BreakTimings breakTimings = new BreakTimings();
				model.put("breakTimings", breakTimings);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return "createBreakTimings";
		} else {
			return "redirect:/";
		}
	}

	@PostMapping("/saveBreakTime")
	public String saveBreakTime(@ModelAttribute BreakTimings breakTimings, BindingResult result, ModelMap map) {
		if (sessionobj != null && sessionobj.getIsValidLogin()) {
			try {

				System.out.println("breakTimings=" + breakTimings);
				breakTimings.setCreatedDate(new Date());
				jobDetailsService.saveBreakTimings(breakTimings);
				return "redirect:/jobActivity/allBreakTimings";

			} catch (Exception e) {
				e.printStackTrace();
				return "redirect:/";
			}
		} else
			return "redirect:/";
	}

	@GetMapping("editBreakTimings/{id}")
	public String editBreakTimings(@PathVariable Long id, ModelMap model) {
		if (sessionobj != null && sessionobj.getIsValidLogin()) {
			try {
				BreakTimings breakTimings = jobDetailsService.getBreakTimingsById(id);
				model.put("break", breakTimings);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return "editBreakTimePage";
		} else {
			return "redirect:/";
		}
	}

	@PostMapping("/updateBreakTimings")
	public String updateBreakTimings(@ModelAttribute BreakTimings breakTimings, BindingResult result, ModelMap map) {
		if (sessionobj != null && sessionobj.getIsValidLogin()) {
			try {
				breakTimings.setCreatedDate(new Date());
				jobDetailsService.updateBreakTimings(breakTimings);
				return "redirect:/jobActivity/allBreakTimings";

			} catch (Exception e) {
				e.printStackTrace();
				return "redirect:/";
			}
		} else
			return "redirect:/";
	}

	@GetMapping("/allBreakTimings")
	public String allBreakTimings(ModelMap map) {
		if (sessionobj != null && sessionobj.getIsValidLogin()) {
			try {
				List<BreakTimings> breakTimingsList = jobDetailsService.getAllBreakTimings();
				map.put("breakTimingsList", breakTimingsList);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return "breakTimingsList";
		} else
			return "redirect:/";
	}

	@GetMapping("deleteBreakTimings/{id}")
	public String deleteBreakTimings(@PathVariable Long id) {
		if (sessionobj != null && sessionobj.getIsValidLogin()) {
			try {
				jobDetailsService.deleteBreakTimingsById(id);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return "redirect:/jobActivity/allBreakTimings";
		} else {
			return "redirect:/";
		}
	}

	@GetMapping("/activeJobs")
	public String activeJobs(ModelMap map) {
		if (sessionobj != null && sessionobj.getIsValidLogin()) {
			try {
				List<JobDetails> jobList = jobDetailsService.getActiveJobs();
				map.put("jobList", jobList);
				map.put("title", "Active Jobs List");
			} catch (Exception e) {
				e.printStackTrace();
			}
			return "jobsList";
		} else
			return "redirect:/";
	}

	@GetMapping("removeAssignedActivity/{activityId}/{jobId}")
	public String removeAssignedActivity(@PathVariable Long activityId, @PathVariable Long jobId) {
		if (sessionobj != null && sessionobj.getIsValidLogin()) {
			try {
				activityDetailsService.removeAtivityById(activityId);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return "redirect:/jobActivity/workStationsForJob/" + jobId;
		} else {
			return "redirect:/";
		}
	}

}
