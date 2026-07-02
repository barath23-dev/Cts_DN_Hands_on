package com.patterns.factorymethod;

/**
 * Abstract factory class for creating Document objects.
 * Declares the Factory Method createDocument().
 */
public abstract class DocumentFactory {
    // Factory Method declaration
    public abstract Document createDocument();

    // Helper method to create, open, and work on a document
    public Document processDocument() {
        Document doc = createDocument();
        doc.open();
        return doc;
    }
}
