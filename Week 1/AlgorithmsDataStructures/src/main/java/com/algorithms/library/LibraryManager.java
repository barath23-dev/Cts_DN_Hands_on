package com.algorithms.library;

import java.util.Arrays;

/**
 * Manages searches in a library database.
 */
public class LibraryManager {

    /**
     * Linear search to find a book by its title.
     * Time Complexity: O(N)
     */
    public static Book linearSearchByTitle(Book[] books, String targetTitle) {
        if (books == null || targetTitle == null) return null;
        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(targetTitle)) {
                return book;
            }
        }
        return null;
    }

    /**
     * Binary search to find a book by its title.
     * Assumes books array is sorted by title.
     * Time Complexity: O(log N)
     */
    public static Book binarySearchByTitle(Book[] sortedBooks, String targetTitle) {
        if (sortedBooks == null || targetTitle == null) return null;
        int low = 0;
        int high = sortedBooks.length - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            int comp = sortedBooks[mid].getTitle().compareToIgnoreCase(targetTitle);

            if (comp == 0) {
                return sortedBooks[mid];
            } else if (comp < 0) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return null;
    }
}
