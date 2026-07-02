package com.patterns.factorymethod;

/**
 * Base interface representing a Document in the system.
 */
public interface Document {
    void open();
    void save();
    void close();
}
