package com.patterns.singleton;

import org.junit.jupiter.api.Test;
import java.util.Collections;
import java.util.Set;
import java.util.ConcurrentModificationException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

public class LoggerTest {

    @Test
    public void testSingletonSingleThreaded() {
        Logger logger1 = Logger.getInstance();
        Logger logger2 = Logger.getInstance();

        assertNotNull(logger1, "Logger instance should not be null");
        assertSame(logger1, logger2, "Logger.getInstance() must return the same instance");
    }

    @Test
    public void testSingletonMultiThreaded() throws InterruptedException {
        int threadCount = 50;
        ExecutorService executor = Executors.newFixedThreadPool(threadCount);
        Set<Logger> instances = Collections.newSetFromMap(new ConcurrentHashMap<>());

        for (int i = 0; i < threadCount; i++) {
            executor.submit(() -> {
                instances.add(Logger.getInstance());
            });
        }

        executor.shutdown();
        assertTrue(executor.awaitTermination(5, TimeUnit.SECONDS), "Threads did not complete execution in time");

        assertEquals(1, instances.size(), "Only one instance should be created across multiple threads");
        Logger singleInstance = instances.iterator().next();
        assertSame(Logger.getInstance(), singleInstance, "Retrieved instance matches the created singleton");
    }
}
