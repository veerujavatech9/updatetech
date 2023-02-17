package com.gorica.dao.impl;

import java.util.List;

import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.gorica.controller.web.UserController;
import com.gorica.dao.ActivityDetailsDao;
import com.gorica.dao.model.Activity;
import com.gorica.dao.model.JobActivity;
import com.gorica.dao.repository.BaseRepositoryImpl;

@Repository
@SuppressWarnings("unchecked")
public class ActivityDetailsDaoImpl extends BaseRepositoryImpl<Activity, Long> implements ActivityDetailsDao {

	private static final Logger logger = LoggerFactory.getLogger(ActivityDetailsDaoImpl.class);
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2420046887749653410L;

	@Override
	public List<Activity> getActivitiesByJob(Long jobId) {
		List<Activity> activitiesList = getEntityManager().createQuery(
				"from Activity where id in(select activity from JobActivity where job=" + jobId + ") and status=true")
				.getResultList();
		return activitiesList;
	}

	@Override
	public void deleteActivitiesByJob(Long jobId) {
		Query query = getEntityManager().createQuery("delete from JobActivity where job=" + jobId);
		query.executeUpdate();

	}

	@Override
	public void saveJobActivity(JobActivity jobActivity) {
		getEntityManager().persist(jobActivity);
	}

	@Override
	public List<JobActivity> getAllJobActivities() {
		List<JobActivity> jobActivitiesList = getEntityManager()
				.createQuery("from JobActivity where job in(select id from JobDetails where  status is true) and status is true")
				.getResultList();
		return jobActivitiesList;
	}

	@Override
	public void removeAtivityById(Long activityId) {
		Query activityQuery = getEntityManager().createQuery("update Activity set status=false where id=" + activityId);
		Query jobActivityQuery = getEntityManager()
				.createQuery("update JobActivity set status=false where activity=" + activityId);
		activityQuery.executeUpdate();
		jobActivityQuery.executeUpdate();
	}

}
