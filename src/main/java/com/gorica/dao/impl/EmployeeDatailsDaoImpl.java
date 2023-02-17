package com.gorica.dao.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.gorica.dao.EmployeeDetailsDao;
import com.gorica.dao.model.Employee;
import com.gorica.dao.repository.BaseRepositoryImpl;

@Repository
public class EmployeeDatailsDaoImpl extends BaseRepositoryImpl<Employee, Long> implements EmployeeDetailsDao {

	private static final Logger logger = LoggerFactory.getLogger(EmployeeDatailsDaoImpl.class);
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5863044219962744595L;

	@Override
	public Employee getEmployeeByEmpNo(String empNo) {
		Employee employee = null;
		try {
			employee =  (Employee) getEntityManager().createQuery("from Employee where empNo=" + empNo)
					.getResultList().stream().findFirst().orElse(null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return employee;
	}

	@Override
	public List<Employee> getActiveEmployees() {
		List<Employee> activeEmpList = getEntityManager().createQuery("from Employee where status is true")
				.getResultList();
		return activeEmpList;
	}

	@Override
	public Employee getSingleEmployeeByEmpNo(String empNo) {
	try {
		Employee emp = (Employee) getEntityManager().createQuery("from Employee where empNo=" + empNo).setMaxResults(1)
				.getSingleResult();
		return emp;
	}catch(Exception e ) {
		return null;
	}
}

}
