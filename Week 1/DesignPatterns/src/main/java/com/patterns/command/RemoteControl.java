package com.patterns.command;

import java.util.ArrayList;
import java.util.List;

/**
 * Invoker class that registers and triggers Commands.
 */
public class RemoteControl {
    private Command command;
    private final List<Command> commandHistory = new ArrayList<>();

    public void setCommand(Command command) {
        this.command = command;
    }

    public void pressButton() {
        if (command == null) {
            System.out.println("Remote Control: No command registered.");
            return;
        }
        command.execute();
        commandHistory.add(command);
    }

    public int getHistorySize() {
        return commandHistory.size();
    }
}
