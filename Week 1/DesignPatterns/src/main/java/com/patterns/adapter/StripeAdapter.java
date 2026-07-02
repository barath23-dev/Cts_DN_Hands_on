package com.patterns.adapter;

/**
 * Adapter that maps Stripe's interfaces to our target PaymentProcessor.
 */
public class StripeAdapter implements PaymentProcessor {
    private final StripeGateway stripeGateway;

    public StripeAdapter(StripeGateway stripeGateway) {
        this.stripeGateway = stripeGateway;
    }

    @Override
    public void processPayment(double amount) {
        // Translate client processPayment call to Stripe chargeCard call
        stripeGateway.chargeCard(amount);
    }
}
