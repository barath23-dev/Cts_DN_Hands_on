package com.algorithms.task;

/**
 * Singly Linked List to manage dynamic tasks.
 */
public class TaskLinkedList {
    private Node head;

    // Node wrapper class
    private static class Node {
        Task task;
        Node next;

        Node(Task task) {
            this.task = task;
            this.next = null;
        }
    }

    /**
     * Add a task to the end of the list.
     * Time Complexity: O(N) (can be optimized to O(1) by maintaining a tail pointer)
     */
    public void addTask(Task task) {
        Node newNode = new Node(task);
        if (head == null) {
            head = newNode;
            return;
        }
        Node current = head;
        while (current.next != null) {
            current = current.next;
        }
        current.next = newNode;
    }

    /**
     * Search for a task by ID.
     * Time Complexity: O(N)
     */
    public Task searchTask(String taskId) {
        Node current = head;
        while (current != null) {
            if (current.task.getTaskId().equals(taskId)) {
                return current.task;
            }
            current = current.next;
        }
        return null;
    }

    /**
     * Traverse and print all tasks.
     * Time Complexity: O(N)
     */
    public void traverseTasks() {
        System.out.println("--- Task List ---");
        Node current = head;
        while (current != null) {
            System.out.println(current.task);
            current = current.next;
        }
    }

    /**
     * Delete a task by ID.
     * Time Complexity: O(N)
     */
    public boolean deleteTask(String taskId) {
        if (head == null) return false;

        // If head is the target node
        if (head.task.getTaskId().equals(taskId)) {
            head = head.next;
            return true;
        }

        Node current = head;
        while (current.next != null) {
            if (current.next.task.getTaskId().equals(taskId)) {
                current.next = current.next.next;
                return true;
            }
            current = current.next;
        }

        System.out.printf("Task with ID %s not found for deletion.%n", taskId);
        return false;
    }
}
