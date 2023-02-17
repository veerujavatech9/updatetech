package com.gorica.service;

import java.util.List;

import com.gorica.dao.model.BreakTimings;
import com.gorica.dao.model.JobDetails;

public interface JobDetailsService {

	public List<JobDetails> getAllJobs();

	public List<JobDetails> getActiveJobs();

	public void saveJob(JobDetails job);

	public void updateJob(JobDetails job);

	public JobDetails getJobDetailsById(Long id);
	
	public void deleteJobById(Long jobId);
	
	
	public List<BreakTimings> getAllBreakTimings();

	public List<BreakTimings> getActiveBreakTimings();

	public void saveBreakTimings(BreakTimings breakTime);

	public void updateBreakTimings(BreakTimings breakTime);

	public BreakTimings getBreakTimingsById(Long id);
	
	public void deleteBreakTimingsById(Long id);
	
	public JobDetails getJobDetailsByJobNo(Long id);

}
