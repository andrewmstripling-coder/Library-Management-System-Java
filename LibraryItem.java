
package librarymanagement;

import java.math.BigDecimal;

/**
 * Represents a general item in the Library Management System.
 *
 * This abstract class defines the common attributes and behaviors
 * shared by all library items, including books, magazines, and DVDs.
 *
 * Each library item has a unique identifier, title, publication year,
 * and availability status.
 *
 * Subclasses must implement the calculateLateFee() method to define
 * their own overdue fee calculations.
 */
public abstract class LibraryItem {

    // Unique identifier for the library item
    private String itemID;

    // Title of the library item
    private String title;

    // Year the library item was published
    private int yearPublished;

    // Indicates whether the item is available for borrowing
    private boolean available;

    /**
     * Constructs a new library item with the specified information.
     *
     * Newly created library items are available for borrowing
     * by default.
     *
     * @param itemID unique identifier for the library item
     * @param title title of the library item
     * @param yearPublished year the item was published
     */
    public LibraryItem(
            String itemID,
            String title,
            int yearPublished) {

        this.itemID = itemID;
        this.title = title;
        this.yearPublished = yearPublished;
        this.available = true;
    }

    /**
     * Returns the unique identifier of the library item.
     *
     * @return the item ID
     */
    public String getItemID() {
        return itemID;
    }

    /**
     * Returns the title of the library item.
     *
     * @return the item title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Returns the publication year of the library item.
     *
     * @return the publication year
     */
    public int getYearPublished() {
        return yearPublished;
    }

    /**
     * Checks whether the library item is available for borrowing.
     *
     * @return true if the item is available, false otherwise
     */
    public boolean isAvailable() {
        return available;
    }

    /**
     * Updates the availability status of the library item.
     *
     * This method is used when an item is borrowed or returned.
     *
     * @param available the new availability status
     */
    public void setAvailable(boolean available) {
        this.available = available;
    }

    /**
     * Calculates the overdue fee for the library item.
     *
     * Each subclass implements its own late-fee calculation
     * based on the number of days the item is overdue.
     *
     * @param daysLate number of days the item is overdue
     * @return the calculated late fee as a BigDecimal
     */
    public abstract BigDecimal calculateLateFee(int daysLate);

}
