package com.gorica.dao;

import java.util.List;

import com.gorica.dao.model.BreakTimings;
import com.gorica.dao.model.JobDetails;
import com.gorica.dao.repository.BaseRepositoryI;

public interface JobDetailsDao extends BaseRepositoryI<JobDetails, Long> {

	public List<JobDetails> getActiveJobs();

	public List<BreakTimings> getAllBreakTimings();

	public List<BreakTimings> getActiveBreakTimings();

	public void saveBreakTimings(BreakTimings breakTime);

	public void updateBreakTimings(BreakTimings breakTime);

	public BreakTimings getBreakTimingsById(Long id);

	public void deleteBreakTimingsById(Long id);

	public JobDetails getJobDetailsByJobNo(Long id);

}
