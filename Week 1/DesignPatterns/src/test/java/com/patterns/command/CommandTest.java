package com.patterns.command;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CommandTest {

    @Test
    public void testCommandExecution() {
        Light livingRoomLight = new Light("Living Room");
        RemoteControl remote = new RemoteControl();

        Command turnOn = new LightOnCommand(livingRoomLight);
        Command turnOff = new LightOffCommand(livingRoomLight);

        // 1. Test Turn On
        remote.setCommand(turnOn);
        remote.pressButton();
        assertTrue(livingRoomLight.isOn(), "Light should be turned ON");

        // 2. Test Turn Off
        remote.setCommand(turnOff);
        remote.pressButton();
        assertFalse(livingRoomLight.isOn(), "Light should be turned OFF");

        assertEquals(2, remote.getHistorySize(), "Invoker should maintain a history of executed commands");
    }
}
