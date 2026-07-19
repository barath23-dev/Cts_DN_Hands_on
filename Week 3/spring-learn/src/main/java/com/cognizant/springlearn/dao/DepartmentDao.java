package com.cognizant.springlearn.dao;

import java.util.ArrayList;
import java.util.List;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Repository;
import com.cognizant.springlearn.Department;

@Repository
public class DepartmentDao {

	private static List<Department> DEPARTMENT_LIST;

	@SuppressWarnings("unchecked")
	public DepartmentDao() {
		if (DEPARTMENT_LIST == null) {
			ApplicationContext context = new ClassPathXmlApplicationContext("employee.xml");
			DEPARTMENT_LIST = context.getBean("departmentList", ArrayList.class);
		}
	}

	public List<Department> getAllDepartments() {
		return DEPARTMENT_LIST;
	}

}
