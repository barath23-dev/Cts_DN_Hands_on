package com.employee.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Table(name = "employees")
@Data
@NoArgsConstructor
@AllArgsConstructor
@NamedQuery(name = "Employee.findByDepartmentName",
        query = "SELECT e FROM Employee e WHERE e.department.name = :deptName")
public class Employee extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(unique = true)
    private String email;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "department_id")
    private Department department;

    @Override
    public String toString() {
        return "Employee [id=" + id + ", name=" + name + ", email=" + email + "]";
    }
}
