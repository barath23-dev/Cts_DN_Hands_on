package com.employee.controller;

import com.employee.model.Employee;
import com.employee.projection.EmployeeNameProjection;
import com.employee.projection.EmployeeSummaryDTO;
import com.employee.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping
    public List<Employee> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployee(@PathVariable Long id) {
        return ResponseEntity.ok(employeeService.getEmployeeById(id));
    }

    @PostMapping
    public Employee createEmployee(@RequestBody Employee employee) {
        return employeeService.saveEmployee(employee);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable Long id, @RequestBody Employee details) {
        Employee existing = employeeService.getEmployeeById(id);
        existing.setName(details.getName());
        existing.setEmail(details.getEmail());
        existing.setDepartment(details.getDepartment());
        return ResponseEntity.ok(employeeService.saveEmployee(existing));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Long id) {
        employeeService.deleteEmployee(id);
        return ResponseEntity.noContent().build();
    }

    // Pagination & Sorting
    @GetMapping("/paginated")
    public Page<Employee> getPaginated(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "name") String sortBy) {
        return employeeService.getEmployeesPaginated(page, size, sortBy);
    }

    // Search
    @GetMapping("/search")
    public List<Employee> searchByName(@RequestParam String keyword) {
        return employeeService.searchByName(keyword);
    }

    // Interface projection
    @GetMapping("/names")
    public List<EmployeeNameProjection> getNames() {
        return employeeService.getEmployeeNamesOnly();
    }

    // Class projection
    @GetMapping("/summaries")
    public List<EmployeeSummaryDTO> getSummaries() {
        return employeeService.getEmployeeSummaries();
    }
}
