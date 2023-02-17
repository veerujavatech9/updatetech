package com.gorica.dao.impl;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Query;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.gorica.dao.TimeLogDetailsDao;
import com.gorica.dao.model.Employee;
import com.gorica.dao.model.IdleTimeReason;
import com.gorica.dao.model.JobDetails;
import com.gorica.dao.model.ScannedTimeLog;
import com.gorica.dao.model.User;
import com.gorica.dao.repository.BaseRepositoryImpl;

@Repository
@SuppressWarnings("unchecked")
public class TimeLogDetailsDaoImpl extends BaseRepositoryImpl<ScannedTimeLog, Long> implements TimeLogDetailsDao {

	private static final Logger logger = LoggerFactory.getLogger(TimeLogDetailsDaoImpl.class);
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8710353762757207531L;

	@Override
	public List<IdleTimeReason> getIdealTimeResons() {
		List<IdleTimeReason> reasonList = getEntityManager().createQuery("from IdleTimeReason").getResultList();
		return reasonList;
	}

	@Override
	public ScannedTimeLog getTimeLogDetailsByJobActivityEmpPostDate(Long jobId, Long activitId, Long empId,
			String postDate) {
		List<ScannedTimeLog> timeLogList = getEntityManager().createQuery("from ScannedTimeLog where job=" + jobId
				+ " and activity=" + activitId + " and employee=" + empId + " and DATE(checkInTime)='" + postDate + "'")
				.getResultList();
		ScannedTimeLog timeLog = null;
		if (timeLogList != null && timeLogList.size() != 0)
			timeLog = timeLogList.get(0);
		return timeLog;
	}

	@Override
	public ScannedTimeLog getTimeLogDetailsByEmpPostDate(Long empId, String postDate) {
		List<ScannedTimeLog> timeLogList = getEntityManager().createQuery("from ScannedTimeLog where employee=" + empId
				+ " and DATE(checkInTime)='" + postDate + "' and (checkOutTime is null)").getResultList();
		ScannedTimeLog timeLog = null;
		if (timeLogList != null && timeLogList.size() != 0)
			timeLog = timeLogList.get(0);
		return timeLog;
	}

	@Override
	public List<ScannedTimeLog> getScannedTimeLogByPostingDate(String postDate) {
		List<ScannedTimeLog> timeLogList = getEntityManager()
				.createQuery("from ScannedTimeLog where DATE(checkInTime)='" + postDate + "' order by checkInTime")
				.getResultList();
		return timeLogList;
	}

	@Override
	public void saveIdleReason(IdleTimeReason reason) {
		getEntityManager().persist(reason);
	}

	@Override
	public void updateIdleReason(IdleTimeReason reason) {
		getEntityManager().merge(reason);
	}

	@Override
	public IdleTimeReason getIdleTimeReasonById(Long id) {
		IdleTimeReason reason = (IdleTimeReason) getEntityManager().createQuery("from IdleTimeReason where id=" + id)
				.getSingleResult();
		return reason;
	}

	@Override
	public void deleteReasonById(Long reasonId) {
		Query query = getEntityManager().createQuery("delete from IdleTimeReason where id=" + reasonId);
		query.executeUpdate();

	}

	@Override
	public List<ScannedTimeLog> getScannedTimeLogByPostingDateAndUser(String postDate, Long userId) {
		List<ScannedTimeLog> timeLogList = getEntityManager()
				.createQuery("from ScannedTimeLog where DATE(checkInTime)='" + postDate + "' and user=" + userId
						+ " order by checkInTime")
				.getResultList();
		return timeLogList;
	}

	@Override
	public List<ScannedTimeLog> getScannedTimeLogForWeek(String weekDay, String yesterday) {
		List<ScannedTimeLog> timeLogList = getEntityManager()
				.createQuery("from ScannedTimeLog where DATE(checkInTime) BETWEEN'" + weekDay + "' and '" + yesterday
						+ "' and activityHrs!=null and idealHrs!=null order by checkInTime")
				.getResultList();
		return timeLogList;
	}

