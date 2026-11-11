
package librarymanagement;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.math.BigDecimal;

/**
 * Represents a borrowing transaction in the Library Management System.
 *
 * The Loan class associates a registered library member with a
 * borrowed library item and maintains the borrowing, due,
 * and return dates.
 *
 * It provides functionality for recording item returns,
 * calculating overdue days, and determining late fees
 * based on the type of library item borrowed.
 */
public class Loan {

    // Unique identifier assigned to the loan
    private String loanID;

    // Member responsible for borrowing the item
    private Member member;

    // Library item associated with the loan
    private LibraryItem item;

    // Date the library item was borrowed
    private LocalDate borrowDate;

    // Date the library item is expected to be returned
    private LocalDate dueDate;

    // Actual return date; null until the item is returned
    private LocalDate returnDate;

    /**
     * Constructs a new Loan with the specified borrowing information.
     *
     * Initializes the loan identifier, member, library item,
     * borrowing date, and due date.
     *
     * The return date is initially null because the item
     * has not yet been returned.
     *
     * @param loanID unique identifier for the loan
     * @param member member borrowing the library item
     * @param item library item being borrowed
     * @param borrowDate date the item was borrowed
     * @param dueDate date the item is expected to be returned
     */
    public Loan(
            String loanID,
            Member member,
            LibraryItem item,
            LocalDate borrowDate,
            LocalDate dueDate) {

        this.loanID = loanID;
        this.member = member;
        this.item = item;
        this.borrowDate = borrowDate;
        this.dueDate = dueDate;
        this.returnDate = null;
    }

    /**
     * Returns the unique identifier of the loan.
     *
     * @return the loan ID
     */
    public String getLoanID() {
        return loanID;
    }

    /**
     * Returns the member associated with the loan.
     *
     * @return the borrowing member
     */
    public Member getMember() {
        return member;
    }

    /**
     * Returns the library item associated with the loan.
     *
     * @return the borrowed library item
     */
    public LibraryItem getItem() {
        return item;
    }

    /**
     * Returns the date the library item was borrowed.
     *
     * @return the borrowing date
     */
    public LocalDate getBorrowDate() {
        return borrowDate;
    }

    /**
     * Returns the date the library item is due.
     *
     * @return the due date
     */
    public LocalDate getDueDate() {
        return dueDate;
    }

    /**
     * Returns the actual return date of the library item.
     *
     * @return the return date, or null if not yet returned
     */
    public LocalDate getReturnDate() {
        return returnDate;
    }

    /**
     * Records the date the borrowed library item was returned.
     *
     * @param returnDate actual date the item was returned
     */
    public void returnItem(LocalDate returnDate) {

        // Store the actual return date
        this.returnDate = returnDate;
    }

    /**
     * Calculates the number of days the library item was overdue.
     *
     * If the item has not been returned, the method returns zero.
     *
     * ChronoUnit.DAYS calculates the difference between the
     * due date and the actual return date.
     *
     * Math.max() ensures that the result is never negative.
     *
     * @return the number of overdue days, or zero if the item
     *         was returned on time, early, or not yet returned
     */
    public long calculateDaysLate() {

        // No overdue days are calculated for unreturned items
        if (returnDate == null) {
            return 0;
        }

        // Calculate the number of days between due date and return date
        long daysLate = ChronoUnit.DAYS.between(dueDate, returnDate);

        // Prevent negative values for early returns
        return Math.max(0, daysLate);
    }

    /**
     * Calculates the total late fee for the borrowed library item.
     *
     * Retrieves the number of overdue days and delegates
     * the fee calculation to the associated LibraryItem.
     *
     * Polymorphism allows Book, Magazine, and DVD objects
     * to apply their own daily late-fee rates.
     *
     * @return the calculated late fee as a BigDecimal
     * @throws ArithmeticException if the overdue day count
     *         exceeds the range of an integer
     */
    public BigDecimal calculateLateFee() {

        // Determine the total number of overdue days
        long daysLate = calculateDaysLate();

        // Convert the overdue day count to an integer
        int days = Math.toIntExact(daysLate);

        // Delegate fee calculation to the appropriate item subclass
        return item.calculateLateFee(days);
    }
}
