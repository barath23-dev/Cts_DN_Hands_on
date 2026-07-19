package com.algorithms.forecasting;

import java.util.HashMap;
import java.util.Map;

/**
 * Calculates future value forecasts using recursive approaches.
 */
public class FinancialForecasting {
    
    // Memoization table for optimization
    private final Map<Integer, Double> memo = new HashMap<>();

    /**
     * Standard recursive implementation to calculate future value.
     * Formula: FV = PV * (1 + growthRate)^n
     * Time Complexity: O(n) due to n recursive steps.
     * Space Complexity: O(n) stack space.
     */
    public double calculateFutureValue(double currentValue, double growthRate, int periods) {
        // Base Case
        if (periods <= 0) {
            return currentValue;
        }
        // Recursive Step
        return calculateFutureValue(currentValue * (1 + growthRate), growthRate, periods - 1);
    }

    /**
     * Optimized recursive implementation using memoization to avoid redundant computations.
     * (Useful when calculating subproblems for varying rates/periods or overlapping intervals).
     */
    public double calculateFutureValueOptimized(double currentValue, double growthRate, int periods) {
        // Base Case
        if (periods <= 0) {
            return currentValue;
        }
        if (memo.containsKey(periods)) {
            return memo.get(periods);
        }
        double result = calculateFutureValueOptimized(currentValue * (1 + growthRate), growthRate, periods - 1);
        memo.put(periods, result);
        return result;
    }
}
