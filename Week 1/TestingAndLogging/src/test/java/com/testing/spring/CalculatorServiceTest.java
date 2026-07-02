package com.testing.spring;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class CalculatorServiceTest {

    @Autowired
    private CalculatorService calculatorService;

    @Test
    public void testAdd() {
        assertEquals(5, calculatorService.add(2, 3));
        assertEquals(0, calculatorService.add(-5, 5));
    }
}
