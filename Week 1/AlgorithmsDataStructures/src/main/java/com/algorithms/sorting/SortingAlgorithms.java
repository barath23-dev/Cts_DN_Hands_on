package com.algorithms.sorting;

/**
 * Class containing sorting algorithms for customer orders.
 */
public class SortingAlgorithms {

    /**
     * Bubble Sort algorithm to sort orders by totalPrice in ascending order.
     * Time Complexity: O(N^2) average and worst-case, O(N) best-case (with optimizations).
     * Space Complexity: O(1) auxiliary space (in-place).
     */
    public static void bubbleSort(Order[] orders) {
        if (orders == null || orders.length <= 1) return;
        int n = orders.length;
        boolean swapped;
        for (int i = 0; i < n - 1; i++) {
            swapped = false;
            for (int j = 0; j < n - i - 1; j++) {
                if (orders[j].getTotalPrice() > orders[j + 1].getTotalPrice()) {
                    // Swap elements
                    Order temp = orders[j];
                    orders[j] = orders[j + 1];
                    orders[j + 1] = temp;
                    swapped = true;
                }
            }
            // If no elements were swapped, array is already sorted
            if (!swapped) break;
        }
    }

    /**
     * Quick Sort algorithm to sort orders by totalPrice in ascending order.
     * Time Complexity: O(N log N) average-case, O(N^2) worst-case (depending on pivot choice).
     * Space Complexity: O(log N) auxiliary space due to call stack.
     */
    public static void quickSort(Order[] orders, int low, int high) {
        if (orders == null || orders.length <= 1 || low >= high) return;
        
        int pivotIndex = partition(orders, low, high);
        quickSort(orders, low, pivotIndex - 1);
        quickSort(orders, pivotIndex + 1, high);
    }

    private static int partition(Order[] orders, int low, int high) {
        double pivot = orders[high].getTotalPrice();
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (orders[j].getTotalPrice() <= pivot) {
                i++;
                // Swap orders[i] and orders[j]
                Order temp = orders[i];
                orders[i] = orders[j];
                orders[j] = temp;
            }
        }

        // Swap orders[i+1] and orders[high] (pivot)
        Order temp = orders[i + 1];
        orders[i + 1] = orders[high];
        orders[high] = temp;

        return i + 1;
    }
}
