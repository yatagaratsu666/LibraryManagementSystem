import org.example.Book;
import org.example.Student;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

public class StudentTest {
    private Student student;
    private Book book1, book2;

    @BeforeEach
    void setUp() {
        student = new Student("123", "John Doe");
        book1 = new Book("B001", "Effective Java", "Joshua Bloch");
        book2 = new Book("B002", "Clean Code", "Robert C. Martin");
    }

    @Test
    void testBorrowBookWhenBookIsAvailable() {
        assertTrue(book1.isAvailable());
        assertTrue(student.borrowBook(book1));
        assertFalse(book1.isAvailable());
        assertTrue(student.getBorrowedBooks().contains(book1));
    }

    @Test
    void testBorrowBookWhenBookIsUnavailable() {
        book1.markAsUnavailable();
        assertFalse(student.borrowBook(book1));
        assertFalse(student.getBorrowedBooks().contains(book1));
    }

    @Test
    void testReturnBook() {
        student.borrowBook(book1);
        assertTrue(student.getBorrowedBooks().contains(book1));
        student.returnBook(book1);
        assertFalse(student.getBorrowedBooks().contains(book1));
        assertTrue(book1.isAvailable());
    }

    @Test
    void testReturnBookNotBorrowed() {
        assertFalse(student.getBorrowedBooks().contains(book2));
        student.returnBook(book2); // Trying to return a book not borrowed
        assertFalse(student.getBorrowedBooks().contains(book2));
    }

    @Test
    void testMultipleBookBorrowAndReturn() {
        student.borrowBook(book1);
        student.borrowBook(book2);
        List<Book> borrowedBooks = student.getBorrowedBooks();
        assertTrue(borrowedBooks.contains(book1) && borrowedBooks.contains(book2));
        assertEquals(2, borrowedBooks.size());

        student.returnBook(book1);
        assertFalse(borrowedBooks.contains(book1));
        assertTrue(borrowedBooks.contains(book2));
        assertEquals(1, borrowedBooks.size());
    }
}

