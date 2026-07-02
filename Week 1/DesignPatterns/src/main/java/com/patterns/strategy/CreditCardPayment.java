package com.patterns.strategy;

public class CreditCardPayment implements PaymentStrategy {
    private final String cardHolderName;
    private final String cardNumber;
    private final String cvv;
    private final String expiryDate;

    public CreditCardPayment(String cardHolderName, String cardNumber, String cvv, String expiryDate) {
        this.cardHolderName = cardHolderName;
        this.cardNumber = cardNumber;
        this.cvv = cvv;
        this.expiryDate = expiryDate;
    }

    @Override
    public void pay(double amount) {
        // Redact card number for security logs
        String maskedCard = "XXXX-XXXX-XXXX-" + cardNumber.substring(Math.max(0, cardNumber.length() - 4));
        System.out.printf("Paid $%.2f using Credit Card: %s (Holder: %s)%n", amount, maskedCard, cardHolderName);
    }
}
