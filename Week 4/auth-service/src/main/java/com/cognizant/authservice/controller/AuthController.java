package com.cognizant.authservice.controller;

import com.cognizant.authservice.security.JwtTokenProvider;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final JwtTokenProvider jwtTokenProvider;

    public AuthController(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @PostMapping("/login")
    public Map<String, String> login(@RequestBody Map<String, String> request) {
        String username = request.get("username");
        String password = request.get("password");
        if ("admin".equals(username) && "password".equals(password)) {
            String token = jwtTokenProvider.createToken(username);
            return Map.of("token", token);
        }
        throw new RuntimeException("Invalid credentials");
    }

    @GetMapping("/secure")
    public String secureEndpoint() {
        return "This is a secure endpoint accessible only with a valid JWT token!";
    }
}
