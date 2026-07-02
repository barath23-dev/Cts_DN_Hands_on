package com.patterns.decorator;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DecoratorTest {

    @Test
    public void testEmailOnlyNotification() {
        Notifier emailNotifier = new EmailNotifier();
        assertNotNull(emailNotifier);
        
        System.out.println("--- Test: Email Only ---");
        emailNotifier.send("Hello World!");
    }

    @Test
    public void testEmailAndSMSNotification() {
        Notifier emailAndSMS = new SMSNotifierDecorator(new EmailNotifier());
        assertNotNull(emailAndSMS);
        
        System.out.println("--- Test: Email + SMS ---");
        emailAndSMS.send("System Update: Success!");
    }

    @Test
    public void testAllChannelsNotification() {
        Notifier allChannels = new SlackNotifierDecorator(
                new SMSNotifierDecorator(
                        new EmailNotifier()
                )
        );
        assertNotNull(allChannels);

        System.out.println("--- Test: Email + SMS + Slack ---");
        allChannels.send("Critical Alert: Server Down!");
    }
}
