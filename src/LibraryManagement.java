import java.util.Scanner;

public class LibraryManagement {
    public static void main(String[] args) {
        Library library = new Library();
        Transaction transaction = Transaction.getTransaction();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Menu:");
            System.out.println("1. Add Member");
            System.out.println("2. Add Book");
            System.out.println("3. Borrow Book");
            System.out.println("4. Return Book");
            System.out.println("5. Show Borrowed Books");
            System.out.println("6. View Transaction History");
            System.out.println("7. Exit");

            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    System.out.print("Enter Member ID: ");
                    int memberId = scanner.nextInt();
                    System.out.print("Enter Member Name: ");
                    String memberName = scanner.next();
                    library.addMember(new Member(memberId, memberName));
                    break;

                case 2:
                    System.out.print("Enter Book ID: ");
                    int bookId = scanner.nextInt();
                    System.out.print("Enter Book Title: ");
                    String bookTitle = scanner.next();
                    try {
                        library.addBook(new Book(bookId, bookTitle));
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case 3:
                    System.out.print("Enter Member ID: ");
                    int borrowMemberId = scanner.nextInt();
                    Member borrowMember = library.findMemberById(borrowMemberId);

                    if (borrowMember == null) {
                        System.out.println("Member not found!");
                        break;
                    }

                    System.out.print("Enter Book ID: ");
                    int borrowBookId = scanner.nextInt();
                    Book borrowBook = library.findBookById(borrowBookId);

                    if (borrowBook == null) {
                        System.out.println("Book not found!");
                        break;
                    }

                    transaction.borrowBook(borrowBook, borrowMember);
                    break;

                case 4:
                    System.out.print("Enter Member ID: ");
                    int returnMemberId = scanner.nextInt();
                    Member returnMember = library.findMemberById(returnMemberId);

                    if (returnMember == null) {
                        System.out.println("Member not found!");
                        break;
                    }

                    System.out.print("Enter Book ID: ");
                    int returnBookId = scanner.nextInt();
                    Book returnBook = library.findBookById(returnBookId);

                    if (returnBook == null) {
                        System.out.println("Book not found!");
                        break;
                    }

                    transaction.returnBook(returnBook, returnMember);
                    break;

                case 5:
                    System.out.print("Enter Member ID: ");
                    int memberToCheckId = scanner.nextInt();
                    Member memberToCheck = library.findMemberById(memberToCheckId);

                    if (memberToCheck == null) {
                        System.out.println("Member not found!");
                    } else {
                        System.out.println("Books borrowed by " + memberToCheck.getName() + ":");
                        for (Book book : memberToCheck.getBorrowedBooks()) {
                            System.out.println("- " + book.getTitle());
                        }
                    }
                    break;

                case 6:
                    transaction.displayTransactionHistory();
                    break;

                case 7:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid choice, please try again.");
            }
        }
    }
}
