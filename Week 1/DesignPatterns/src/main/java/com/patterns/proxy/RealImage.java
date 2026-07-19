package com.patterns.proxy;

public class RealImage implements Image {
    private final String fileName;

    public RealImage(String fileName) {
        this.fileName = fileName;
        loadFromDisk();
    }

    private void loadFromDisk() {
        System.out.println("Loading image from remote server: " + fileName);
        try {
            // Simulate a time-consuming network download
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        System.out.println("Image successfully loaded and buffered: " + fileName);
    }

    @Override
    public void display() {
        System.out.println("Rendering and displaying image: " + fileName);
    }
}
