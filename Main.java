import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JOptionPane;
import javax.swing.BorderFactory;
import javax.swing.SwingUtilities;
import javax.swing.JLabel;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import javax.swing.border.EmptyBorder;

enum Genre {
    FICTION, NONFICTION, PROGRAMMING, SCIENCE, HISTORY
}
class Book {
    String title, author;
    Genre genre;
    boolean isAvailable = true;
    Book(String title, String author, Genre genre) {
        this.title = title;
        this.author = author;
        this.genre = genre;
    }
}
class BorrowRecord {
    String bookTitle;
    Genre genre;
    Date date;
    String action;
    BorrowRecord(String bookTitle, Genre genre, String action) {
        this.bookTitle = bookTitle;
        this.genre = genre;
        this.action = action;
        this.date = new Date();
    }
}

class Library {
    ArrayList<Book> books = new ArrayList<>();
    ArrayList<BorrowRecord> history = new ArrayList<>();
    Library() {
        books.add(new Book("Java Programming", "James Gosling", Genre.PROGRAMMING));
        books.add(new Book("Effective Java", "Joshua Bloch", Genre.PROGRAMMING));
        books.add(new Book("Clean Code", "Robert C. Martin", Genre.PROGRAMMING));
        books.add(new Book("Head First Java", "Kathy Sierra", Genre.PROGRAMMING));
        books.add(new Book("A Brief History of Time", "Stephen Hawking", Genre.SCIENCE));
        books.add(new Book("The Selfish Gene", "Richard Dawkins", Genre.SCIENCE));
        books.add(new Book("1984", "George Orwell", Genre.FICTION));
        books.add(new Book("The Great Gatsby", "F. Scott Fitzgerald", Genre.FICTION));
        books.add(new Book("The Catcher in the Rye", "J.D. Salinger", Genre.FICTION));
        books.add(new Book("To Kill a Mockingbird", "Harper Lee", Genre.FICTION));
        books.add(new Book("Sapiens", "Yuval Noah Harari", Genre.HISTORY));
        books.add(new Book("Educated", "Tara Westover", Genre.NONFICTION));
        books.add(new Book("Becoming", "Michelle Obama", Genre.NONFICTION));
        books.add(new Book("The Immortal Life of Henrietta Lacks", "Rebecca Skloot", Genre.NONFICTION));
        books.add(new Book("The Code Book", "Simon Singh", Genre.SCIENCE));
        books.add(new Book("The Art of Computer Programming", "Donald Knuth", Genre.PROGRAMMING));
    }
    ArrayList<Book> getBooksByGenre(Genre genre) {
        ArrayList<Book> list = new ArrayList<>();
        for (Book b : books) {
            if (b.genre == genre) list.add(b);
        }
        return list;
    }
    Book searchBook(String title, Genre genre) {
        for (Book b : books) {
            if (b.title.equalsIgnoreCase(title) && b.genre == genre)
                return b;
        }
        return null;
    }
    boolean borrowBook(String title, Genre genre) {
        Book b = searchBook(title, genre);
        if (b != null && b.isAvailable) {
            b.isAvailable = false;
            history.add(new BorrowRecord(title, genre, "Borrowed"));
            return true;
        }
        return false;
    }
    boolean returnBook(String title, Genre genre) {
        Book b = searchBook(title, genre);
        if (b != null && !b.isAvailable) {
            b.isAvailable = true;
            history.add(new BorrowRecord(title, genre, "Returned"));
            return true;
        }
        return false;
    }
    ArrayList<BorrowRecord> getHistory() {
        return history;
    }
    ArrayList<Book> getBooks() {
        return books;
    }
}

