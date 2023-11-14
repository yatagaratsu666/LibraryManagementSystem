import org.example.Book;
import org.example.Student;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;
import java.util.List;
import org.example.LibraryDatabase;
import org.example.LoanManager;

public class StudentTest {
    private Student student;
    private Book book1, book2;
    
    private LoanManager LM;
    @Mock
    private LibraryDatabase LDataBase;
    

    @BeforeEach
    void setUp() {
        book1 = new Book("B001", "Effective Java", "Joshua Bloch");
        book2 = new Book("B002", "Clean Code", "Robert C. Martin");
        student = new Student("123", "John Doe");
        MockitoAnnotations.openMocks(this);
        LM = new LoanManager(LDataBase);
    }

    @Test
    void testBorrowBookWhenBookIsAvailable() {
        book1.markAsAvailable();
            when(LDataBase.getBookById("0")).thenReturn(book1);
        boolean getBook = book1.isAvailable();
        assertTrue(getBook);
    }

    @Test
    void testBorrowBookWhenBookIsUnavailable() {
        book2.markAsUnavailable();
            when(LDataBase.getBookById("0")).thenReturn(book2);
        boolean getBook = book2.isAvailable();
        assertFalse(getBook);
    }

    @Test
    void testReturnBook() {
        book1.markAsUnavailable();
            when(LDataBase.getBookById("0")).thenReturn(book1);
        student.returnBook(book1);
        assertFalse(book1.isAvailable());
    }

    @Test
    void testReturnBookNotBorrowed() {
        book1.markAsAvailable();
            when(LDataBase.getBookById("0")).thenReturn(book1);
        student.returnBook(book1);
        assertFalse(book1.isAvailable());
    }

    @Test
    void testMultipleBookBorrowAndReturn() {
        student.borrowBook(book1);
        student.borrowBook(book2);

        book1.markAsAvailable();
        book2.markAsUnavailable();

            when(LDataBase.getBookById("0")).thenReturn(book1);
        student.borrowBook(book1);
            when(LDataBase.getBookById("1")).thenReturn(book2);
        student.returnBook(book2);
        
        List<Book> borrowedBooks = student.getBorrowedBooks();
        assertEquals(2, borrowedBooks.size());
        
        assertFalse(borrowedBooks.contains(book2));
    }
}

