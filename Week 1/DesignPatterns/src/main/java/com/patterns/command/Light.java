package com.patterns.command;

/**
 * Receiver class that performs the actual operations.
 */
public class Light {
    private final String roomName;
    private boolean isOn = false;

    public Light(String roomName) {
        this.roomName = roomName;
    }

    public void turnOn() {
        isOn = true;
        System.out.println("The " + roomName + " light is now ON.");
    }

    public void turnOff() {
        isOn = false;
        System.out.println("The " + roomName + " light is now OFF.");
    }

    public boolean isOn() {
        return isOn;
    }
}
