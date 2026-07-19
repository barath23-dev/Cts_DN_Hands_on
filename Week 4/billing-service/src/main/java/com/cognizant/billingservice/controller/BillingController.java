package com.cognizant.billingservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/billing")
public class BillingController {

    @GetMapping("/{id}")
    public Map<String, Object> getBillingById(@PathVariable String id) {
        return Map.of(
                "billingId", id,
                "amount", 1250.75,
                "status", "PAID"
        );
    }
}
