package com.gorica.dao;

import java.util.List;

import com.gorica.dao.model.Employee;
import com.gorica.dao.model.IdleTimeReason;
import com.gorica.dao.model.JobDetails;
import com.gorica.dao.model.ScannedTimeLog;
import com.gorica.dao.model.User;
import com.gorica.dao.repository.BaseRepositoryI;

public interface TimeLogDetailsDao extends BaseRepositoryI<ScannedTimeLog, Long> {

	public List<IdleTimeReason> getIdealTimeResons();

	public ScannedTimeLog getTimeLogDetailsByJobActivityEmpPostDate(Long jobId, Long activitId, Long empId,
			String postDate);

	public ScannedTimeLog getTimeLogDetailsByEmpPostDate(Long empId, String postDate);

	public List<ScannedTimeLog> getScannedTimeLogByPostingDate(String postDate);

	public void saveIdleReason(IdleTimeReason reason);

	public void updateIdleReason(IdleTimeReason reason);

	public IdleTimeReason getIdleTimeReasonById(Long id);

	public void deleteReasonById(Long reasonId);

	public List<ScannedTimeLog> getScannedTimeLogByPostingDateAndUser(String postDate, Long userId);

	public List<ScannedTimeLog> getScannedTimeLogForWeek(String weekDay, String yesterday);

	public List<User> scanningUsersListByPostingDate(String postDate);

	public List<ScannedTimeLog> getEmpScannAction(String postDate, Long empId);

	public List<Employee> getIdleEmployeesByPostingDate(String postDate);

	public List<Employee> getScannedEmployeesByPostingDate(String postDate);

	public List<JobDetails> getScannedJobsByPostingDate(String postDate);

	public List<ScannedTimeLog> getEmpScannActionByJobAndActivity(String postDate, Long empId, Long jobId,
			Long activityId);

	public List<ScannedTimeLog> getEmpScannActionByJob(String postDate, Long empId, Long jobId);
	
	public ScannedTimeLog getTimeLogDetailsByJobEmpPostDate(Long jobId, Long empId, String postDate);
	
	public List<ScannedTimeLog> getScannedTimeLogBetweenDates(String postYstrDate,String postTodayDate);
	
	public ScannedTimeLog getTimeLogDetailsByJobWorkStationEmpPostDate(Long jobId,Long workId, Long empId, String postDate);
	
	public ScannedTimeLog getTimeLogDetailsByJobWorkStationEmpPostDateForNightShift(Long jobId,Long workId, Long empId, String postDate);

	public void removeDuplicateEntries(String postDate,String sixMonthAgoDate);
	
}
