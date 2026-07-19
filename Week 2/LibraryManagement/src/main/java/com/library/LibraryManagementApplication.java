package com.library;

import com.library.service.BookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource("classpath:applicationContext.xml")
public class LibraryManagementApplication implements CommandLineRunner {

    private static final Logger LOGGER = LoggerFactory.getLogger(LibraryManagementApplication.class);

    private final ApplicationContext context;

    public LibraryManagementApplication(ApplicationContext context) {
        this.context = context;
    }

    public static void main(String[] args) {
        SpringApplication.run(LibraryManagementApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        LOGGER.info("Inside main runner - verifying XML-configured Beans:");
        
        BookService setterService = (BookService) context.getBean("bookServiceSetter");
        LOGGER.info("Setter DI wired Books: " + setterService.getBooks());

        BookService constructorService = (BookService) context.getBean("bookServiceConstructor");
        LOGGER.info("Constructor DI wired Books: " + constructorService.getBooks());
    }
}
