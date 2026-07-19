package com.cognizant.productservice;

import com.cognizant.productservice.entity.Product;
import com.cognizant.productservice.repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableDiscoveryClient
public class ProductServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(ProductServiceApplication.class, args);
    }

    @Bean
    public CommandLineRunner initProducts(ProductRepository repository) {
        return args -> {
            repository.save(new Product(null, "PROD-A", "Wireless Mouse", 25.0, 100));
            repository.save(new Product(null, "PROD-B", "Mechanical Keyboard", 85.0, 50));
        };
    }
}
