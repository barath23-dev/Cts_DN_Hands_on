package com.patterns.adapter;

/**
 * Adaptee class representing PayPal's payment gateway.
 */
public class PaypalGateway {
    public void makePayment(String accountEmail, double amount) {
        System.out.printf("PayPal Gateway: Executing payment of $%.2f for account %s%n", amount, accountEmail);
    }
}
