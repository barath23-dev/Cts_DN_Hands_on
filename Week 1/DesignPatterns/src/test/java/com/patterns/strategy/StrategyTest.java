package com.patterns.strategy;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class StrategyTest {

    @Test
    public void testPaymentStrategies() {
        PaymentContext context = new PaymentContext();

        // 1. Credit Card Strategy
        PaymentStrategy creditCard = new CreditCardPayment("John Doe", "1234567890123456", "123", "12/28");
        context.setPaymentStrategy(creditCard);
        System.out.println("--- Executing Credit Card Payment ---");
        context.executePayment(99.99);

        // 2. PayPal Strategy
        PaymentStrategy paypal = new PayPalPayment("john.doe@example.com", "secureTokenHash");
        context.setPaymentStrategy(paypal);
        System.out.println("--- Executing PayPal Payment ---");
        context.executePayment(49.50);

        assertNotNull(context);
    }
}
