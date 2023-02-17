package com.gorica.controller.web;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gorica.SessionData;
import com.gorica.controller.rest.ActivityRestController;
import com.gorica.dao.model.Employee;
import com.gorica.service.EmployeeDetailsService;

@Controller
@RequestMapping("/emp")
public class EmployeeController {

	private static final Logger logger = LoggerFactory.getLogger(EmployeeController.class);
	
	@Autowired
	private SessionData sessionobj;

	@Autowired
	private EmployeeDetailsService empService;

	@GetMapping("/createEmp")
	public String createEmployee(ModelMap model) {
		if (sessionobj != null && sessionobj.getIsValidLogin()) {
			try {
				Employee emp = new Employee();
				model.put("emp", emp);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return "createEmployee";
		} else {
			return "redirect:/";
		}
	}

	@PostMapping("/saveEmp")
	public String saveEmployee(@ModelAttribute Employee emp, BindingResult result, ModelMap map) {
		if (sessionobj != null && sessionobj.getIsValidLogin()) {
			try {

				String empNo= emp.getEmpNo();
				Employee employee = empService.getSingleEmployeeByEmpNo(empNo);
				
				if(employee!=null) {
					emp.setErrorMsg(" is already Exist");
					map.put("emp", emp);
					return "createEmployee";
				}
				emp.setCreatedDate(new Date());
				System.out.println("emp{}=" + emp);
				empService.saveEmployee(emp);
				return "redirect:/emp/allEmps";

			} catch (Exception e) {
				e.printStackTrace();
				return "redirect:/";
			}
		} else
			return "redirect:/";
	}

	@GetMapping("editEmp/{id}")
	public String editEmp(@PathVariable Long id, ModelMap model) {
		if (sessionobj != null && sessionobj.getIsValidLogin()) {
			try {
				Employee emp = empService.getEmployeeById(id);
				model.put("emp", emp);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return "editEmpPage";
		} else {
			return "redirect:/";
		}
	}

	@PostMapping("/updateEmp")
	public String updateEmp(@ModelAttribute Employee emp, BindingResult result, ModelMap map) {
		if (sessionobj != null && sessionobj.getIsValidLogin()) {
			try {
				emp.setCreatedDate(new Date());
				empService.updateEmployee(emp);
				return "redirect:/emp/allEmps";

			} catch (Exception e) {
				e.printStackTrace();
				return "redirect:/";
			}
		} else
			return "redirect:/";
	}

	@GetMapping("/allEmps")
	public String allEmployees(ModelMap map) {
		if (sessionobj != null && sessionobj.getIsValidLogin()) {
			try {
				List<Employee> empList = empService.getAllEmployees();
				map.put("empList", empList);
				map.put("title", "Employees List");
			} catch (Exception e) {
				e.printStackTrace();
			}
			return "employeesList";
		} else
			return "redirect:/";
	}

	@GetMapping("deleteEmp/{id}")
	public String deleteEmp(@PathVariable Long id, ModelMap model) {
		if (sessionobj != null && sessionobj.getIsValidLogin()) {
			try {
				empService.deleteEmployeeById(id);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return "redirect:/emp/allEmps";
		} else {
			return "redirect:/";
		}
	}

	@GetMapping("/activeEmps")
	public String activeEmps(ModelMap map) {
		if (sessionobj != null && sessionobj.getIsValidLogin()) {
			try {
				List<Employee> empList = empService.getActiveEmployees();
				map.put("empList", empList);
				map.put("title", "Active Employees List");
			} catch (Exception e) {
				e.printStackTrace();
			}
			return "employeesList";
		} else
			return "redirect:/";
	}

}
