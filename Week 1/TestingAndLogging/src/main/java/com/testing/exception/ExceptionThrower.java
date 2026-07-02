package com.testing.exception;

public class ExceptionThrower {
    public void throwException(String message) {
        if (message == null || message.trim().isEmpty()) {
            throw new IllegalArgumentException("Message cannot be empty");
        }
        throw new RuntimeException(message);
    }
}
