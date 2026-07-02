package com.testing.advanced;

import com.testing.evenchecker.EvenChecker;
import com.testing.exception.ExceptionThrower;
import com.testing.performance.PerformanceTester;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class JUnitAdvancedTest {

    private final EvenChecker evenChecker = new EvenChecker();
    private final ExceptionThrower exceptionThrower = new ExceptionThrower();
    private final PerformanceTester performanceTester = new PerformanceTester();

    @Order(1)
    @ParameterizedTest
    @ValueSource(ints = {2, 4, 6, 8, 10})
    public void testEvenNumbers(int number) {
        assertTrue(evenChecker.isEven(number), number + " should be even");
    }

    @Order(2)
    @Test
    public void testExceptionThrown() {
        assertThrows(IllegalArgumentException.class, () -> {
            exceptionThrower.throwException(" ");
        });

        assertThrows(RuntimeException.class, () -> {
            exceptionThrower.throwException("Test exception");
        });
    }

    @Order(3)
    @Test
    public void testExecutionTimeout() {
        // Assert that execution completes within 200 milliseconds
        assertTimeout(Duration.ofMillis(200), () -> {
            performanceTester.performTask(50);
        });
    }
}
