
package librarymanagement;

import java.math.BigDecimal;

/**
 * Represents a magazine in the Library Management System.
 *
 * The Magazine class extends LibraryItem and inherits
 * common attributes such as item ID, title, publication
 * year, and availability status.
 *
 * Each magazine also contains an issue number, publisher,
 * and category.
 *
 * Magazines are charged a late fee of $0.15 per overdue day.
 */
public class Magazine extends LibraryItem {

    // Issue number identifying the magazine edition
    private int issueNumber;

    // Name of the magazine's publisher
    private String publisher;

    // Subject category of the magazine
    private String category;

    /**
     * Constructs a new Magazine with the specified information.
     *
     * Initializes the inherited library item attributes
     * and the magazine-specific attributes.
     *
     * @param itemID unique identifier for the magazine
     * @param title title of the magazine
     * @param yearPublished year the magazine was published
     * @param issueNumber issue number of the magazine
     * @param publisher name of the magazine's publisher
     * @param category subject category of the magazine
     */
    public Magazine(
            String itemID,
            String title,
            int yearPublished,
            int issueNumber,
            String publisher,
            String category) {

        super(itemID, title, yearPublished);

        this.issueNumber = issueNumber;
        this.publisher = publisher;
        this.category = category;
    }

    /**
     * Returns the issue number of the magazine.
     *
     * @return the magazine's issue number
     */
    public int getIssueNumber() {
        return issueNumber;
    }

    /**
     * Returns the name of the magazine's publisher.
     *
     * @return the publisher's name
     */
    public String getPublisher() {
        return publisher;
    }

    /**
     * Returns the subject category of the magazine.
     *
     * @return the magazine's category
     */
    public String getCategory() {
        return category;
    }

    /**
     * Calculates the late fee for an overdue magazine.
     *
     * Magazines are charged $0.15 for each day overdue.
     * No fee is charged when daysLate is zero or negative.
     *
     * BigDecimal is used to maintain precision when
     * performing monetary calculations.
     *
     * @param daysLate number of days the magazine is overdue
     * @return the total late fee as a BigDecimal
     */
    @Override
    public BigDecimal calculateLateFee(int daysLate) {

        // Return zero when the magazine is not overdue
        if (daysLate <= 0) {
            return BigDecimal.ZERO;
        }

        // Define the daily late fee
        BigDecimal dailyFee = new BigDecimal("0.15");

        // Calculate the total fee based on overdue days
        return dailyFee.multiply(BigDecimal.valueOf(daysLate));
    }
}
