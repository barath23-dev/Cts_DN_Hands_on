package com.patterns.decorator;

public class SMSNotifierDecorator extends NotifierDecorator {
    public SMSNotifierDecorator(Notifier notifier) {
        super(notifier);
    }

    @Override
    public void send(String message) {
        super.send(message); // Send previous channel notification(s)
        sendSMS(message);     // Add SMS functionality
    }

    private void sendSMS(String message) {
        System.out.printf("SMS Notifier: Sending SMS text with content -> \"%s\"%n", message);
    }
}
