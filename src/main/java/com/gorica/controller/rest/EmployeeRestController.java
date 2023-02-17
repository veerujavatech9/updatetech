package com.gorica.controller.rest;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gorica.dao.model.Employee;
import com.gorica.service.EmployeeDetailsService;

@RestController
@RequestMapping("/rest/employee")
@SuppressWarnings({ "rawtypes", "unchecked" })
public class EmployeeRestController {

	private static final Logger logger = LoggerFactory.getLogger(EmployeeRestController.class);
	
	@Autowired
	private EmployeeDetailsService employeeDetailsService;

	@GetMapping("/getAllEmployees")
	public ResponseEntity getAllEmployees() {
		ResponseEntity response = null;
		try {
			List<Employee> empList = employeeDetailsService.getAllEmployees();
			response = new ResponseEntity(empList, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			response = new ResponseEntity(HttpStatus.EXPECTATION_FAILED);
		}

		return response;
	}

	@PostMapping("/getEmployeeById")
	public ResponseEntity getEmployeeById(@RequestBody Employee emp) {
		ResponseEntity response = null;
		try {
			Employee employee = employeeDetailsService.getEmployeeById(emp.getId());
			response = new ResponseEntity(employee, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			response = new ResponseEntity(HttpStatus.EXPECTATION_FAILED);
		}

		return response;
	}

	@SuppressWarnings("removal")
	@PostMapping("/getEmployeeByEmpNo")
	public ResponseEntity getEmployeeByEmpNo(@RequestBody Employee emp) {
		ResponseEntity response = null;
		try {
			Employee employee = employeeDetailsService.getEmployeeByEmpNo(emp.getEmpNo());
			if(employee==null) {
				emp.setId(new Long(0));
				emp.setEmpName("");
				emp.setErrorMsg("");
				response = new ResponseEntity(emp, HttpStatus.OK);
			}else {
				employee.setCurrentDateTime(new Date());
				response = new ResponseEntity(employee, HttpStatus.OK);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			response = new ResponseEntity(HttpStatus.EXPECTATION_FAILED);
		}

		return response;
	}

}
