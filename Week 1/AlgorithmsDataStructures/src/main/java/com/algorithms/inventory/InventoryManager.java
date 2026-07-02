package com.algorithms.inventory;

import java.util.HashMap;
import java.util.Map;

/**
 * InventoryManager handles product storage and operations in a warehouse.
 * Chooses HashMap as the primary data structure to ensure O(1) average time complexity 
 * for add, update, search, and delete operations.
 */
public class InventoryManager {
    private final Map<String, Product> inventory = new HashMap<>();

    /**
     * Add a product to the inventory.
     * Time Complexity: O(1) average
     */
    public void addProduct(Product product) {
        if (product == null || product.getProductId() == null) {
            throw new IllegalArgumentException("Invalid product or product ID");
        }
        inventory.put(product.getProductId(), product);
    }

    /**
     * Update an existing product's details.
     * Time Complexity: O(1) average
     */
    public void updateProduct(String productId, int newQuantity, double newPrice) {
        Product product = inventory.get(productId);
        if (product != null) {
            product.setQuantity(newQuantity);
            product.setPrice(newPrice);
        } else {
            System.out.printf("Update Failed: Product with ID %s not found.%n", productId);
        }
    }

    /**
     * Delete a product from the inventory.
     * Time Complexity: O(1) average
     */
    public void deleteProduct(String productId) {
        if (inventory.containsKey(productId)) {
            inventory.remove(productId);
        } else {
            System.out.printf("Delete Failed: Product with ID %s not found.%n", productId);
        }
    }

    /**
     * Retrieve a product from the inventory.
     * Time Complexity: O(1) average
     */
    public Product getProduct(String productId) {
        return inventory.get(productId);
    }

    /**
     * Get the current count of unique products in the inventory.
     */
    public int getInventorySize() {
        return inventory.size();
    }
}
