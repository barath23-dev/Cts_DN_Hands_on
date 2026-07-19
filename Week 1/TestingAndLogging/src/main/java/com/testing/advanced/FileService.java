package com.testing.advanced;

public class FileService {
    private final FileReader fileReader;
    private final FileWriter fileFileWriter;

    public FileService(FileReader fileReader, FileWriter fileFileWriter) {
        this.fileReader = fileReader;
        this.fileFileWriter = fileFileWriter;
    }

    public String processFile() {
        String content = fileReader.read();
        String processed = "Processed " + content;
        fileFileWriter.write(processed);
        return processed;
    }
}
