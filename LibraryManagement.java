import java.util.*;

// Represents a Book in the Library
class Book {
    int id;
    String title;
    String author;
    boolean isAvailable;

    Book(int id, String title, String author) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.isAvailable = true;
    }

    void showBook() {
        System.out.println("ID: " + id + " | Title: " + title + " | Author: " + author + " | Available: " + isAvailable);
    }
}

// Represents a User who borrows books
class User {
    String name;
    List<Book> borrowedBooks;

    User(String name) {
        this.name = name;
        borrowedBooks = new ArrayList<>();
    }

    void borrowBook(Book book) {
        borrowedBooks.add(book);
        book.isAvailable = false;
        System.out.println("You borrowed: " + book.title);
    }

    void viewHistory() {
        if (borrowedBooks.isEmpty()) {
            System.out.println("You haven't borrowed any books yet.");
            return;
        }

        System.out.println("Your Borrow History:");
        for (Book b : borrowedBooks) {
            System.out.println("- " + b.title + " by " + b.author);
        }
    }
}

// Represents the Library and its functions
class Library {
    Scanner input = new Scanner(System.in);
    Map<Integer, Book> bookList = new HashMap<>();
    int bookIdCounter = 1;

    // Librarian menu
    void librarianMenu() {
        while (true) {
            System.out.println("\n--- Librarian Menu ---");
            System.out.println("1. Add Book");
            System.out.println("2. Edit Book");
            System.out.println("3. Delete Book");
            System.out.println("4. View All Books");
            System.out.println("5. Go Back");

            System.out.print("Enter choice: ");
            int choice = input.nextInt();
            input.nextLine();

            switch (choice) {
                case 1 -> addBook();
                case 2 -> editBook();
                case 3 -> deleteBook();
                case 4 -> showAllBooks();
                case 5 -> { return; }
                default -> System.out.println("Please enter a valid option.");
            }
        }
    }

    // User menu
    void userMenu(User user) {
        while (true) {
            System.out.println("\n--- User Menu ---");
            System.out.println("1. View Available Books");
            System.out.println("2. Borrow Book");
            System.out.println("3. View Borrow History");
            System.out.println("4. Go Back");

            System.out.print("Enter choice: ");
            int choice = input.nextInt();

            switch (choice) {
                case 1 -> showAllBooks();
                case 2 -> borrowBook(user);
                case 3 -> user.viewHistory();
                case 4 -> { return; }
                default -> System.out.println("Please enter a valid option.");
            }
        }
    }

    // Add new book
    void addBook() {
        System.out.print("Enter book title: ");
        String title = input.nextLine();

        System.out.print("Enter author name: ");
        String author = input.nextLine();

        Book book = new Book(bookIdCounter++, title, author);
        bookList.put(book.id, book);

        System.out.println("Book added successfully.");
    }

    // Edit existing book
    void editBook() {
        System.out.print("Enter book ID to edit: ");
        int id = input.nextInt();
        input.nextLine();

        if (bookList.containsKey(id)) {
            Book book = bookList.get(id);

            System.out.print("Enter new title: ");
            book.title = input.nextLine();

            System.out.print("Enter new author: ");
            book.author = input.nextLine();

            System.out.println("Book updated successfully.");
        } else {
            System.out.println("Book not found.");
        }
    }

    // Delete a book
    void deleteBook() {
        System.out.print("Enter book ID to delete: ");
        int id = input.nextInt();

        if (bookList.remove(id) != null) {
            System.out.println("Book deleted.");
        } else {
            System.out.println("Book not found.");
        }
    }

    // Show all books
    void showAllBooks() {
        if (bookList.isEmpty()) {
            System.out.println("No books available right now.");
            return;
        }

        System.out.println("\n--- Book Catalog ---");
        for (Book book : bookList.values()) {
            book.showBook();
        }
    }

    // Let user borrow a book
    void borrowBook(User user) {
        System.out.print("Enter book ID to borrow: ");
        int id = input.nextInt();

        if (bookList.containsKey(id)) {
            Book book = bookList.get(id);

            if (book.isAvailable) {
                user.borrowBook(book);
            } else {
                System.out.println("Sorry, this book is already borrowed.");
            }
        } else {
            System.out.println("Book not found.");
        }
    }
}

// Main class to run the app
public class LibraryManagement {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Library library = new Library();

        while (true) {
            System.out.println("\n=== Online Library Management ===");
            System.out.println("1. Librarian");
            System.out.println("2. User");
            System.out.println("3. Exit");

            System.out.print("Enter your role: ");
            int role = input.nextInt();
            input.nextLine();

            switch (role) {
                case 1 -> library.librarianMenu();
                case 2 -> {
                    System.out.print("Enter your name: ");
                    String name = input.nextLine();
                    User user = new User(name);
                    library.userMenu(user);
                }
                case 3 -> {
                    System.out.println("Thanks for using the system. Goodbye!");
                    return;
                }
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
