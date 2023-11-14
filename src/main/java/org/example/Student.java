package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Student {
    private String id;
    private String name;
    private List<Book> borrowedBooks;

    public Student(String id, String name) {
        this.id = id;
        this.name = name;
        this.borrowedBooks = new ArrayList<>();
    }

    public boolean borrowBook(Book book) {
        if (book.isAvailable()) {
            borrowedBooks.add(book);
            book.markAsUnavailable();
            return true;
        }
        return false;
    }

    public void returnBook(Book book) {
        Optional<Book> foundBook = borrowedBooks.stream()
                .filter(b -> b.equals(book))
                .findFirst();

        if (foundBook.isPresent()) {
            borrowedBooks.remove(book);
            book.markAsAvailable();
        }
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Book> getBorrowedBooks() {
        return borrowedBooks;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBorrowedBooks(List<Book> borrowedBooks) {
        this.borrowedBooks = borrowedBooks;
    }
}
