package com.patterns.builder;

/**
 * Computer class representing a complex configuration of components.
 * Employs the Builder design pattern.
 */
public class Computer {
    // Required attributes
    private final String cpu;
    private final String ram;
    private final String storage;

    // Optional attributes
    private final String graphicsCard;
    private final String operatingSystem;
    private final boolean hasBluetooth;

    // Private constructor taking Builder parameter
    private Computer(Builder builder) {
        this.cpu = builder.cpu;
        this.ram = builder.ram;
        this.storage = builder.storage;
        this.graphicsCard = builder.graphicsCard;
        this.operatingSystem = builder.operatingSystem;
        this.hasBluetooth = builder.hasBluetooth;
    }

    // Getters for attributes
    public String getCpu() { return cpu; }
    public String getRam() { return ram; }
    public String getStorage() { return storage; }
    public String getGraphicsCard() { return graphicsCard; }
    public String getOperatingSystem() { return operatingSystem; }
    public boolean hasBluetooth() { return hasBluetooth; }

    @Override
    public String toString() {
        return "Computer [CPU=" + cpu + ", RAM=" + ram + ", Storage=" + storage 
                + ", GPU=" + graphicsCard + ", OS=" + operatingSystem 
                + ", Bluetooth=" + hasBluetooth + "]";
    }

    // Static nested Builder class
    public static class Builder {
        // Required parameters
        private final String cpu;
        private final String ram;
        private final String storage;

        // Optional parameters - initialized to default values
        private String graphicsCard = "Integrated Graphics";
        private String operatingSystem = "FreeDOS";
        private boolean hasBluetooth = false;

        // Constructor of Builder requiring mandatory fields
        public Builder(String cpu, String ram, String storage) {
            this.cpu = cpu;
            this.ram = ram;
            this.storage = storage;
        }

        // Setter methods returning Builder instance for chaining
        public Builder setGraphicsCard(String graphicsCard) {
            this.graphicsCard = graphicsCard;
            return this;
        }

        public Builder setOperatingSystem(String operatingSystem) {
            this.operatingSystem = operatingSystem;
            return this;
        }

        public Builder setBluetooth(boolean hasBluetooth) {
            this.hasBluetooth = hasBluetooth;
            return this;
        }

        // The build method to construct the product
        public Computer build() {
            return new Computer(this);
        }
    }
}
