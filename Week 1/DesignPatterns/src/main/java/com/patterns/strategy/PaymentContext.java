package com.patterns.strategy;

/**
 * Context class that delegates payment execution to a selected Strategy.
 */
public class PaymentContext {
    private PaymentStrategy paymentStrategy;

    // Allows dynamic configuration of strategies
    public void setPaymentStrategy(PaymentStrategy paymentStrategy) {
        this.paymentStrategy = paymentStrategy;
    }

    public void executePayment(double amount) {
        if (paymentStrategy == null) {
            throw new IllegalStateException("Payment strategy has not been set.");
        }
        paymentStrategy.pay(amount);
    }
}
