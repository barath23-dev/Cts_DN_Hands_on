package com.patterns.factorymethod;

public class WordDocument implements Document {
    @Override
    public void open() {
        System.out.println("Opening Microsoft Word document...");
    }

    @Override
    public void save() {
        System.out.println("Saving Word document changes to disk...");
    }

    @Override
    public void close() {
        System.out.println("Closing Word document. Session ended.");
    }
}
