package com.patterns.adapter;

/**
 * Adapter that maps PayPal's interfaces to our target PaymentProcessor.
 */
public class PaypalAdapter implements PaymentProcessor {
    private final PaypalGateway paypalGateway;
    private final String clientEmail;

    public PaypalAdapter(PaypalGateway paypalGateway, String clientEmail) {
        this.paypalGateway = paypalGateway;
        this.clientEmail = clientEmail;
    }

    @Override
    public void processPayment(double amount) {
        // Translate client processPayment call to PayPal makePayment call
        paypalGateway.makePayment(clientEmail, amount);
    }
}
