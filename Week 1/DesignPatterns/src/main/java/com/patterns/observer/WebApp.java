package com.patterns.observer;

public class WebApp implements Observer {
    private final String clientName;

    public WebApp(String clientName) {
        this.clientName = clientName;
    }

    @Override
    public void update(String stockSymbol, double price) {
        System.out.printf("Web Application Portal [%s]: Stock update -> %s is now $%.2f%n", clientName, stockSymbol, price);
    }
}
