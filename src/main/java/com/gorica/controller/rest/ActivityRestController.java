package com.gorica.controller.rest;

import java.util.List;

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

import com.gorica.dao.model.Activity;
import com.gorica.dao.model.JobActivity;
import com.gorica.dao.model.JobDetails;
import com.gorica.service.ActivityDetailsService;

@RestController
@RequestMapping("/rest/activity")
@SuppressWarnings({ "rawtypes", "unchecked" })
public class ActivityRestController {

	private static final Logger logger = LoggerFactory.getLogger(ActivityRestController.class);
	
	@Autowired
	private ActivityDetailsService activityDetailsService;

	@PostMapping("/getActivitiesByJob")
	public ResponseEntity getActivitiesByJob(@RequestBody JobDetails job) {
		ResponseEntity response = null;
		//syss
		try {
			List<Activity> activitiesList = activityDetailsService.getActivitiesByJob(job.getId());
			response = new ResponseEntity(activitiesList, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			response = new ResponseEntity(HttpStatus.EXPECTATION_FAILED);
		}

		return response;
	}
	
	@GetMapping("/getJobAndActivities")
	public ResponseEntity getJobAndActivities() {
		ResponseEntity response = null;
		try {
			List<JobActivity> jobActivities = activityDetailsService.getAllJobActivities();
			response = new ResponseEntity(jobActivities, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			response = new ResponseEntity(HttpStatus.EXPECTATION_FAILED);
		}

		return response;
	}

}
