package com.gorica.dao.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.gorica.dao.JobDetailsDao;
import com.gorica.dao.model.BreakTimings;
import com.gorica.dao.model.JobDetails;
import com.gorica.dao.repository.BaseRepositoryImpl;

@Repository("jobDetailsDao")
@SuppressWarnings("unchecked")
public class JobDetailsDaoImpl extends BaseRepositoryImpl<JobDetails, Long> implements JobDetailsDao {

	private static final Logger logger = LoggerFactory.getLogger(JobDetailsDaoImpl.class);
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 9135187051858796432L;

	@Override
	public List<JobDetails> getActiveJobs() {
		List<JobDetails> jobsList = getEntityManager().createQuery("from JobDetails where status is true order by jobNo desc")
				.getResultList();
		return jobsList;
	}

	@Override
	public List<BreakTimings> getAllBreakTimings() {
		List<BreakTimings> breakTimings = getEntityManager().createQuery("from BreakTimings").getResultList();
		return breakTimings;
	}

	@Override
	public List<BreakTimings> getActiveBreakTimings() {
		List<BreakTimings> breakTimings = getEntityManager().createQuery("from BreakTimings where status is true")
				.getResultList();
		return breakTimings;
	}

	@Override
	public void saveBreakTimings(BreakTimings breakTime) {
		getEntityManager().persist(breakTime);
	}

	@Override
	public void updateBreakTimings(BreakTimings breakTime) {
		getEntityManager().merge(breakTime);
	}

	@Override
	public BreakTimings getBreakTimingsById(Long id) {
		return getEntityManager().find(BreakTimings.class, id);
	}

	@Override
	public void deleteBreakTimingsById(Long id) {
		BreakTimings breakTimings = getEntityManager().find(BreakTimings.class, id);
		getEntityManager().remove(breakTimings);
	}

	@Override
	public JobDetails getJobDetailsByJobNo(Long id) {
		try {
			JobDetails jobDetails = (JobDetails) getEntityManager().createQuery("from JobDetails where jobNo=" + id).setMaxResults(1)
					.getSingleResult();
			return jobDetails;
		}catch(Exception e) {
			return null;
		}
	}

}
