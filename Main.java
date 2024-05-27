import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

class Book {
    String title;
    String author;
    String ISBN;
    boolean availability;

    public Book(String title, String author, String ISBN, boolean availability) {
        this.title = title;
        this.author = author;
        this.ISBN = ISBN;
        this.availability = availability;
    }
}

class Library {
    ArrayList<Book> books;

    public Library() {
        books = new ArrayList<>();
    }

    public void addBook(Book book) {
        books.add(book);
    }

    public void removeBook(String ISBN) {
        books.removeIf(book -> book.ISBN.equals(ISBN));
    }

    public String displayBooks() {
        StringBuilder sb = new StringBuilder();
        for (Book book : books) {
            sb.append("Title: ").append(book.title)
                    .append(", Author: ").append(book.author)
                    .append(", ISBN: ").append(book.ISBN)
                    .append(", Availability: ").append(book.availability ? "Available" : "Not Available")
                    .append("\n");
        }
        return sb.toString();
    }
}

public class Main {
    private JFrame frame;
    private JTextField titleField, authorField, isbnField;
    private JCheckBox availabilityCheckBox;
    private JTextArea displayArea;
    private Library library;

    public Main() {
        library = new Library();
        frame = new JFrame("Library Management System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // Input Panel
        JPanel inputPanel = new JPanel(new GridLayout(0, 2));
        titleField = new JTextField();
        authorField = new JTextField();
        isbnField = new JTextField();
        availabilityCheckBox = new JCheckBox("Available");
        inputPanel.add(new JLabel("Title:"));
        inputPanel.add(titleField);
        inputPanel.add(new JLabel("Author:"));
        inputPanel.add(authorField);
        inputPanel.add(new JLabel("ISBN:"));
        inputPanel.add(isbnField);
        inputPanel.add(new JLabel("Availability:"));
        inputPanel.add(availabilityCheckBox);

        // Button Panel
        JPanel buttonPanel = new JPanel();
        JButton addButton = new JButton("Add Book");
        JButton removeButton = new JButton("Remove Book");
        JButton displayButton = new JButton("Display Books");
        buttonPanel.add(addButton);
        buttonPanel.add(removeButton);
        buttonPanel.add(displayButton);

        // Display Area
        displayArea = new JTextArea(10, 30);
        displayArea.setEditable(false);

        // Add Action Listeners
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String title = titleField.getText();
                String author = authorField.getText();
                String isbn = isbnField.getText();
                boolean availability = availabilityCheckBox.isSelected();
                library.addBook(new Book(title, author, isbn, availability));
                titleField.setText("");
                authorField.setText("");
                isbnField.setText("");
                availabilityCheckBox.setSelected(false);
            }
        });

        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String isbn = isbnField.getText();
                library.removeBook(isbn);
                isbnField.setText("");
            }
        });

        displayButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayArea.setText(library.displayBooks());
            }
        });

        // Add components to frame
        frame.add(inputPanel, BorderLayout.NORTH);
        frame.add(buttonPanel, BorderLayout.CENTER);
        frame.add(new JScrollPane(displayArea), BorderLayout.SOUTH);

        // Finalize and show frame
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Main();
            }
        });
    }
}
