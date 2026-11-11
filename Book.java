
package librarymanagement;

import java.math.BigDecimal;

/**
 * Represents a book in the Library Management System.
 *
 * The Book class extends LibraryItem and inherits common
 * attributes such as item ID, title, publication year,
 * and availability status.
 *
 * Each book also contains an author, ISBN, and genre.
 *
 * Books are charged a late fee of $0.25 per overdue day.
 */
public class Book extends LibraryItem {

    // Name of the book's author
    private String author;

    // International Standard Book Number
    private String isbn;

    // Literary or subject category of the book
    private String genre;

    /**
     * Constructs a new Book with the specified information.
     *
     * Initializes the inherited library item attributes
     * and the book-specific attributes.
     *
     * @param itemID unique identifier for the book
     * @param title title of the book
     * @param yearPublished year the book was published
     * @param author name of the book's author
     * @param isbn International Standard Book Number
     * @param genre category or genre of the book
     */
    public Book(
            String itemID,
            String title,
            int yearPublished,
            String author,
            String isbn,
            String genre) {

        super(itemID, title, yearPublished);

        this.author = author;
        this.isbn = isbn;
        this.genre = genre;
    }

    /**
     * Returns the name of the book's author.
     *
     * @return the author's name
     */
    public String getAuthor() {
        return author;
    }

    /**
     * Returns the International Standard Book Number.
     *
     * @return the book's ISBN
     */
    public String getIsbn() {
        return isbn;
    }

    /**
     * Returns the genre of the book.
     *
     * @return the book's genre
     */
    public String getGenre() {
        return genre;
    }

    /**
     * Calculates the late fee for an overdue book.
     *
     * Books are charged $0.25 for each day overdue.
     * No fee is charged when daysLate is zero or negative.
     *
     * BigDecimal is used to maintain precision when
     * performing monetary calculations.
     *
     * @param daysLate number of days the book is overdue
     * @return the total late fee as a BigDecimal
     */
    @Override
    public BigDecimal calculateLateFee(int daysLate) {

        // Return zero when the book is not overdue
        if (daysLate <= 0) {
            return BigDecimal.ZERO;
        }

        // Define the daily late fee
        BigDecimal dailyFee = new BigDecimal("0.25");

        // Calculate the total fee based on overdue days
        return dailyFee.multiply(BigDecimal.valueOf(daysLate));
    }
}