	@Override
	public List<User> scanningUsersListByPostingDate(String postDate) {
		List<User> usersList = getEntityManager()
				.createQuery(
						"select distinct(s.user) from ScannedTimeLog s where DATE(s.checkInTime)='" + postDate + "'")
				.getResultList();
		return usersList;
	}

	@Override
	public List<ScannedTimeLog> getEmpScannAction(String postDate, Long empId) {
		List<ScannedTimeLog> timeLogList = getEntityManager()
				.createQuery("from ScannedTimeLog where DATE(checkInTime)='" + postDate + "' and employee=" + empId
						+ " and checkInTime IS NOT NULL and (checkOutTime IS NULL or idealOutTime IS NULL)")
				.getResultList();
		return timeLogList;
	}

	@Override
	public List<Employee> getIdleEmployeesByPostingDate(String postDate) {
		List<Employee> empList = getEntityManager()
				.createQuery("select distinct(s.employee) from ScannedTimeLog s where DATE(s.checkInTime)='" + postDate
						+ "' and s.idealInTime IS NOT NULL")
				.getResultList();
		return empList;
	}

	@Override
	public List<Employee> getScannedEmployeesByPostingDate(String postDate) {
		List<Employee> empList = getEntityManager().createQuery(
				"select distinct(s.employee) from ScannedTimeLog s where DATE(s.checkInTime)='" + postDate + "'")
				.getResultList();
		return empList;
	}

	@Override
	public List<JobDetails> getScannedJobsByPostingDate(String postDate) {
		List<JobDetails> jobList = getEntityManager()
				.createQuery(
						"select distinct(s.job) from ScannedTimeLog s where DATE(s.checkInTime)='" + postDate + "'")
				.getResultList();
		return jobList;
	}

	@Override
	public List<ScannedTimeLog> getEmpScannActionByJobAndActivity(String postDate, Long empId, Long jobId,
			Long activityId) {
		List<ScannedTimeLog> timeLogList = getEntityManager()
				.createQuery("from ScannedTimeLog where DATE(checkInTime)='" + postDate + "' and employee=" + empId
						+ " and job=" + jobId + " and activity=" + activityId
						+ " and checkInTime IS NOT NULL and (checkOutTime IS NULL or idealOutTime IS NULL)")
				.getResultList();
		return timeLogList;
	}

	@Override
	public List<ScannedTimeLog> getEmpScannActionByJob(String postDate, Long empId, Long jobId) {
		List<ScannedTimeLog> timeLogList = getEntityManager()
				.createQuery("from ScannedTimeLog where DATE(checkInTime)='" + postDate + "' and employee=" + empId
						+ " and job=" + jobId
						+ " and checkInTime IS NOT NULL and (checkOutTime IS NULL or idealOutTime IS NULL)")
				.getResultList();
		return timeLogList;
	}

	@Override
	public ScannedTimeLog getTimeLogDetailsByJobEmpPostDate(Long jobId, Long empId, String postDate) {
		List<ScannedTimeLog> timeLogList = getEntityManager().createQuery("from ScannedTimeLog where job=" + jobId
				+ " and employee=" + empId + " and DATE(checkInTime)='" + postDate + "'").getResultList();
		ScannedTimeLog timeLog = null;
		if (timeLogList != null && timeLogList.size() != 0)
			timeLog = timeLogList.get(0);
		return timeLog;
	}

	@Override
	public List<ScannedTimeLog> getScannedTimeLogBetweenDates(String postYstrDate, String postTodayDate) {
		/*
		 * List<ScannedTimeLog> timeLogList = getEntityManager()
		 * .createQuery("from ScannedTimeLog where checkInTime BETWEEN '" + postYstrDate
		 * + "' and '" + postTodayDate + "' order by checkInTime") .getResultList();
		 */

		List<ScannedTimeLog> timeLogList = getEntityManager().createQuery("from ScannedTimeLog where checkInTime <='"
				+ postTodayDate + "' and activityHrs is null  order by checkInTime").getResultList();
		return timeLogList;
	}

