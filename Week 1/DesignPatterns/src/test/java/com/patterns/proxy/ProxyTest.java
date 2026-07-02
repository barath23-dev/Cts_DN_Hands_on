package com.patterns.proxy;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ProxyTest {

    @Test
    public void testProxyLazyLoadingAndCaching() {
        Image image = new ProxyImage("high_res_photo.png");

        long start = System.currentTimeMillis();
        // First display call: should trigger remote loading (takes ~1.5s)
        System.out.println("--- First Display Call ---");
        image.display();
        long loadTime = System.currentTimeMillis() - start;
        assertTrue(loadTime >= 1400, "First load should take at least 1.5 seconds");

        start = System.currentTimeMillis();
        // Second display call: should use cached version (takes near 0s)
        System.out.println("--- Second Display Call ---");
        image.display();
        long cacheTime = System.currentTimeMillis() - start;
        assertTrue(cacheTime < 100, "Second display call should use cache instantly");
    }
}
