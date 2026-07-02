package com.patterns.decorator;

/**
 * Concrete component that sends standard email notifications.
 */
public class EmailNotifier implements Notifier {
    @Override
    public void send(String message) {
        System.out.printf("Email Notifier: Sending email with content -> \"%s\"%n", message);
    }
}
