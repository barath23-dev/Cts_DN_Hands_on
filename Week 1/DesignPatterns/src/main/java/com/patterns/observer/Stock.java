package com.patterns.observer;

/**
 * Subject interface in the Observer pattern.
 */
public interface Stock {
    void registerObserver(Observer observer);
    void deregisterObserver(Observer observer);
    void notifyObservers();
}
