package com.cognizant.userservice;

import com.cognizant.userservice.entity.User;
import com.cognizant.userservice.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableDiscoveryClient
public class UserServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(UserServiceApplication.class, args);
    }

    @Bean
    public CommandLineRunner initData(UserRepository repository) {
        return args -> {
            repository.save(new User(null, "Alice Smith", "alice@example.com"));
            repository.save(new User(null, "Bob Jones", "bob@example.com"));
        };
    }
}
