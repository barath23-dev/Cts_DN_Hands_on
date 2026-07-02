package com.testing.service;

import com.testing.external.ExternalApi;

public class MyService {
    private final ExternalApi externalApi;

    public MyService(ExternalApi externalApi) {
        this.externalApi = externalApi;
    }

    public String fetchData() {
        return externalApi.getData();
    }

    public void runAction(String input) {
        externalApi.executeAction(input);
    }

    public String getProcessedInput(String input, int value) {
        return externalApi.processInput(input, value);
    }
}
