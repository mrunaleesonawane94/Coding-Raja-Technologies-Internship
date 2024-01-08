import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

class Book {
    private String title;
    private String author;
    private int availableCopies;

    public Book(String title, String author, int availableCopies) {
        this.title = title;
        this.author = author;
        this.availableCopies = availableCopies;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public int getAvailableCopies() {
        return availableCopies;
    }

    public void decreaseAvailableCopies() {
        if (availableCopies > 0) {
            availableCopies--;
        }
    }

    public void increaseAvailableCopies() {
        availableCopies++;
    }
}

class Patron {
    private String name;

    public Patron(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

class Library {
    private Map<String, Book> books;
    private Map<String, Patron> patrons;
    private List<String> borrowedRecords;

    public Library() {
        this.books = new HashMap<>();
        this.patrons = new HashMap<>();
        this.borrowedRecords = new ArrayList<>();
    }

    public void addBook(String isbn, String title, String author, int availableCopies) {
        books.put(isbn, new Book(title, author, availableCopies));
        System.out.println("Book added successfully.");
    }

    public void addPatron(String patronId, String name) {
        patrons.put(patronId, new Patron(name));
        System.out.println("Patron added successfully.");
    }

    public void borrowBook(String patronId, String isbn) {
        if (patrons.containsKey(patronId) && books.containsKey(isbn)) {
            Book book = books.get(isbn);
            if (book.getAvailableCopies() > 0) {
                book.decreaseAvailableCopies();
                borrowedRecords.add(patronId + " borrowed " + book.getTitle());
                System.out.println("Book borrowed successfully.");
            } else {
                System.out.println("Sorry, the book is not available for borrowing.");
            }
        } else {
            System.out.println("Invalid patron or book.");
        }
    }

    public void returnBook(String patronId, String isbn) {
        if (patrons.containsKey(patronId) && books.containsKey(isbn)) {
            Book book = books.get(isbn);
            book.increaseAvailableCopies();
            borrowedRecords.remove(patronId + " borrowed " + book.getTitle());
            System.out.println("Book returned successfully.");
        } else {
            System.out.println("Invalid patron or book.");
        }
    }

    public void displayBooks() {
        System.out.println("\nBooks in the library:");
        for (Book book : books.values()) {
            System.out.println(book.getTitle() + " by " + book.getAuthor() +
                    " - Available Copies: " + book.getAvailableCopies());
        }
    }

    public void displayPatrons() {
        System.out.println("\nPatrons in the library:");
        for (Patron patron : patrons.values()) {
            System.out.println("Patron: " + patron.getName());
        }
    }

    public void displayBorrowedRecords() {
        System.out.println("\nBorrowed records:");
        for (String record : borrowedRecords) {
            System.out.println(record);
        }
    }
}

public class LibraryManagementSystem {
    public static void main(String[] args) {
        Library library = new Library();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n1. Add Book\n2. Add Patron\n3. Borrow Book\n4. Return Book\n5. Display Books\n6. Display Patrons\n7. Display Borrowed Records\n8. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter ISBN: ");
                    String isbn = scanner.nextLine();
                    System.out.print("Enter Title: ");
                    String title = scanner.nextLine();
                    System.out.print("Enter Author: ");
                    String author = scanner.nextLine();
                    System.out.print("Enter Available Copies: ");
                    int availableCopies = scanner.nextInt();
                    library.addBook(isbn, title, author, availableCopies);
                    break;
                case 2:
                    System.out.print("Enter Patron ID: ");
                    String patronId = scanner.nextLine();
                    System.out.print("Enter Patron Name: ");
                    String patronName = scanner.nextLine();
                    library.addPatron(patronId, patronName);
                    break;
                case 3:
                    System.out.print("Enter Patron ID: ");
                    String borrowPatronId = scanner.nextLine();
                    System.out.print("Enter ISBN of the book to borrow: ");
                    String borrowIsbn = scanner.nextLine();
                    library.borrowBook(borrowPatronId, borrowIsbn);
                    break;
                case 4:
                    System.out.print("Enter Patron ID: ");
                    String returnPatronId = scanner.nextLine();
                    System.out.print("Enter ISBN of the book to return: ");
                    String returnIsbn = scanner.nextLine();
                    library.returnBook(returnPatronId, returnIsbn);
                    break;
                case 5:
                    library.displayBooks();
                    break;
                case 6:
                    library.displayPatrons();
                    break;
                case 7:
                    library.displayBorrowedRecords();
                    break;
                case 8:
                    System.out.println("Exiting the library management system. Goodbye!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
            }
        }
    }
}