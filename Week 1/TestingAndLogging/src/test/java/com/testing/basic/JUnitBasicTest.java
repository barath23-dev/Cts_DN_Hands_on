package com.testing.basic;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Validates basic JUnit 5 features: setup, teardown, assertions, and AAA pattern.
 */
public class JUnitBasicTest {

    private List<String> listFixture;

    @BeforeEach
    public void setUp() {
        // Arrange phase for shared test fixture
        listFixture = new ArrayList<>();
        listFixture.add("Item1");
        listFixture.add("Item2");
    }

    @AfterEach
    public void tearDown() {
        // Teardown resources
        listFixture.clear();
    }

    @Test
    public void testBasicAssertions() {
        // Act and Assert phases (AAA Pattern)
        assertEquals(2, listFixture.size(), "Size of list fixture should be 2");
        assertTrue(listFixture.contains("Item1"), "List should contain 'Item1'");
        assertFalse(listFixture.contains("Item3"), "List should not contain 'Item3'");
        assertNotNull(listFixture, "Fixture must not be null");
    }
}
