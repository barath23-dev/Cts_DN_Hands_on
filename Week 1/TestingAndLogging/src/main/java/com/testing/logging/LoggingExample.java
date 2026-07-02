package com.testing.logging;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class LoggingExample {

    public void demonstrateLogging() {
        log.error("This is an error message");
        log.warn("This is a warning message");
    }

    public void demonstrateParameterizedLogging(String userName, int userAge) {
        // Parameterized logging avoids string concatenation performance overheads
        log.info("User session initialized. Name: {}, Age: {}", userName, userAge);
    }
}
