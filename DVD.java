
package librarymanagement;

import java.math.BigDecimal;

/**
 * Represents a DVD in the Library Management System.
 *
 * The DVD class extends LibraryItem and inherits common
 * attributes such as item ID, title, publication year,
 * and availability status.
 *
 * Each DVD also contains a director, running time,
 * and content rating.
 *
 * DVDs are charged a late fee of $1.00 per overdue day.
 */
public class DVD extends LibraryItem {

    // Name of the DVD's director
    private String director;

    // Total running time of the DVD in minutes
    private int durationMinutes;

    // Content rating assigned to the DVD
    private String rating;

    /**
     * Constructs a new DVD with the specified information.
     *
     * Initializes the inherited library item attributes
     * and the DVD-specific attributes.
     *
     * @param itemID unique identifier for the DVD
     * @param title title of the DVD
     * @param yearPublished year the DVD was published
     * @param director name of the DVD's director
     * @param durationMinutes running time in minutes
     * @param rating content rating of the DVD
     */
    public DVD(
            String itemID,
            String title,
            int yearPublished,
            String director,
            int durationMinutes,
            String rating) {

        super(itemID, title, yearPublished);

        this.director = director;
        this.durationMinutes = durationMinutes;
        this.rating = rating;
    }

    /**
     * Returns the name of the DVD's director.
     *
     * @return the director's name
     */
    public String getDirector() {
        return director;
    }

    /**
     * Returns the running time of the DVD.
     *
     * @return the duration in minutes
     */
    public int getDurationMinutes() {
        return durationMinutes;
    }

    /**
     * Returns the content rating of the DVD.
     *
     * @return the DVD's content rating
     */
    public String getRating() {
        return rating;
    }

    /**
     * Calculates the late fee for an overdue DVD.
     *
     * DVDs are charged $1.00 for each day overdue.
     * No fee is charged when daysLate is zero or negative.
     *
     * BigDecimal is used to maintain precision when
     * performing monetary calculations.
     *
     * @param daysLate number of days the DVD is overdue
     * @return the total late fee as a BigDecimal
     */
    @Override
    public BigDecimal calculateLateFee(int daysLate) {

        // Return zero when the DVD is not overdue
        if (daysLate <= 0) {
            return BigDecimal.ZERO;
        }

        // Define the daily late fee
        BigDecimal dailyFee = new BigDecimal("1.00");

        // Calculate the total fee based on overdue days
        return dailyFee.multiply(BigDecimal.valueOf(daysLate));
    }
}
