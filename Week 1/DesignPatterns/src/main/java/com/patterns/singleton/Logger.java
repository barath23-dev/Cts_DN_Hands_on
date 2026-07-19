package com.patterns.singleton;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Thread-safe Singleton implementation of a Logger utility.
 * Demonstrates double-checked locking pattern for optimal performance.
 */
public class Logger {
    // Volatile instance to ensure visibility changes across threads
    private static volatile Logger instance;

    // Formatter for timestamped log outputs
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");

    // Private constructor to prevent direct instantiation
    private Logger() {
        // Prevent instantiation via reflection API
        if (instance != null) {
            throw new IllegalStateException("Logger instance already exists. Use getInstance().");
        }
    }

    /**
     * Public static method to retrieve the single Logger instance.
     */
    public static Logger getInstance() {
        if (instance == null) {
            synchronized (Logger.class) {
                if (instance == null) {
                    instance = new Logger();
                }
            }
        }
        return instance;
    }

    /**
     * Log a standard informational message.
     */
    public void log(String message) {
        String timestamp = LocalDateTime.now().format(formatter);
        System.out.printf("[%s] [INFO] %s%n", timestamp, message);
    }

    /**
     * Log an error warning message.
     */
    public void logError(String message, Throwable throwable) {
        String timestamp = LocalDateTime.now().format(formatter);
        System.err.printf("[%s] [ERROR] %s%n", timestamp, message);
        if (throwable != null) {
            throwable.printStackTrace();
        }
    }
}
