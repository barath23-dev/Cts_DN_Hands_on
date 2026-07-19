package com.testing.mockito;

import com.testing.external.ExternalApi;
import com.testing.service.MyService;
import org.junit.jupiter.api.Test;
import org.mockito.InOrder;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class MockitoBasicTest {

    @Test
    public void testMockingAndStubbing() {
        ExternalApi mockApi = mock(ExternalApi.class);
        when(mockApi.getData()).thenReturn("Mocked Server Response");

        MyService service = new MyService(mockApi);
        assertEquals("Mocked Server Response", service.fetchData());
    }

    @Test
    public void testVerifyingInteractionsAndOrder() {
        ExternalApi mockApi = mock(ExternalApi.class);
        MyService service = new MyService(mockApi);

        service.fetchData();
        service.runAction("ProcessData");

        // Verify calls took place
        verify(mockApi).getData();
        verify(mockApi).executeAction("ProcessData");

        // Verify order of execution
        InOrder inOrder = inOrder(mockApi);
        inOrder.verify(mockApi).getData();
        inOrder.verify(mockApi).executeAction("ProcessData");
    }

    @Test
    public void testArgumentMatching() {
        ExternalApi mockApi = mock(ExternalApi.class);
        when(mockApi.processInput(anyString(), eq(42))).thenReturn("Matched successfully");

        MyService service = new MyService(mockApi);
        String result = service.getProcessedInput("RandomQuery", 42);

        assertEquals("Matched successfully", result);
        verify(mockApi).processInput(anyString(), eq(42));
    }

    @Test
    public void testVoidMethodAndExceptions() {
        ExternalApi mockApi = mock(ExternalApi.class);
        
        // Stub a void method to throw an exception
        doThrow(new RuntimeException("API Connection Refused")).when(mockApi).executeAction("refuse");

        MyService service = new MyService(mockApi);
        
        assertThrows(RuntimeException.class, () -> {
            service.runAction("refuse");
        });
        
        verify(mockApi).executeAction("refuse");
    }

    @Test
    public void testMultipleReturnValues() {
        ExternalApi mockApi = mock(ExternalApi.class);
        when(mockApi.getData())
                .thenReturn("Initial Call Data")
                .thenReturn("Secondary Call Data");

        MyService service = new MyService(mockApi);
        assertEquals("Initial Call Data", service.fetchData());
        assertEquals("Secondary Call Data", service.fetchData());
    }
}
