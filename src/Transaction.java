import java.util.ArrayList;
import java.util.List;

public class Transaction {
    private static Transaction instance;
    private List<Book> borrowedBooks = new ArrayList<>();

    private Transaction() {}

    public static Transaction getTransaction() {
        if (instance == null) {
            instance = new Transaction();
        }
        return instance;
    }

    public boolean isBookAvailable(Book book) {
        return !borrowedBooks.contains(book);
    }

    public boolean borrowBook(Book book, Member member) {
        if (isBookAvailable(book)) {
            borrowedBooks.add(book);
            System.out.println("Book borrowed: " + book.getTitle());
            return true;
        } else {
            System.out.println("Book is not available: " + book.getTitle());
            return false;
        }
    }

    public boolean returnBook(Book book, Member member) {
        if (borrowedBooks.contains(book)) {
            borrowedBooks.remove(book);
            System.out.println("Book returned: " + book.getTitle());
            return true;
        } else {
            System.out.println("Book was not borrowed: " + book.getTitle());
            return false;
        }
    }

    public void displayTransactionHistory() {
        System.out.println("Transaction History:");
        for (Book book : borrowedBooks) {
            System.out.println("- " + book.getTitle());
        }
    }
}
