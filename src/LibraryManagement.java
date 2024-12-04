import java.util.Scanner;

public class LibraryManagement {
    private Library library = new Library();
    private Transaction transaction = Transaction.getTransaction();

    public static void main(String[] args) {
        new LibraryManagement().run();
    }

    private void run() {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("===========================");
            System.out.println("Library Management System");
            System.out.println("1. Add Member");
            System.out.println("2. Add Book");
            System.out.println("3. Borrow Book");
            System.out.println("4. Return Book");
            System.out.println("5. View Borrowed Books");
            System.out.println("6. View Transaction History");
            System.out.println("7. Exit");
            System.out.println("===========================");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter member ID: ");
                    int memberId = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Enter member name: ");
                    String memberName = scanner.nextLine();
                    Member newMember = new Member(memberId, memberName);
                    if (library.addMember(newMember)) {
                        System.out.println("Member added successfully.");
                    }
                    break;
                case 2:
                    System.out.print("Enter book ID: ");
                    int bookId = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Enter book title: ");
                    String bookTitle = scanner.nextLine();
                    Book newBook = new Book(bookId, bookTitle);
                    if (library.addBook(newBook)) {
                        System.out.println("Book added successfully.");
                    }
                    break;
                case 3:
                    System.out.print("Enter member ID: ");
                    memberId = scanner.nextInt();
                    System.out.print("Enter book ID: ");
                    bookId = scanner.nextInt();
                    scanner.nextLine();
                    Member member = library.findMemberById(memberId);
                    Book book = library.findBookById(bookId);
                    if (member != null && book != null) {
                        transaction.borrowBook(book, member);
                    }
                    break;
                case 4:
                    System.out.print("Enter member ID: ");
                    memberId = scanner.nextInt();
                    System.out.print("Enter book ID: ");
                    bookId = scanner.nextInt();
                    scanner.nextLine();
                    member = library.findMemberById(memberId);
                    book = library.findBookById(bookId);
                    if (member != null && book != null) {
                        transaction.returnBook(book, member);
                    }
                    break;
                case 5:
                    System.out.print("Enter member ID: ");
                    memberId = scanner.nextInt();
                    scanner.nextLine();
                    member = library.findMemberById(memberId);
                    if (member != null) {
                        System.out.println("Books borrowed by " + member.getName() + ":");
                        for (Book b : member.getBorrowedBooks()) {
                            System.out.println(" - " + b.getTitle());
                        }
                    }
                    break;
                case 6:
                    transaction.displayTransactionHistory();
                    break;
                case 7:
                    System.out.println("Exiting. Goodbye!");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }
}
