package com.cognizant.orderservice;

import com.cognizant.orderservice.entity.Order;
import com.cognizant.orderservice.repository.OrderRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class OrderServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(OrderServiceApplication.class, args);
    }

    @Bean
    public CommandLineRunner initOrders(OrderRepository repository) {
        return args -> {
            repository.save(new Order(null, 1L, "PROD-A", 2, "PENDING"));
            repository.save(new Order(null, 2L, "PROD-B", 5, "COMPLETED"));
        };
    }
}
