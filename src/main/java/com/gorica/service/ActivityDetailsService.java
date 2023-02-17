package com.gorica.service;

import java.util.List;

import com.gorica.dao.model.Activity;
import com.gorica.dao.model.JobActivity;

public interface ActivityDetailsService {

	public List<Activity> getActivitiesByJob(Long jobId);

	public void saveAtivity(Activity activity);

	public void updateActivity(Activity activity);

	public Activity getActivityById(Long id);

	public List<Activity> getAllActivities();
	
	public void deleteActivitiesByJob(Long jobId);
	
	public void saveJobActivity(JobActivity jobActivity);
	
	public List<JobActivity> getAllJobActivities();
	
	public void deleteAtivityById(Long activityId);
	
	public void removeAtivityById(Long activityId);

}
