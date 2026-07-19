package com.cognizant.paymentservice.controller;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payment")
public class PaymentController {
    private static final Logger logger = LoggerFactory.getLogger(PaymentController.class);

    @GetMapping("/process")
    @CircuitBreaker(name = "paymentService", fallbackMethod = "fallbackPayment")
    public String processPayment(@RequestParam(value = "slow", defaultValue = "false") boolean slow) {
        if (slow) {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            throw new RuntimeException("Slow service call failed");
        }
        return "Payment processed successfully!";
    }

    public String fallbackPayment(boolean slow, Throwable throwable) {
        logger.warn("Circuit Breaker triggered. Fallback executed. Reason: {}", throwable.getMessage());
        return "Payment gateway is currently experiencing delays. Please try again later. (Fallback active)";
    }
}
