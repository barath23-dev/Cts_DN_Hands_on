package com.patterns.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * Concrete Subject that tracks stock price updates.
 */
public class StockMarket implements Stock {
    private final List<Observer> observers = new ArrayList<>();
    private final String stockSymbol;
    private double price;

    public StockMarket(String stockSymbol, double initialPrice) {
        this.stockSymbol = stockSymbol;
        this.price = initialPrice;
    }

    public void setPrice(double newPrice) {
        if (Math.abs(this.price - newPrice) > 0.001) {
            this.price = newPrice;
            notifyObservers();
        }
    }

    public String getStockSymbol() {
        return stockSymbol;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public void registerObserver(Observer observer) {
        if (observer != null && !observers.contains(observer)) {
            observers.add(observer);
        }
    }

    @Override
    public void deregisterObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(stockSymbol, price);
        }
    }
}
