package com.patterns.adapter;

/**
 * Target interface expected by our client application.
 */
public interface PaymentProcessor {
    void processPayment(double amount);
}
