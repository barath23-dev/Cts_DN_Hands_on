package com.algorithms;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import com.algorithms.inventory.*;
import com.algorithms.search.*;
import com.algorithms.sorting.*;
import com.algorithms.employee.*;
import com.algorithms.task.*;
import com.algorithms.library.*;
import com.algorithms.forecasting.*;

import java.util.Arrays;

public class AlgorithmsTest {

    @Test
    public void testInventoryManagement() {
        InventoryManager manager = new InventoryManager();
        com.algorithms.inventory.Product p1 = new com.algorithms.inventory.Product("P101", "Laptop", 10, 899.99);
        com.algorithms.inventory.Product p2 = new com.algorithms.inventory.Product("P102", "Smartphone", 25, 499.50);

        manager.addProduct(p1);
        manager.addProduct(p2);
        assertEquals(2, manager.getInventorySize());

        manager.updateProduct("P101", 8, 849.99);
        assertEquals(8, manager.getProduct("P101").getQuantity());
        assertEquals(849.99, manager.getProduct("P101").getPrice());

        manager.deleteProduct("P102");
        assertEquals(1, manager.getInventorySize());
        assertNull(manager.getProduct("P102"));
    }

    @Test
    public void testECommerceSearch() {
        com.algorithms.search.Product[] products = {
            new com.algorithms.search.Product("1", "Keyboard", "Peripherals"),
            new com.algorithms.search.Product("2", "Monitor", "Display"),
            new com.algorithms.search.Product("3", "Mouse", "Peripherals")
        };

        // Linear Search
        com.algorithms.search.Product res1 = SearchAlgorithms.linearSearch(products, "Monitor");
        assertNotNull(res1);
        assertEquals("2", res1.getProductId());

        // Sort for Binary Search
        Arrays.sort(products);
        com.algorithms.search.Product res2 = SearchAlgorithms.binarySearch(products, "Mouse");
        assertNotNull(res2);
        assertEquals("3", res2.getProductId());
    }

    @Test
    public void testOrderSorting() {
        Order[] orders1 = {
            new Order("1", "A", 250.0),
            new Order("2", "B", 100.0),
            new Order("3", "C", 400.0)
        };
        Order[] orders2 = orders1.clone();

        // Bubble Sort
        SortingAlgorithms.bubbleSort(orders1);
        assertEquals(100.0, orders1[0].getTotalPrice());
        assertEquals(250.0, orders1[1].getTotalPrice());
        assertEquals(400.0, orders1[2].getTotalPrice());

        // Quick Sort
        SortingAlgorithms.quickSort(orders2, 0, orders2.length - 1);
        assertEquals(100.0, orders2[0].getTotalPrice());
        assertEquals(250.0, orders2[1].getTotalPrice());
        assertEquals(400.0, orders2[2].getTotalPrice());
    }

    @Test
    public void testEmployeeManagement() {
        EmployeeManager em = new EmployeeManager(5);
        Employee e1 = new Employee("E01", "Alice", "Developer", 80000);
        Employee e2 = new Employee("E02", "Bob", "Designer", 60000);

        assertTrue(em.addEmployee(e1));
        assertTrue(em.addEmployee(e2));
        assertEquals(2, em.getSize());

        assertEquals(e1, em.searchEmployee("E01"));

        assertTrue(em.deleteEmployee("E01"));
        assertEquals(1, em.getSize());
        assertNull(em.searchEmployee("E01"));
    }

    @Test
    public void testTaskManagementLinkedList() {
        TaskLinkedList tlist = new TaskLinkedList();
        Task t1 = new Task("T01", "Database Setup", "Complete");
        Task t2 = new Task("T02", "Auth Implementation", "Pending");

        tlist.addTask(t1);
        tlist.addTask(t2);

        assertEquals(t1, tlist.searchTask("T01"));
        assertEquals(t2, tlist.searchTask("T02"));

        assertTrue(tlist.deleteTask("T01"));
        assertNull(tlist.searchTask("T01"));
    }

    @Test
    public void testLibrarySearch() {
        Book[] books = {
            new Book("B01", "Effective Java", "Joshua Bloch"),
            new Book("B02", "Clean Code", "Robert C. Martin"),
            new Book("B03", "Design Patterns", "Gang of Four")
        };

        // Linear Search
        Book res1 = LibraryManager.linearSearchByTitle(books, "Clean Code");
        assertNotNull(res1);
        assertEquals("B02", res1.getBookId());

        // Binary Search
        Arrays.sort(books);
        Book res2 = LibraryManager.binarySearchByTitle(books, "Design Patterns");
        assertNotNull(res2);
        assertEquals("B03", res2.getBookId());
    }

    @Test
    public void testFinancialForecasting() {
        FinancialForecasting forecaster = new FinancialForecasting();
        double startValue = 1000.0;
        double growthRate = 0.05; // 5%
        int periods = 5;

        double expected = startValue * Math.pow(1 + growthRate, periods);
        double actual = forecaster.calculateFutureValue(startValue, growthRate, periods);
        assertEquals(expected, actual, 0.01);

        double actualOptimized = forecaster.calculateFutureValueOptimized(startValue, growthRate, periods);
        assertEquals(expected, actualOptimized, 0.01);
    }
}
