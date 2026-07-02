package com.patterns.factorymethod;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class FactoryMethodTest {

    @Test
    public void testWordDocumentCreation() {
        DocumentFactory factory = new WordDocumentFactory();
        Document doc = factory.createDocument();

        assertNotNull(doc, "Document should not be null");
        assertTrue(doc instanceof WordDocument, "Document created should be an instance of WordDocument");
    }

    @Test
    public void testPdfDocumentCreation() {
        DocumentFactory factory = new PdfDocumentFactory();
        Document doc = factory.createDocument();

        assertNotNull(doc, "Document should not be null");
        assertTrue(doc instanceof PdfDocument, "Document created should be an instance of PdfDocument");
    }

    @Test
    public void testExcelDocumentCreation() {
        DocumentFactory factory = new ExcelDocumentFactory();
        Document doc = factory.createDocument();

        assertNotNull(doc, "Document should not be null");
        assertTrue(doc instanceof ExcelDocument, "Document created should be an instance of ExcelDocument");
    }

    @Test
    public void testProcessDocumentFlow() {
        DocumentFactory factory = new PdfDocumentFactory();
        Document doc = factory.processDocument();
        
        assertNotNull(doc, "Processed document should not be null");
        assertTrue(doc instanceof PdfDocument);
    }
}
