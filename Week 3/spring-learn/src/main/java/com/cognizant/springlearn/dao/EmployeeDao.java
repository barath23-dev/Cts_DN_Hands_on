package com.cognizant.springlearn.dao;

import java.util.ArrayList;
import java.util.List;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Repository;
import com.cognizant.springlearn.Employee;
import com.cognizant.springlearn.service.exception.EmployeeNotFoundException;

@Repository
public class EmployeeDao {

	private static List<Employee> EMPLOYEE_LIST;

	@SuppressWarnings("unchecked")
	public EmployeeDao() {
		if (EMPLOYEE_LIST == null) {
			ApplicationContext context = new ClassPathXmlApplicationContext("employee.xml");
			EMPLOYEE_LIST = context.getBean("employeeList", ArrayList.class);
		}
	}

	public List<Employee> getAllEmployees() {
		return EMPLOYEE_LIST;
	}

	public void updateEmployee(Employee employee) throws EmployeeNotFoundException {
		Employee existing = null;
		for (Employee emp : EMPLOYEE_LIST) {
			if (emp.getId().equals(employee.getId())) {
				existing = emp;
				break;
			}
		}
		if (existing != null) {
			existing.setName(employee.getName());
			existing.setSalary(employee.getSalary());
			existing.setPermanent(employee.getPermanent());
			existing.setDateOfBirth(employee.getDateOfBirth());
			existing.setDepartment(employee.getDepartment());
			existing.setSkills(employee.getSkills());
		} else {
			throw new EmployeeNotFoundException();
		}
	}

	public void deleteEmployee(int id) throws EmployeeNotFoundException {
		Employee existing = null;
		for (Employee emp : EMPLOYEE_LIST) {
			if (emp.getId() == id) {
				existing = emp;
				break;
			}
		}
		if (existing != null) {
			EMPLOYEE_LIST.remove(existing);
		} else {
			throw new EmployeeNotFoundException();
		}
	}

}
