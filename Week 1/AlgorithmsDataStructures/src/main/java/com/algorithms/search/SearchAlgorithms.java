package com.algorithms.search;

import java.util.Arrays;

/**
 * Class containing search algorithms for the e-commerce search function.
 */
public class SearchAlgorithms {

    /**
     * Performs a linear search to find a product by name.
     * Time Complexity: O(N) worst-case, O(1) best-case.
     */
    public static Product linearSearch(Product[] products, String targetName) {
        if (products == null || targetName == null) return null;
        for (Product product : products) {
            if (product.getProductName().equals(targetName)) {
                return product;
            }
        }
        return null;
    }

    /**
     * Performs a binary search to find a product by name.
     * Assumes the array is already sorted by product name.
     * Time Complexity: O(log N) worst-case, O(1) best-case.
     */
    public static Product binarySearch(Product[] sortedProducts, String targetName) {
        if (sortedProducts == null || targetName == null) return null;
        int low = 0;
        int high = sortedProducts.length - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            int comparison = sortedProducts[mid].getProductName().compareTo(targetName);

            if (comparison == 0) {
                return sortedProducts[mid];
            } else if (comparison < 0) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return null;
    }
}
