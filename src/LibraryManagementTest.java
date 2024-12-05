import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;

public class LibraryManagementTest {
    private Transaction transaction;

    @Before
    public void setUp() {
        transaction = Transaction.getTransaction();
    }

    @Test
    public void testBookId() throws Exception {
        assertEquals(true, new Book(100, "Programming").isValidId(100));
        assertEquals(true, new Book(999, "Math").isValidId(999));

        try {
            new Book(1000, "AI");
            fail("Expected an exception for invalid ID");
        } catch (Exception e) {
            assertEquals("Invalid book ID: 1000", e.getMessage());
        }

        try {
            new Book(99, "Physics");
            fail("Expected an exception for invalid ID");
        } catch (Exception e) {
            assertEquals("Invalid book ID: 99", e.getMessage());
        }
    }

    @Test
    public void testBorrowReturn() throws Exception {
        Book book = new Book(101, "Programming");
        Member member = new Member(1111, "George");

        assertTrue(transaction.isBookAvailable(book));
        assertTrue(transaction.borrowBook(book, member));
        assertFalse(transaction.isBookAvailable(book));
        assertFalse(transaction.borrowBook(book, member));
        assertTrue(transaction.returnBook(book, member));
        assertTrue(transaction.isBookAvailable(book));
        assertFalse(transaction.returnBook(book, member));
    }

    @Test
    public void testSingletonTransaction() throws Exception {
        Constructor<Transaction> constructor = Transaction.class.getDeclaredConstructor();
        int modifiers = constructor.getModifiers();
        assertEquals(Modifier.PRIVATE, modifiers);
    }
}
