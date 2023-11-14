package org.example;

import java.util.ArrayList;
import java.util.List;

import java.util.Optional;

public class LoanManager {
    private List<Book> books;
    private List<Student> students;
    private LibraryDatabase libraryDatabase;

    public LoanManager(LibraryDatabase libraryDatabase) {
        this.books = new ArrayList<>();
        this.students = new ArrayList<>();
        this.libraryDatabase = libraryDatabase;
    }

    public void addBook(Book book) {
        books.add(book);
        libraryDatabase.saveBookData(book);
    }

    public void registerStudent(Student student) {
        students.add(student);
        libraryDatabase.saveStudentData(student);
    }

    public boolean lendBook(String bookId, String studentId) {
        Optional<Book> book = books.stream()
                .filter(b -> b.getId().equals(bookId) && b.isAvailable())
                .findFirst();

        Optional<Student> student = students.stream()
                .filter(s -> s.getId().equals(studentId))
                .findFirst();

        if (book.isPresent() && student.isPresent()) {
            Book b = book.get();
            Student s = student.get();

            b.markAsUnavailable();
            s.borrowBook(b);
            libraryDatabase.saveBookData(b);
            libraryDatabase.saveStudentData(s);

            return true;
        }

        return false;
    }

    public void receiveReturnedBook(String bookId, String studentId) {
        Optional<Book> book = books.stream()
                .filter(b -> b.getId().equals(bookId))
                .findFirst();

        Optional<Student> student = students.stream()
                .filter(s -> s.getId().equals(studentId))
                .findFirst();

        if (book.isPresent() && student.isPresent()) {
            Book b = book.get();
            Student s = student.get();

            b.markAsAvailable();
            s.returnBook(b);
            libraryDatabase.saveBookData(b);
            libraryDatabase.saveStudentData(s);
        }
    }

    // Getters for books and students (useful for testing)
    public List<Book> getBooks() {
        return books;
    }

    public List<Student> getStudents() {
        return students;
    }
}

