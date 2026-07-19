package com.patterns.strategy;

public class PayPalPayment implements PaymentStrategy {
    private final String emailId;
    private final String token;

    public PayPalPayment(String emailId, String token) {
        this.emailId = emailId;
        this.token = token;
    }

    @Override
    public void pay(double amount) {
        System.out.printf("Paid $%.2f using PayPal account: %s%n", amount, emailId);
    }
}
