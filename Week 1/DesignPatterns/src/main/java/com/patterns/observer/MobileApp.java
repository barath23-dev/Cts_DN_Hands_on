package com.patterns.observer;

public class MobileApp implements Observer {
    private final String appName;

    public MobileApp(String appName) {
        this.appName = appName;
    }

    @Override
    public void update(String stockSymbol, double price) {
        System.out.printf("Mobile App [%s]: Stock update -> %s is now $%.2f%n", appName, stockSymbol, price);
    }
}
