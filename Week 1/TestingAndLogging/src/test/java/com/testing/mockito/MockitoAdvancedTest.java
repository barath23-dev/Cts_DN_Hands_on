package com.testing.mockito;

import com.testing.advanced.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class MockitoAdvancedTest {

    @Test
    public void testMockingDatabaseRepository() {
        Repository mockRepo = mock(Repository.class);
        when(mockRepo.getData()).thenReturn("DatabaseRecord");

        Service service = new Service(mockRepo);
        assertEquals("Processed DatabaseRecord", service.processData());
    }

    @Test
    public void testMockingRestClient() {
        RestClient mockClient = mock(RestClient.class);
        when(mockClient.getResponse()).thenReturn("APIPayload");

        ApiService service = new ApiService(mockClient);
        assertEquals("Fetched APIPayload", service.fetchData());
    }

    @Test
    public void testMockingFileIO() {
        FileReader reader = mock(FileReader.class);
        FileWriter writer = mock(FileWriter.class);

        when(reader.read()).thenReturn("Hello File");
        doNothing().when(writer).write(anyString());

        FileService service = new FileService(reader, writer);
        assertEquals("Processed Hello File", service.processFile());

        verify(reader).read();
        verify(writer).write("Processed Hello File");
    }

    @Test
    public void testMockingNetworkClient() {
        NetworkClient client = mock(NetworkClient.class);
        when(client.connect()).thenReturn("192.168.1.1:8080");

        NetworkService service = new NetworkService(client);
        assertEquals("Connected to 192.168.1.1:8080", service.connectToServer());
    }

    @Test
    public void testMockingMultipleReturns() {
        Repository mockRepo = mock(Repository.class);
        when(mockRepo.getData())
                .thenReturn("FirstValue")
                .thenReturn("SecondValue");

        Service service = new Service(mockRepo);
        assertEquals("Processed FirstValue", service.processData());
        assertEquals("Processed SecondValue", service.processData());
    }
}
