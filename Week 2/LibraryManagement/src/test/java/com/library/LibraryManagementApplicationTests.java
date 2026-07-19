package com.library;

import com.library.service.BookService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class LibraryManagementApplicationTests {

    @Autowired
    private ApplicationContext context;

    @Test
    void contextLoads() {
        assertNotNull(context);
        assertTrue(context.containsBean("bookServiceSetter"));
        assertTrue(context.containsBean("bookServiceConstructor"));
        
        BookService setterService = (BookService) context.getBean("bookServiceSetter");
        assertNotNull(setterService.getBooks());
    }
}
