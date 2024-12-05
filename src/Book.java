public class Book {
    private int id;
    private String title;
    private boolean isBorrowed;

    // Constructor
    public Book(int id, String title) throws Exception {
        if (!isValidId(id)) {
            throw new Exception("Invalid book ID: " + id);
        }
        this.id = id;
        this.title = title;
        this.isBorrowed = false; // Default state
    }

    // Method to check if the book ID is valid
    public boolean isValidId(int id) {
        return id >= 100 && id <= 999;
    }

    // Getter for ID
    public int getId() {
        return id;
    }

    // Getter for Title
    public String getTitle() {
        return title;
    }

    // Method to check if the book is borrowed
    public boolean isBorrowed() {
        return isBorrowed;
    }

    // Method to set the borrowed status
    public void setBorrowed(boolean isBorrowed) {
        this.isBorrowed = isBorrowed;
    }
}
