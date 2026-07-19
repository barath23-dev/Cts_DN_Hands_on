package com.patterns.dependencyinjection;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DependencyInjectionTest {

    @Test
    public void testDependencyInjectionFlow() {
        // Instantiate the concrete repository
        CustomerRepository repository = new CustomerRepositoryImpl();

        // Inject dependency via constructor
        CustomerService service = new CustomerService(repository);

        // Verify successful data retrieval
        assertEquals("Emma Watson", service.getCustomerName("C001"));
        assertEquals("Liam Neeson", service.getCustomerName("C002"));
        assertEquals("Customer Not Found", service.getCustomerName("C999"));
    }
}
