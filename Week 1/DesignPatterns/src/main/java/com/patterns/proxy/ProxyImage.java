package com.patterns.proxy;

public class ProxyImage implements Image {
    private final String fileName;
    private RealImage realImage;

    public ProxyImage(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public void display() {
        // Lazy loading and caching
        if (realImage == null) {
            realImage = new RealImage(fileName);
        } else {
            System.out.println("Displaying cached version of image: " + fileName);
        }
        realImage.display();
    }
}
