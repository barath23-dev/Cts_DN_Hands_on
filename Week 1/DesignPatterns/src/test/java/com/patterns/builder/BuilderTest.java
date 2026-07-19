package com.patterns.builder;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BuilderTest {

    @Test
    public void testMinimalComputerConfiguration() {
        Computer computer = new Computer.Builder("Intel Core i5", "8GB", "256GB SSD").build();

        assertEquals("Intel Core i5", computer.getCpu());
        assertEquals("8GB", computer.getRam());
        assertEquals("256GB SSD", computer.getStorage());
        assertEquals("Integrated Graphics", computer.getGraphicsCard()); // Default
        assertEquals("FreeDOS", computer.getOperatingSystem());           // Default
        assertFalse(computer.hasBluetooth());                             // Default
    }

    @Test
    public void testHighEndComputerConfiguration() {
        Computer computer = new Computer.Builder("AMD Ryzen 9", "32GB", "1TB NVMe SSD")
                .setGraphicsCard("NVIDIA RTX 4080")
                .setOperatingSystem("Windows 11 Home")
                .setBluetooth(true)
                .build();

        assertEquals("AMD Ryzen 9", computer.getCpu());
        assertEquals("32GB", computer.getRam());
        assertEquals("1TB NVMe SSD", computer.getStorage());
        assertEquals("NVIDIA RTX 4080", computer.getGraphicsCard());
        assertEquals("Windows 11 Home", computer.getOperatingSystem());
        assertTrue(computer.hasBluetooth());
    }
}
