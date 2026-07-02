package com.patterns.adapter;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AdapterTest {

    @Test
    public void testStripeAdapter() {
        StripeGateway stripe = new StripeGateway();
        PaymentProcessor stripeProcessor = new StripeAdapter(stripe);
        
        // This validates the compilation and runtime assignment
        assertNotNull(stripeProcessor, "Stripe adapter should be initialized");
        stripeProcessor.processPayment(150.75);
    }

    @Test
    public void testPaypalAdapter() {
        PaypalGateway paypal = new PaypalGateway();
        PaymentProcessor paypalProcessor = new PaypalAdapter(paypal, "user@example.com");

        assertNotNull(paypalProcessor, "PayPal adapter should be initialized");
        paypalProcessor.processPayment(250.00);
    }
}
