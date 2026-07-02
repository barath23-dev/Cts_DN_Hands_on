package com.employee.service;

import com.employee.model.Employee;
import com.employee.projection.EmployeeNameProjection;
import com.employee.projection.EmployeeSummaryDTO;
import com.employee.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public Employee getEmployeeById(Long id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Employee not found with id: " + id));
    }

    public Employee saveEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public void deleteEmployee(Long id) {
        employeeRepository.deleteById(id);
    }

    // Pagination & sorting
    public Page<Employee> getEmployeesPaginated(int page, int size, String sortBy) {
        return employeeRepository.findAll(PageRequest.of(page, size, Sort.by(sortBy)));
    }

    // Search by keyword
    public List<Employee> searchByName(String keyword) {
        return employeeRepository.findByNameContaining(keyword);
    }

    // Interface projection
    public List<EmployeeNameProjection> getEmployeeNamesOnly() {
        return employeeRepository.findAllProjectedBy();
    }

    // Class projection (DTO)
    public List<EmployeeSummaryDTO> getEmployeeSummaries() {
        return employeeRepository.findEmployeeSummaries();
    }
}
