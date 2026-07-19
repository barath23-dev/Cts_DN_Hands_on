package com.testing.advanced;

public class NetworkService {
    private final NetworkClient networkClient;

    public NetworkService(NetworkClient networkClient) {
        this.networkClient = networkClient;
    }

    public String connectToServer() {
        String conn = networkClient.connect();
        return "Connected to " + conn;
    }
}
