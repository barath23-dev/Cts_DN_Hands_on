package com.patterns.mvc;

/**
 * View class in MVC representing user interface representation.
 */
public class StudentView {
    public void displayStudentDetails(String studentName, String studentId, String studentGrade) {
        System.out.println("----------------------------------------");
        System.out.println("STUDENT DETAILS RECORD:");
        System.out.println("Name:  " + studentName);
        System.out.println("ID:    " + studentId);
        System.out.println("Grade: " + studentGrade);
        System.out.println("----------------------------------------");
    }
}
