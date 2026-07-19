package com.patterns.observer;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ObserverTest {

    @Test
    public void testObserverNotification() {
        StockMarket googleStock = new StockMarket("GOOGL", 175.50);

        MobileApp phoneApp = new MobileApp("AndroidTrader");
        WebApp webPortal = new WebApp("ChromeFinance");

        googleStock.registerObserver(phoneApp);
        googleStock.registerObserver(webPortal);

        System.out.println("--- Triggering Price Change 1 ---");
        googleStock.setPrice(178.20); // Both observers should be notified

        System.out.println("--- Deregistering MobileApp Observer ---");
        googleStock.deregisterObserver(phoneApp);

        System.out.println("--- Triggering Price Change 2 ---");
        googleStock.setPrice(180.00); // Only WebApp should be notified
        
        assertEquals(180.00, googleStock.getPrice());
    }
}
