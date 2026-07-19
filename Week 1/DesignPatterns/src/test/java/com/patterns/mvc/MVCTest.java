package com.patterns.mvc;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MVCTest {

    @Test
    public void testStudentControllerUpdate() {
        // Create initial Student Model
        Student model = new Student("S101", "Alice Johnson", "A");
        StudentView view = new StudentView();
        
        StudentController controller = new StudentController(model, view);

        assertEquals("Alice Johnson", controller.getStudentName());
        assertEquals("S101", controller.getStudentId());
        assertEquals("A", controller.getStudentGrade());

        // Update details via Controller
        controller.setStudentName("Alice Smith");
        controller.setStudentGrade("A+");

        assertEquals("Alice Smith", model.getName(), "Model state should reflect controller changes");
        assertEquals("A+", model.getGrade(), "Model state should reflect controller changes");

        System.out.println("--- Testing MVC View Output ---");
        controller.updateView();
    }
}
