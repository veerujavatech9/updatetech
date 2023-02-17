package com.gorica.dao.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "final_activity_details")
public class FinalActivityDetails {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@OneToOne()
	@JoinColumn(name = "emp_id")
	private Employee employee;
	
	@OneToOne()
	@JoinColumn(name = "job_id")
	private JobDetails job;

	@OneToOne()
	@JoinColumn(name = "activity_id")
	private Activity activity;
	
	@Column(name = "activity_hours")
	private Double activityHrs;
	
	@Column(name = "ideal_hours")
	private Double idealHrs;
	
	@OneToOne()
	@JoinColumn(name = "ideal_reason")
	private IdleTimeReason idealReason;
	
	@Column(name = "posting_date")
	private Date postingDate;
	
	@Column(name = "created_date")
	private Date createdDate;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public JobDetails getJob() {
		return job;
	}

	public void setJob(JobDetails job) {
		this.job = job;
	}

	public Activity getActivity() {
		return activity;
	}

	public void setActivity(Activity activity) {
		this.activity = activity;
	}

	public Double getActivityHrs() {
		return activityHrs;
	}

	public void setActivityHrs(Double activityHrs) {
		this.activityHrs = activityHrs;
	}

	public Double getIdealHrs() {
		return idealHrs;
	}

	public void setIdealHrs(Double idealHrs) {
		this.idealHrs = idealHrs;
	}

	public IdleTimeReason getIdealReason() {
		return idealReason;
	}

	public void setIdealReason(IdleTimeReason idealReason) {
		this.idealReason = idealReason;
	}

	public Date getPostingDate() {
		return postingDate;
	}

	public void setPostingDate(Date postingDate) {
		this.postingDate = postingDate;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	@Override
	public String toString() {
		return "FinalActivityDetails [id=" + id + ", employee=" + employee + ", job=" + job + ", activity=" + activity
				+ ", activityHrs=" + activityHrs + ", idealHrs=" + idealHrs + ", idealReason=" + idealReason
				+ ", postingDate=" + postingDate + ", createdDate=" + createdDate + "]";
	}

	
}
