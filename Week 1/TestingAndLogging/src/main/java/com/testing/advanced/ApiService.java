package com.testing.advanced;

public class ApiService {
    private final RestClient restClient;

    public ApiService(RestClient restClient) {
        this.restClient = restClient;
    }

    public String fetchData() {
        String data = restClient.getResponse();
        return "Fetched " + data;
    }
}
