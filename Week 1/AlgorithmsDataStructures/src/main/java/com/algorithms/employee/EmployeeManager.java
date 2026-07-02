package com.algorithms.employee;

/**
 * Manages employee records using a standard array structure.
 */
public class EmployeeManager {
    private final Employee[] employees;
    private int size = 0;

    public EmployeeManager(int capacity) {
        this.employees = new Employee[capacity];
    }

    /**
     * Add an employee to the array.
     * Time Complexity: O(1)
     */
    public boolean addEmployee(Employee employee) {
        if (size >= employees.length) {
            System.out.println("Array capacity reached. Cannot add employee.");
            return false;
        }
        employees[size++] = employee;
        return true;
    }

    /**
     * Search for an employee by ID.
     * Time Complexity: O(N)
     */
    public Employee searchEmployee(String employeeId) {
        for (int i = 0; i < size; i++) {
            if (employees[i].getEmployeeId().equals(employeeId)) {
                return employees[i];
            }
        }
        return null;
    }

    /**
     * Traverse and print all employees.
     * Time Complexity: O(N)
     */
    public void traverseEmployees() {
        System.out.println("--- Employee Directory ---");
        for (int i = 0; i < size; i++) {
            System.out.println(employees[i]);
        }
    }

    /**
     * Delete an employee by ID. Shifts subsequent elements left to close the gap.
     * Time Complexity: O(N)
     */
    public boolean deleteEmployee(String employeeId) {
        int indexToDelete = -1;
        for (int i = 0; i < size; i++) {
            if (employees[i].getEmployeeId().equals(employeeId)) {
                indexToDelete = i;
                break;
            }
        }

        if (indexToDelete == -1) {
            System.out.printf("Employee with ID %s not found for deletion.%n", employeeId);
            return false;
        }

        // Shift elements to the left
        for (int i = indexToDelete; i < size - 1; i++) {
            employees[i] = employees[i + 1];
        }

        employees[--size] = null; // Clear the last duplicated reference
        return true;
    }

    public int getSize() {
        return size;
    }
}
