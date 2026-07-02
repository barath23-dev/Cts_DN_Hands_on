package com.patterns.dependencyinjection;

import java.util.HashMap;
import java.util.Map;

/**
 * Concrete repository implementation returning mock database data.
 */
public class CustomerRepositoryImpl implements CustomerRepository {
    private final Map<String, String> customerDatabase = new HashMap<>();

    public CustomerRepositoryImpl() {
        // Pre-fill database with sample data
        customerDatabase.put("C001", "Emma Watson");
        customerDatabase.put("C002", "Liam Neeson");
        customerDatabase.put("C003", "Morgan Freeman");
    }

    @Override
    public String findCustomerById(String id) {
        return customerDatabase.getOrDefault(id, "Customer Not Found");
    }
}
