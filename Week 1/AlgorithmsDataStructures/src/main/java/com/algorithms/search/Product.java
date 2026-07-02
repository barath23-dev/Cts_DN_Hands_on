package com.algorithms.search;

public class Product implements Comparable<Product> {
    private String productId;
    private String productName;
    private String category;

    public Product(String productId, String productName, String category) {
        this.productId = productId;
        this.productName = productName;
        this.category = category;
    }

    public String getProductId() { return productId; }
    public String getProductName() { return productName; }
    public String getCategory() { return category; }

    @Override
    public int compareTo(Product other) {
        // Sort products primarily by product name for binary search lookup
        return this.productName.compareTo(other.productName);
    }

    @Override
    public String toString() {
        return String.format("Product [ID=%s, Name=%s, Category=%s]", productId, productName, category);
    }
}
