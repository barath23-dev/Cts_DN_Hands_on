package com.patterns.dependencyinjection;

/**
 * Service class performing business operations.
 * Relies on Constructor Injection to retrieve CustomerRepository dependency.
 */
public class CustomerService {
    private final CustomerRepository repository;

    // Constructor Injection
    public CustomerService(CustomerRepository repository) {
        if (repository == null) {
            throw new IllegalArgumentException("CustomerRepository dependency cannot be null");
        }
        this.repository = repository;
    }

    public String getCustomerName(String customerId) {
        return repository.findCustomerById(customerId);
    }
}
