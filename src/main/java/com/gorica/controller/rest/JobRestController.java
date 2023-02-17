package com.gorica.controller.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.gorica.dao.model.JobDetails;
import com.gorica.service.JobDetailsService;

@RestController
@RequestMapping("/rest/jobs")
@SuppressWarnings({ "rawtypes", "unchecked" })
public class JobRestController {

	private static final Logger logger = LoggerFactory.getLogger(JobRestController.class);
	
	@Autowired
	private JobDetailsService jobDetailsService;

	@GetMapping("/getAllJobs")
	public ResponseEntity getAllJobs() {
		ResponseEntity response = null;
		try {
			logger.info("logger test info");
			List<JobDetails> jobsList = jobDetailsService.getAllJobs();
			response = new ResponseEntity(jobsList, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			response = new ResponseEntity(HttpStatus.EXPECTATION_FAILED);
		}

		return response;
	}

	@GetMapping("/getActiveJobs")
	public ResponseEntity getActiveJobs() {
		ResponseEntity response = null;
		try {
			List<JobDetails> jobsList = jobDetailsService.getActiveJobs();
			response = new ResponseEntity(jobsList, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			response = new ResponseEntity(HttpStatus.EXPECTATION_FAILED);
		}

		return response;
	}

}
