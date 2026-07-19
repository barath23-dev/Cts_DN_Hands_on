package com.employee.repository;

import com.employee.model.Employee;
import com.employee.projection.EmployeeNameProjection;
import com.employee.projection.EmployeeSummaryDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    // Derived query methods
    List<Employee> findByName(String name);
    List<Employee> findByDepartmentName(String departmentName);
    List<Employee> findByNameContaining(String keyword);

    // Custom JPQL query
    @Query("SELECT e FROM Employee e WHERE e.department.id = :deptId")
    List<Employee> findEmployeesByDepartmentId(@Param("deptId") Long deptId);

    // Pagination support
    Page<Employee> findByDepartmentId(Long departmentId, Pageable pageable);

    // Interface-based projection
    List<EmployeeNameProjection> findAllProjectedBy();

    // Class-based projection using constructor expression
    @Query("SELECT new com.employee.projection.EmployeeSummaryDTO(e.name, d.name) " +
            "FROM Employee e JOIN e.department d")
    List<EmployeeSummaryDTO> findEmployeeSummaries();
}
