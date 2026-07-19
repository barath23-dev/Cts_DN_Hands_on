package com.patterns.observer;

/**
 * Observer interface in the Observer pattern.
 */
public interface Observer {
    void update(String stockSymbol, double price);
}