	@Override
	public ScannedTimeLog getTimeLogDetailsByJobWorkStationEmpPostDate(Long jobId, Long workId, Long empId,
			String postDate) {
		List<ScannedTimeLog> timeLogList = getEntityManager().createQuery("from ScannedTimeLog where job=" + jobId
				+ "and activity=" + workId + " and employee=" + empId + " and DATE(checkInTime)='" + postDate + "'")
				.getResultList();
		ScannedTimeLog timeLog = null;
		if (timeLogList != null && timeLogList.size() != 0)
			timeLog = timeLogList.get(0);
		return timeLog;
	}

	@Override
	public ScannedTimeLog getTimeLogDetailsByJobWorkStationEmpPostDateForNightShift(Long jobId, Long workId, Long empId,
			String postDate) {

		LocalDate yesterday = LocalDate.now().minusDays(1L);
		postDate = yesterday.toString() + " 18:30:00";

		List<ScannedTimeLog> timeLogList = getEntityManager().createQuery("from ScannedTimeLog where job=" + jobId
				+ "and activity=" + workId + " and employee=" + empId + " and checkInTime >='" + postDate + "'")
				.getResultList();

		ScannedTimeLog timeLog = null;
		if (timeLogList != null && timeLogList.size() != 0)
			timeLog = timeLogList.get(0);
		return timeLog;
	}

	
	@Override
	@Transactional()
	public void removeDuplicateEntries(String postDate, String sixMonthAgoDate) {

		List<ScannedTimeLog> timeLogList = getEntityManager()
				.createQuery("from ScannedTimeLog  group by employee,activity,job,checkInTime\r\n"
						+ "having count(*) >=2\r\n" + "order by checkInTime desc")
				.getResultList();

		timeLogList.forEach(ScannedTimeLog -> {

			List<ScannedTimeLog> list = null;

			boolean flag=false;
			if (ScannedTimeLog.getJob() != null) {

				list = getEntityManager().createQuery("from ScannedTimeLog where  employee="
						+ ScannedTimeLog.getEmployee().getId() + " and activity ="
						+ ScannedTimeLog.getActivity().getId() + " and job =" + ScannedTimeLog.getJob().getId()
						+ " and checkInTime ='" + ScannedTimeLog.getCheckInTime() + "'").getResultList();
				
				flag=true;
			} else {
				list = getEntityManager()
						.createQuery("from ScannedTimeLog where  employee=" + ScannedTimeLog.getEmployee().getId()
								+ " and activity =" + ScannedTimeLog.getActivity().getId() + " and checkInTime ='"
								+ ScannedTimeLog.getCheckInTime() + "'")
						.getResultList();
			}

			if (list != null && list.size() > 1) {
				
				Query query  = null;
				if(flag) {
					 query = getEntityManager()
							.createQuery("delete from ScannedTimeLog where  checkOutTime = NULL and employee="
									+ ScannedTimeLog.getEmployee().getId() + " and job ="
									+ ScannedTimeLog.getJob().getId() + " and activity ="
									+ ScannedTimeLog.getActivity().getId() + " and checkInTime ='"
									+ ScannedTimeLog.getCheckInTime() + "'");
					
				}else {
					 query = getEntityManager()
							.createQuery("delete from ScannedTimeLog where  checkOutTime = NULL and employee="
									+ ScannedTimeLog.getEmployee().getId() + " and activity ="
									+ ScannedTimeLog.getActivity().getId() + " and checkInTime ='"
									+ ScannedTimeLog.getCheckInTime() + "'");
				}
				

				

				boolean isAllNullPresent = list.stream().allMatch(timeLog -> timeLog.getCheckOutTime() == null);
				if (isAllNullPresent) {
					getEntityManager().persist(list.get(0));
				}
				boolean isNotNullPresent = list.stream().allMatch(timeLog -> timeLog.getCheckOutTime() != null);
				if(isNotNullPresent) {
					query = getEntityManager()
							.createQuery("delete from ScannedTimeLog where employee="
									+ ScannedTimeLog.getEmployee().getId() + " and checkOutTime ='"
									+ ScannedTimeLog.getCheckOutTime() + "' and activity ="
									+ ScannedTimeLog.getActivity().getId() + " and checkInTime ='"
									+ ScannedTimeLog.getCheckInTime() + "'");
				}
				query.executeUpdate();
			}
		});
	}

}
