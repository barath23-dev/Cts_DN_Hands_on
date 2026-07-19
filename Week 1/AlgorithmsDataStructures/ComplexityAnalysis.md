# Asymptotic Complexity Analysis - Algorithms and Data Structures

This document provides a detailed breakdown of the time and space complexity of the algorithms and data structures implemented for the week 1 exercises.

---

## Exercise 1: Inventory Management System

### Chosen Data Structure
- `HashMap<String, Product>`

### Rationale
A warehouse inventory requires quick lookups, updates, additions, and deletions of product objects by their unique ID. A `HashMap` provides constant-time $O(1)$ performance on average for all these operations, making it extremely suitable compared to an array or linked list (which require $O(N)$ lookup).

### Time Complexity Analysis
- **Add Product**: $O(1)$ average, $O(N)$ worst-case (in case of hash collisions leading to resizing or bucketing).
- **Update Product**: $O(1)$ average, $O(N)$ worst-case.
- **Delete Product**: $O(1)$ average, $O(N)$ worst-case.
- **Search Product**: $O(1)$ average, $O(N)$ worst-case.

---

## Exercise 2 & 6: Search Algorithms (E-commerce & Library)

### Linear Search vs. Binary Search
- **Linear Search**: Iterates through the list of elements checking each element one by one.
- **Binary Search**: Divides the search interval in half recursively. Requires the collection to be sorted.

### Time Complexity Analysis
| Algorithm | Best-Case | Average-Case | Worst-Case | Space Complexity |
| :--- | :--- | :--- | :--- | :--- |
| **Linear Search** | $O(1)$ | $O(N)$ | $O(N)$ | $O(1)$ |
| **Binary Search** | $O(1)$ | $O(\log N)$ | $O(\log N)$ | $O(1)$ |

### Recommendations
Binary search is vastly superior for larger datasets ($N > 1000$). However, since binary search requires sorted arrays, it has a sorting overhead ($O(N \log N)$) if data is frequently updated or unsorted. Linear search is preferred for small, unsorted, or rarely-searched collections.

---

## Exercise 3: Sorting Customer Orders

### Bubble Sort vs. Quick Sort
- **Bubble Sort**: Simplistic comparison sort that repeatedly swaps adjacent elements.
- **Quick Sort**: Divide-and-conquer partition-based sorting algorithm.

### Complexity Analysis
| Algorithm | Best-Case | Average-Case | Worst-Case | Space Complexity |
| :--- | :--- | :--- | :--- | :--- |
| **Bubble Sort** | $O(N)$ | $O(N^2)$ | $O(N^2)$ | $O(1)$ |
| **Quick Sort** | $O(N \log N)$ | $O(N \log N)$ | $O(N^2)$ | $O(\log N)$ |

### Recommendations
Quick Sort is generally preferred over Bubble Sort due to its average time complexity of $O(N \log N)$ compared to $O(N^2)$ for Bubble Sort. For large arrays, Bubble Sort becomes extremely slow.

---

## Exercise 4: Employee Management System (Arrays)

### Representation
Stored in a contiguous static array block of fixed size.

### Complexity Analysis
- **Add**: $O(1)$ if capacity exists, since we track the end index (`size`).
- **Search**: $O(N)$ since we must perform linear scanning to locate an ID.
- **Traverse**: $O(N)$ as we visit each element.
- **Delete**: $O(N)$ because after deleting an element, we must shift all subsequent elements left to maintain contiguity.

---

## Exercise 5: Task Management System (Linked Lists)

### Singly Linked List vs. Arrays
- Linked lists are dynamic and do not require pre-allocated memory or shifting upon deletion.
- However, they do not support random access ($O(1)$ index lookup).

### Complexity Analysis
- **Add**: $O(N)$ to walk to the end of the list and append (can be optimized to $O(1)$ with a tail pointer).
- **Search**: $O(N)$ average and worst-case to traverse and find matching ID.
- **Traverse**: $O(N)$ to visit all nodes.
- **Delete**: $O(N)$ to locate the node and adjust reference pointers.

---

## Exercise 7: Financial Forecasting (Recursion)

### Formula
$$FV = PV \times (1 + growthRate)^n$$

### Complexity Analysis
- **Simple Recursion**:
  - Time Complexity: $O(N)$ where $N$ is the number of periods, as there are $N$ call stack levels.
  - Space Complexity: $O(N)$ stack memory overhead.
- **Memoized Recursion**:
  - Caches results of subproblems. Reduces complexity when we compute overlapping subproblems.
- **Iterative Alternative (Optimal)**:
  - Can be rewritten as a loop to achieve $O(N)$ time complexity and $O(1)$ space complexity, avoiding call stack overflow errors.