public class Main extends JFrame {
    Library library = new Library();
    JTextArea outputArea = new JTextArea(12, 40);
    public Main() {
        setTitle("Library Management System");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLocationRelativeTo(null);
        JPanel mainPanel = new JPanel(new BorderLayout(15, 15));
        mainPanel.setBackground(new Color(240, 248, 255));
        mainPanel.setBorder(new EmptyBorder(15, 15, 15, 15));
        setContentPane(mainPanel);
        JLabel titleLabel = new JLabel("Library Management System", JLabel.CENTER);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 32));
        titleLabel.setForeground(new Color(0, 51, 102));
        mainPanel.add(titleLabel, BorderLayout.NORTH);
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(6, 1, 10, 10));
        buttonPanel.setBackground(new Color(224, 236, 255));
        buttonPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        JButton searchBtn = new JButton("Search Book");
        JButton borrowBtn = new JButton("Borrow Book");
        JButton returnBtn = new JButton("Return Book");
        JButton viewBooksBtn = new JButton("View All Books");
        JButton historyBtn = new JButton("View Borrow History");
        JButton clearBtn = new JButton("Clear Output");
        Font btnFont = new Font("Segoe UI", Font.PLAIN, 18);
        for (JButton btn : new JButton[]{searchBtn, borrowBtn, returnBtn, viewBooksBtn, historyBtn, clearBtn}) {
            btn.setFont(btnFont);
            btn.setBackground(new Color(0, 51, 102));
            btn.setForeground(new Color(0, 0, 124));
            btn.setFocusPainted(false);
            btn.setBorder(BorderFactory.createLineBorder(new Color(0, 51, 102), 2));
            buttonPanel.add(btn);
        }
        mainPanel.add(buttonPanel, BorderLayout.WEST);
        outputArea.setEditable(false);
        outputArea.setFont(new Font("Consolas", Font.PLAIN, 16));
        outputArea.setBackground(new Color(255, 255, 255));
        outputArea.setForeground(new Color(0, 51, 102));
        outputArea.setBorder(BorderFactory.createLineBorder(new Color(0, 51, 102), 2));
        JScrollPane scrollPane = new JScrollPane(outputArea);
        mainPanel.add(scrollPane, BorderLayout.CENTER);
        searchBtn.addActionListener(e -> {
            Genre genre = chooseGenre();
            if (genre == null) return;
            ArrayList<Book> books = library.getBooksByGenre(genre);
            if (books.isEmpty()) {
                outputArea.setText("No books found in this genre.");
                return;
            }
            String[] titles = books.stream().map(b -> b.title).toArray(String[]::new);
            String title = (String) JOptionPane.showInputDialog(this, "Select a book to search:", "Choose Book",
                    JOptionPane.PLAIN_MESSAGE, null, titles, titles[0]);
            if (title != null) {
                Book b = library.searchBook(title, genre);
                if (b != null) {
                    outputArea.setText("Book found:\n\nTitle: " + b.title + "\nAuthor: " + b.author + "\nGenre: " + b.genre +
                            "\nAvailable: " + (b.isAvailable ? "Yes" : "No"));
                } else {
                    outputArea.setText("Book not found.");
                }
            }
        });
        borrowBtn.addActionListener(e -> {
            Genre genre = chooseGenre();
            if (genre == null) return;
            ArrayList<Book> books = library.getBooksByGenre(genre);
            books.removeIf(b -> !b.isAvailable);
            if (books.isEmpty()) {
                outputArea.setText("No available books in this genre.");
                return;
            }
            String[] titles = books.stream().map(b -> b.title).toArray(String[]::new);
            String title = (String) JOptionPane.showInputDialog(this, "Select a book to borrow:", "Choose Book",
                    JOptionPane.PLAIN_MESSAGE, null, titles, titles[0]);
            if (title != null) {
                boolean success = library.borrowBook(title, genre);
                outputArea.setText(success ? "Book borrowed successfully." : "Book not available or not found.");
            }
        });
        returnBtn.addActionListener(e -> {
            Genre genre = chooseGenre();
            if (genre == null) return;
            ArrayList<Book> books = library.getBooksByGenre(genre);
            books.removeIf(b -> b.isAvailable);
            if (books.isEmpty()) {
                outputArea.setText("No borrowed books in this genre.");
                return;
            }
            String[] titles = books.stream().map(b -> b.title).toArray(String[]::new);
            String title = (String) JOptionPane.showInputDialog(this, "Select a book to return:", "Choose Book",
                    JOptionPane.PLAIN_MESSAGE, null, titles, titles[0]);
            if (title != null) {
                boolean success = library.returnBook(title, genre);
                outputArea.setText(success ? "Book returned successfully." : "Book not found or not borrowed.");
            }
        });
        viewBooksBtn.addActionListener(e -> {
            StringBuilder sb = new StringBuilder("Books in Library by Genre:\n\n");
            for (Genre genre : Genre.values()) {
                sb.append("[").append(genre).append("]\n");
                ArrayList<Book> books = library.getBooksByGenre(genre);
                if (books.isEmpty()) {
                    sb.append("  No books in this genre.\n");
                } else {
                    for (Book b : books) {
                        sb.append("  • ").append(b.title).append(" by ").append(b.author)
                                .append(" - ").append(b.isAvailable ? "Available" : "Borrowed").append("\n");
                    }
                }
                sb.append("\n");
            }
            outputArea.setText(sb.toString());
        });
        historyBtn.addActionListener(e -> {
            StringBuilder sb = new StringBuilder("Borrowing History:\n\n");
            for (BorrowRecord r : library.getHistory()) {
                sb.append("• ").append(r.bookTitle).append(" (").append(r.genre).append(") - ").append(r.action)
                        .append(" on ").append(r.date).append("\n");
            }
            if (library.getHistory().isEmpty()) sb.append("No history yet.");
            outputArea.setText(sb.toString());
        });
        clearBtn.addActionListener(e -> outputArea.setText(""));
        outputArea.setText("Welcome to the Library Management System!\n\nUse the buttons on the left to manage books.\n");
        setVisible(true);
    }
    private Genre chooseGenre() {
        Genre[] genres = Genre.values();
        String[] genreNames = Arrays.stream(genres).map(Enum::name).toArray(String[]::new);
        String selected = (String) JOptionPane.showInputDialog(this, "Select a genre:", "Choose Genre",
                JOptionPane.PLAIN_MESSAGE, null, genreNames, genreNames[0]);
        if (selected == null) return null;
        return Genre.valueOf(selected);
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(Main::new);
    }
}
