package com.gorica.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gorica.dao.JobDetailsDao;
import com.gorica.dao.model.BreakTimings;
import com.gorica.dao.model.JobDetails;
import com.gorica.service.JobDetailsService;

@Service
public class JobDetailsServiceImpl implements JobDetailsService {

	@Autowired
	private JobDetailsDao jobDetailsDao;

	@Override
	public List<JobDetails> getAllJobs() {
		return jobDetailsDao.findAll();
	}

	@Override
	public List<JobDetails> getActiveJobs() {
		return jobDetailsDao.getActiveJobs();
	}

	@Override
	@Transactional
	public void saveJob(JobDetails job) {
		jobDetailsDao.save(job);
	}

	@Override
	@Transactional
	public void updateJob(JobDetails job) {
		jobDetailsDao.update(job);
	}

	@Override
	public JobDetails getJobDetailsById(Long id) {
		return jobDetailsDao.findOne(id);
	}

	@Override
	@Transactional
	public void deleteJobById(Long jobId) {
		jobDetailsDao.deleteById(jobId);
	}

	@Override
	public List<BreakTimings> getAllBreakTimings() {
		return jobDetailsDao.getAllBreakTimings();
	}

	@Override
	public List<BreakTimings> getActiveBreakTimings() {
		return jobDetailsDao.getActiveBreakTimings();
	}

	@Override
	@Transactional
	public void saveBreakTimings(BreakTimings breakTime) {
		jobDetailsDao.saveBreakTimings(breakTime);
	}

	@Override
	@Transactional
	public void updateBreakTimings(BreakTimings breakTime) {
		jobDetailsDao.updateBreakTimings(breakTime);
	}

	@Override
	public BreakTimings getBreakTimingsById(Long id) {
		return jobDetailsDao.getBreakTimingsById(id);
	}

	@Override
	@Transactional
	public void deleteBreakTimingsById(Long id) {
		jobDetailsDao.deleteBreakTimingsById(id);
	}

	@Override
	public JobDetails getJobDetailsByJobNo(Long id) {
		return jobDetailsDao.getJobDetailsByJobNo(id);
	}

}
