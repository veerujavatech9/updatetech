package com.gorica;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import com.gorica.dao.model.Employee;
import com.gorica.dao.model.JobDetails;
import com.gorica.dao.model.User;

@Component("sessionObj")
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class SessionData {

	private boolean isValidLogin;
	private String statusCode;
	private String statusMessage;

	private User user;

	private int scanningUsersCount;
	private int activeJobsCount;
	private int activeEmployeesCount;
	private int idleEmployeesCount;

	private List<User> scanningUsersList;
	private List<Employee> idleEmpsList;
	private List<Employee> scannedEmpList;
	private List<JobDetails> scannedJobList;

	public boolean getIsValidLogin() {
		return isValidLogin;
	}

	public void setValidLogin(boolean isValidLogin) {
		this.isValidLogin = isValidLogin;
	}

	public String getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}

	public String getStatusMessage() {
		return statusMessage;
	}

	public void setStatusMessage(String statusMessage) {
		this.statusMessage = statusMessage;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public int getScanningUsersCount() {
		return scanningUsersCount;
	}

	public void setScanningUsersCount(int scanningUsersCount) {
		this.scanningUsersCount = scanningUsersCount;
	}

	public int getActiveJobsCount() {
		return activeJobsCount;
	}

	public void setActiveJobsCount(int activeJobsCount) {
		this.activeJobsCount = activeJobsCount;
	}

	public int getActiveEmployeesCount() {
		return activeEmployeesCount;
	}

	public void setActiveEmployeesCount(int activeEmployeesCount) {
		this.activeEmployeesCount = activeEmployeesCount;
	}

	public int getIdleEmployeesCount() {
		return idleEmployeesCount;
	}

	public void setIdleEmployeesCount(int idleEmployeesCount) {
		this.idleEmployeesCount = idleEmployeesCount;
	}

	public List<User> getScanningUsersList() {
		return scanningUsersList;
	}

	public void setScanningUsersList(List<User> scanningUsersList) {
		this.scanningUsersList = scanningUsersList;
	}

	public List<Employee> getIdleEmpsList() {
		return idleEmpsList;
	}

	public void setIdleEmpsList(List<Employee> idleEmpsList) {
		this.idleEmpsList = idleEmpsList;
	}

	public List<Employee> getScannedEmpList() {
		return scannedEmpList;
	}

	public void setScannedEmpList(List<Employee> scannedEmpList) {
		this.scannedEmpList = scannedEmpList;
	}

	public List<JobDetails> getScannedJobList() {
		return scannedJobList;
	}

	public void setScannedJobList(List<JobDetails> scannedJobList) {
		this.scannedJobList = scannedJobList;
	}

	@Override
	public String toString() {
		return "SessionData [isValidLogin=" + isValidLogin + ", statusCode=" + statusCode + ", statusMessage="
				+ statusMessage + ", user=" + user + ", scanningUsersCount=" + scanningUsersCount + ", activeJobsCount="
				+ activeJobsCount + ", activeEmployeesCount=" + activeEmployeesCount + ", idleEmployeesCount="
				+ idleEmployeesCount + ", scanningUsersList=" + scanningUsersList + ", idleEmpsList=" + idleEmpsList
				+ ", scannedEmpList=" + scannedEmpList + ", scannedJobList=" + scannedJobList + "]";
	}

}
