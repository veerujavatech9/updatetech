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
@Table(name = "scanned_time_log")
public class ScannedTimeLog {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@OneToOne()
	@JoinColumn(name = "job_id")
	private JobDetails job;

	@OneToOne()
	@JoinColumn(name = "activity_id")
	private Activity activity;

	@OneToOne()
	@JoinColumn(name = "emp_id")
	private Employee employee;

	@Column(name = "posting_date")
	private Date postingDate;

	@Column(name = "check_in_time")
	private Date checkInTime;

	@Column(name = "check_out_time")
	private Date checkOutTime;

	@Column(name = "activity_hours")
	private String activityHrs;

	@Column(name = "ideal_in_time")
	private Date idealInTime;

	@Column(name = "ideal_out_time")
	private Date idealOutTime;

	@Column(name = "ideal_hours")
	private String idealHrs;

	@OneToOne()
	@JoinColumn(name = "ideal_reason")
	private IdleTimeReason idealReason;
	
	@OneToOne()
	@JoinColumn(name = "user_id")
	private User user;

	@Column(name = "created_date")
	private Date createdDate;
	
	private boolean scanned;

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

	public Date getPostingDate() {
		return postingDate;
	}

	public void setPostingDate(Date postingDate) {
		this.postingDate = postingDate;
	}

	public Date getCheckInTime() {
		return checkInTime;
	}

	public void setCheckInTime(Date checkInTime) {
		this.checkInTime = checkInTime;
	}

	public Date getCheckOutTime() {
		return checkOutTime;
	}

	public void setCheckOutTime(Date checkOutTime) {
		this.checkOutTime = checkOutTime;
	}

	public String getActivityHrs() {
		return activityHrs;
	}

	public void setActivityHrs(String activityHrs) {
		this.activityHrs = activityHrs;
	}

	public Date getIdealInTime() {
		return idealInTime;
	}

	public void setIdealInTime(Date idealInTime) {
		this.idealInTime = idealInTime;
	}

	public Date getIdealOutTime() {
		return idealOutTime;
	}

	public void setIdealOutTime(Date idealOutTime) {
		this.idealOutTime = idealOutTime;
	}

	public String getIdealHrs() {
		return idealHrs;
	}

	public void setIdealHrs(String idealHrs) {
		this.idealHrs = idealHrs;
	}

	public IdleTimeReason getIdealReason() {
		return idealReason;
	}

	public void setIdealReason(IdleTimeReason idealReason) {
		this.idealReason = idealReason;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public boolean isScanned() {
		return scanned;
	}

	public void setScanned(boolean scanned) {
		this.scanned = scanned;
	}

	@Override
	public String toString() {
		return "ScannedTimeLog [id=" + id + ", job=" + job + ", activity=" + activity + ", employee=" + employee
				+ ", postingDate=" + postingDate + ", checkInTime=" + checkInTime + ", checkOutTime=" + checkOutTime
				+ ", activityHrs=" + activityHrs + ", idealInTime=" + idealInTime + ", idealOutTime=" + idealOutTime
				+ ", idealHrs=" + idealHrs + ", idealReason=" + idealReason + ", user=" + user + ", createdDate="
				+ createdDate + ", scanned=" + scanned + "]";
	}

}
