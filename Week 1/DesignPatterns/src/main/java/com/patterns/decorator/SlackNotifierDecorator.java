package com.patterns.decorator;

public class SlackNotifierDecorator extends NotifierDecorator {
    public SlackNotifierDecorator(Notifier notifier) {
        super(notifier);
    }

    @Override
    public void send(String message) {
        super.send(message); // Send previous channel notification(s)
        sendSlackMessage(message); // Add Slack functionality
    }

    private void sendSlackMessage(String message) {
        System.out.printf("Slack Notifier: Posting message to channel -> \"%s\"%n", message);
    }
}
