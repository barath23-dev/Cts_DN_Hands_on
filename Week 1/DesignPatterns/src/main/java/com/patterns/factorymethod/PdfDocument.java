package com.patterns.factorymethod;

public class PdfDocument implements Document {
    @Override
    public void open() {
        System.out.println("Opening PDF document in Acrobat Reader...");
    }

    @Override
    public void save() {
        System.out.println("Exporting PDF changes. Document saved.");
    }

    @Override
    public void close() {
        System.out.println("Closing PDF document viewer.");
    }
}
