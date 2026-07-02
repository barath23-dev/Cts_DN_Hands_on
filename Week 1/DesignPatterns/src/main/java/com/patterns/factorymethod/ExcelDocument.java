package com.patterns.factorymethod;

public class ExcelDocument implements Document {
    @Override
    public void open() {
        System.out.println("Opening Excel spreadsheet in Microsoft Excel...");
    }

    @Override
    public void save() {
        System.out.println("Recalculating formulas and saving Excel document...");
    }

    @Override
    public void close() {
        System.out.println("Closing Excel document workspace.");
    }
}
