package com.gorica.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gorica.dao.EmployeeDetailsDao;
import com.gorica.dao.model.Employee;
import com.gorica.service.EmployeeDetailsService;

@Service
public class EmployeeDetailsServiceImpl implements EmployeeDetailsService {

	@Autowired
	private EmployeeDetailsDao employeeDetailsDao;

	@Override
	public List<Employee> getAllEmployees() {
		return employeeDetailsDao.findAll();
	}

	@Override
	public Employee getEmployeeById(Long id) {
		return employeeDetailsDao.findOne(id);
	}

	@Override
	public Employee getEmployeeByEmpNo(String empNo) {
		return employeeDetailsDao.getEmployeeByEmpNo(empNo);
	}

	@Override
	@Transactional
	public void saveEmployee(Employee emp) {
		employeeDetailsDao.save(emp);
	}

	@Override
	@Transactional
	public void updateEmployee(Employee emp) {
		employeeDetailsDao.update(emp);
	}

	@Override
	@Transactional
	public void deleteEmployeeById(Long id) {
		employeeDetailsDao.deleteById(id);
	}

	@Override
	public List<Employee> getActiveEmployees() {
		return employeeDetailsDao.getActiveEmployees();
	}

	@Override
	public Employee getSingleEmployeeByEmpNo(String empNo) {
		return employeeDetailsDao.getSingleEmployeeByEmpNo(empNo);
	}

}
