package com.cognizant.productservice.controller;

import com.cognizant.productservice.entity.Product;
import com.cognizant.productservice.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductRepository productRepository;

    @Value("${message:Hello local}")
    private String message;

    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @PostMapping
    public Product createProduct(@RequestBody Product product) {
        return productRepository.save(product);
    }

    @GetMapping("/{code}")
    public Product getProductByCode(@PathVariable String code) {
        return productRepository.findByCode(code).orElseThrow(() -> new RuntimeException("Product not found"));
    }

    @GetMapping("/config-message")
    public String getConfigMessage() {
        return message;
    }
}
