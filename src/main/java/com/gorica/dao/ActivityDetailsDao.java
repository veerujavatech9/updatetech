package com.gorica.dao;

import java.util.List;

import com.gorica.dao.model.Activity;
import com.gorica.dao.model.JobActivity;
import com.gorica.dao.repository.BaseRepositoryI;

public interface ActivityDetailsDao extends BaseRepositoryI<Activity, Long> {

	public List<Activity> getActivitiesByJob(Long jobId);

	public void deleteActivitiesByJob(Long jobId);

	public void saveJobActivity(JobActivity jobActivity);
	
	public List<JobActivity> getAllJobActivities();
	
	public void removeAtivityById(Long activityId);

}
