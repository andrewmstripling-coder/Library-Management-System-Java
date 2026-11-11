
package librarymanagement;

import java.math.BigDecimal;

/**
 * Represents a late-return fine in the Library Management System.
 *
 * The Fine class stores information about a financial penalty
 * associated with a library loan.
 *
 * Each fine contains a unique identifier, the associated Loan,
 * the monetary amount owed, and a payment status.
 *
 * Newly created fines are marked as unpaid by default.
 * The payment status can be updated when a fine is paid.
 */
public class Fine {

    // Unique identifier assigned to the fine
    private String fineID;

    // Loan associated with the fine
    private Loan loan;

    // Monetary amount of the fine
    private BigDecimal amount;

    // Indicates whether the fine has been paid
    private boolean paid;

    /**
     * Constructs a new Fine with the specified information.
     *
     * Initializes the fine identifier, associated loan,
     * and monetary amount.
     *
     * Newly created fines are marked as unpaid.
     *
     * @param fineID unique identifier for the fine
     * @param loan loan associated with the fine
     * @param amount monetary amount of the fine
     */
    public Fine(
            String fineID,
            Loan loan,
            BigDecimal amount) {

        this.fineID = fineID;
        this.loan = loan;
        this.amount = amount;

        // Initialize the fine as unpaid
        this.paid = false;
    }

    /**
     * Returns the unique identifier of the fine.
     *
     * @return the fine ID
     */
    public String getFineID() {
        return fineID;
    }

    /**
     * Returns the loan associated with the fine.
     *
     * @return the associated Loan object
     */
    public Loan getLoan() {
        return loan;
    }

    /**
     * Returns the monetary amount of the fine.
     *
     * @return the fine amount as a BigDecimal
     */
    public BigDecimal getAmount() {
        return amount;
    }

    /**
     * Checks whether the fine has been paid.
     *
     * @return true if the fine is paid, false otherwise
     */
    public boolean isPaid() {
        return paid;
    }

    /**
     * Marks the fine as paid.
     *
     * Updates the payment status to true.
     *
     * Payment validation is handled by the Library class
     * before this method is called.
     */
    public void markAsPaid() {

        // Update the fine's payment status
        paid = true;
    }
}
