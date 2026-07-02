package com.patterns.adapter;

/**
 * Adaptee class representing Stripe's payment gateway.
 */
public class StripeGateway {
    public void chargeCard(double amountInUSD) {
        System.out.printf("Stripe Gateway: Charging card with amount $%.2f%n", amountInUSD);
    }
}
