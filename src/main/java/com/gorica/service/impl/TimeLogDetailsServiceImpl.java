package com.gorica.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gorica.dao.TimeLogDetailsDao;
import com.gorica.dao.model.Employee;
import com.gorica.dao.model.IdleTimeReason;
import com.gorica.dao.model.JobDetails;
import com.gorica.dao.model.ScannedTimeLog;
import com.gorica.dao.model.User;
import com.gorica.service.TimeLogDetailsService;

@Service
public class TimeLogDetailsServiceImpl implements TimeLogDetailsService {

	@Autowired
	private TimeLogDetailsDao timeLogDetailsDao;

	@Override
	@Transactional
	public void saveTimeLog(ScannedTimeLog timeLog) {
		timeLogDetailsDao.save(timeLog);
	}

	@Override
	@Transactional
	public void updateTimeLog(ScannedTimeLog timeLog) {
		timeLogDetailsDao.update(timeLog);
	}

	@Override
	public List<IdleTimeReason> getIdealTimeResons() {
		return timeLogDetailsDao.getIdealTimeResons();
	}

	@Override
	public ScannedTimeLog getTimeLogDetailsByJobActivityEmpPostDate(Long jobId, Long activitId, Long empId,
			String postDate) {
		return timeLogDetailsDao.getTimeLogDetailsByJobActivityEmpPostDate(jobId, activitId, empId, postDate);
	}

	@Override
	public ScannedTimeLog getTimeLogDetailsByEmpPostDate(Long empId, String postDate) {
		return timeLogDetailsDao.getTimeLogDetailsByEmpPostDate(empId, postDate);
	}

	@Override
	public List<ScannedTimeLog> getScannedTimeLogByPostingDate(String postDate) {
		return timeLogDetailsDao.getScannedTimeLogByPostingDate(postDate);
	}

	@Override
	@Transactional
	public void saveIdleReason(IdleTimeReason reason) {
		timeLogDetailsDao.saveIdleReason(reason);
	}

	@Override
	@Transactional
	public void updateIdleReason(IdleTimeReason reason) {
		timeLogDetailsDao.updateIdleReason(reason);
	}

	@Override
	public IdleTimeReason getIdleTimeReasonById(Long id) {
		return timeLogDetailsDao.getIdleTimeReasonById(id);
	}

	@Override
	@Transactional
	public void deleteReasonById(Long reasonId) {
		timeLogDetailsDao.deleteReasonById(reasonId);
	}

	@Override
	public List<ScannedTimeLog> getScannedTimeLogByPostingDateAndUser(String postDate, Long userId) {
		return timeLogDetailsDao.getScannedTimeLogByPostingDateAndUser(postDate, userId);
	}

	@Override
	public List<ScannedTimeLog> getScannedTimeLogForWeek(String weekDay, String yesterday) {
		return timeLogDetailsDao.getScannedTimeLogForWeek(weekDay, yesterday);
	}

	@Override
	public List<User> scanningUsersListByPostingDate(String postDate) {
		return timeLogDetailsDao.scanningUsersListByPostingDate(postDate);
	}

	@Override
	public List<ScannedTimeLog> getEmpScannAction(String postDate, Long empId) {
		return timeLogDetailsDao.getEmpScannAction(postDate, empId);
	}

	@Override
	public List<Employee> getIdleEmployeesByPostingDate(String postDate) {
		return timeLogDetailsDao.getIdleEmployeesByPostingDate(postDate);
	}

	@Override
	public List<Employee> getScannedEmployeesByPostingDate(String postDate) {
		return timeLogDetailsDao.getScannedEmployeesByPostingDate(postDate);
	}

	@Override
	public List<JobDetails> getScannedJobsByPostingDate(String postDate) {
		return timeLogDetailsDao.getScannedJobsByPostingDate(postDate);
	}

	@Override
	public List<ScannedTimeLog> getEmpScannActionByJobAndActivity(String postDate, Long empId, Long jobId,
			Long activityId) {
		return timeLogDetailsDao.getEmpScannActionByJobAndActivity(postDate, empId, jobId, activityId);
	}

	@Override
	public List<ScannedTimeLog> getEmpScannActionByJob(String postDate, Long empId, Long jobId) {
		return timeLogDetailsDao.getEmpScannActionByJob(postDate, empId, jobId);
	}

	@Override
	public ScannedTimeLog getTimeLogDetailsByJobEmpPostDate(Long jobId, Long empId, String postDate) {
		return timeLogDetailsDao.getTimeLogDetailsByJobEmpPostDate(jobId, empId, postDate);
	}

	@Override
	public List<ScannedTimeLog> getScannedTimeLogBetweenDates(String postYstrDate, String postTodayDate) {
		return timeLogDetailsDao.getScannedTimeLogBetweenDates(postYstrDate, postTodayDate);
	}
	
	@Override
	public ScannedTimeLog getTimeLogDetailsByJobWorkStationEmpPostDate(Long jobId, Long workId, Long empId, String postDate) {
		return timeLogDetailsDao.getTimeLogDetailsByJobWorkStationEmpPostDate(jobId, workId, empId, postDate);
	}
	
	@Override
	public ScannedTimeLog getTimeLogDetailsByJobWorkStationEmpPostDateForNightShift(Long jobId, Long workId, Long empId, String postDate) {
		return timeLogDetailsDao.getTimeLogDetailsByJobWorkStationEmpPostDateForNightShift(jobId, workId, empId, postDate);
	}

	@Override
	public void removeDuplicateEntries(String postDate, String sixMonthAgoDate) {
		 timeLogDetailsDao.removeDuplicateEntries(postDate, sixMonthAgoDate);
	}

}
