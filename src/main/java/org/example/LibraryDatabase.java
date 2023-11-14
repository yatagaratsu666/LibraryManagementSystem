package org.example;

public interface LibraryDatabase {
    void saveBookData(Book book);
    void saveStudentData(Student student);
    Book getBookById(String id);
    Student getStudentById(String id);
    // Additional methods to interact with the database
}
