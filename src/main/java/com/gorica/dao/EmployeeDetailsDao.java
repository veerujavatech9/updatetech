package com.gorica.dao;

import java.util.List;

import com.gorica.dao.model.Employee;
import com.gorica.dao.repository.BaseRepositoryI;

public interface EmployeeDetailsDao extends BaseRepositoryI<Employee, Long> {

	public Employee getEmployeeByEmpNo(String empNo);
	
	public List<Employee> getActiveEmployees();
	
	public Employee getSingleEmployeeByEmpNo(String empNO);

}
