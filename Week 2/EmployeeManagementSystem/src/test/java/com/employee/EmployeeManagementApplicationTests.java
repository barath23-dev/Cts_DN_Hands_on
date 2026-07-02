package com.employee;

import com.employee.model.Department;
import com.employee.model.Employee;
import com.employee.repository.DepartmentRepository;
import com.employee.repository.EmployeeRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class EmployeeManagementApplicationTests {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    @Test
    void contextLoads() {
    }

    @Test
    void testCreateDepartmentAndEmployee() {
        Department dept = new Department();
        dept.setName("Engineering");
        departmentRepository.save(dept);

        Employee emp = new Employee();
        emp.setName("Alice");
        emp.setEmail("alice@company.com");
        emp.setDepartment(dept);
        employeeRepository.save(emp);

        assertNotNull(emp.getId());
        assertEquals("Engineering", emp.getDepartment().getName());
    }

    @Test
    void testFindByName() {
        Department dept = new Department();
        dept.setName("Sales");
        departmentRepository.save(dept);

        Employee emp = new Employee();
        emp.setName("Bob");
        emp.setEmail("bob@company.com");
        emp.setDepartment(dept);
        employeeRepository.save(emp);

        var result = employeeRepository.findByName("Bob");
        assertFalse(result.isEmpty());
        assertEquals("Bob", result.get(0).getName());
    }
}
