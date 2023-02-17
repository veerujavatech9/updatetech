package com.gorica.service;

import java.util.List;

import com.gorica.dao.model.Employee;

public interface EmployeeDetailsService {
	
	public List<Employee> getAllEmployees();
	
	public Employee getEmployeeById(Long id);
	
	public Employee getEmployeeByEmpNo(String empNo);
	
	public void saveEmployee(Employee emp);
	
	public void updateEmployee(Employee emp);
	
	public void deleteEmployeeById(Long id);
	
	public List<Employee> getActiveEmployees();
	
	public Employee getSingleEmployeeByEmpNo(String empNo);

}
