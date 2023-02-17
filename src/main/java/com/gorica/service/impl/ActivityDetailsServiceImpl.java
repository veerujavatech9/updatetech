package com.gorica.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gorica.dao.ActivityDetailsDao;
import com.gorica.dao.model.Activity;
import com.gorica.dao.model.JobActivity;
import com.gorica.service.ActivityDetailsService;

@Service
public class ActivityDetailsServiceImpl implements ActivityDetailsService {

	@Autowired
	private ActivityDetailsDao activityDetailsDao;

	@Override
	public List<Activity> getActivitiesByJob(Long jobId) {
		return activityDetailsDao.getActivitiesByJob(jobId);
	}

	@Override
	@Transactional
	public void saveAtivity(Activity activity) {
		activityDetailsDao.save(activity);
	}

	@Override
	@Transactional
	public void updateActivity(Activity activity) {
		activityDetailsDao.update(activity);
	}

	@Override
	public Activity getActivityById(Long id) {
		return activityDetailsDao.findOne(id);
	}

	@Override
	public List<Activity> getAllActivities() {
		return activityDetailsDao.findAll();
	}

	@Override
	@Transactional
	public void deleteActivitiesByJob(Long jobId) {
		activityDetailsDao.deleteActivitiesByJob(jobId);
	}

	@Override
	@Transactional
	public void saveJobActivity(JobActivity jobActivity) {
		activityDetailsDao.saveJobActivity(jobActivity);
	}

	@Override
	public List<JobActivity> getAllJobActivities() {
		return activityDetailsDao.getAllJobActivities();
	}

	@Override
	@Transactional
	public void deleteAtivityById(Long activityId) {
		activityDetailsDao.deleteById(activityId);
	}

	@Override
	@Transactional
	public void removeAtivityById(Long activityId) {
		activityDetailsDao.removeAtivityById(activityId);
	}

}
