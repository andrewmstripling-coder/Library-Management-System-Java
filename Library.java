
package librarymanagement;

import java.util.ArrayList;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.math.BigDecimal;

/**
 * Manages the core operations of the Library Management System.
 *
 * The Library class maintains collections of library items,
 * registered members, borrowing transactions, and fines.
 *
 * It provides methods for registering items and members,
 * borrowing and returning items, calculating late fees,
 * and processing fine payments.
 *
 * Validation is performed to prevent duplicate identifiers,
 * invalid borrowing operations, and duplicate payments.
 */
public class Library {

    // Collection of items belonging to the library
    private ArrayList<LibraryItem> items;

    // Collection of registered library members
    private ArrayList<Member> members;

    // Collection of borrowing transactions
    private ArrayList<Loan> loans;

    // Collection of fines generated from overdue returns
    private ArrayList<Fine> fines;

    /**
     * Constructs a new Library.
     *
     * Initializes empty collections for library items,
     * members, loans, and fines.
     */
    public Library() {

        this.items = new ArrayList<>();
        this.members = new ArrayList<>();
        this.loans = new ArrayList<>();
        this.fines = new ArrayList<>();
    }

    /**
     * Adds a library item to the inventory.
     *
     * Validates that the item is not null, has a valid
     * identifier, and does not duplicate an existing item ID.
     *
     * @param item library item to add
     * @throws IllegalArgumentException if the item is null
     *         or its ID is null or blank
     * @throws IllegalStateException if the item ID already exists
     */
    public void addItem(LibraryItem item) {

        // Validate that the item exists
        if (item == null) {
            throw new IllegalArgumentException(
                "Library item cannot be null.");
        }

        // Validate the item's unique identifier
        if (item.getItemID() == null || item.getItemID().isBlank()) {
            throw new IllegalArgumentException(
                "Library item ID cannot be null or blank.");
        }

        // Prevent duplicate library item IDs
        for (LibraryItem existingItem : items) {
            if (existingItem.getItemID().equals(item.getItemID())) {
                throw new IllegalStateException(
                    "Library item ID already exists.");
            }
        }

        // Add the validated item to the inventory
        items.add(item);
    }

    /**
     * Registers a new member with the library.
     *
     * Ensures that the member is not null, has a valid
     * identifier, and does not duplicate an existing member ID.
     *
     * @param member member to register
     * @throws IllegalArgumentException if the member is null
     *         or its ID is null or blank
     * @throws IllegalStateException if the member ID already exists
     */
    public void registerMember(Member member) {

        // Validate that the member exists
        if (member == null) {
            throw new IllegalArgumentException(
                "Member cannot be null.");
        }

        // Validate the member's unique identifier
        if (member.getMemberID() == null ||
                member.getMemberID().isBlank()) {

            throw new IllegalArgumentException(
                "Member ID cannot be null or blank.");
        }

        // Prevent duplicate member registrations
        for (Member existingMember : members) {
            if (existingMember.getMemberID()
                    .equals(member.getMemberID())) {

                throw new IllegalStateException(
                    "Member ID already exists.");
            }
        }

        // Add the validated member
        members.add(member);
    }

    /**
     * Returns a defensive copy of the library inventory.
     *
     * @return a new ArrayList containing the library items
     */
    public ArrayList<LibraryItem> getItems() {
        return new ArrayList<>(items);
    }

    /**
     * Returns a defensive copy of registered members.
     *
     * @return a new ArrayList containing registered members
     */
    public ArrayList<Member> getMembers() {
        return new ArrayList<>(members);
    }

    /**
     * Returns a defensive copy of borrowing transactions.
     *
     * @return a new ArrayList containing library loans
     */
    public ArrayList<Loan> getLoans() {
        return new ArrayList<>(loans);
    }

    /**
     * Returns a defensive copy of recorded fines.
     *
     * @return a new ArrayList containing library fines
     */
    public ArrayList<Fine> getFines() {
        return new ArrayList<>(fines);
    }

    /**
     * Creates a borrowing transaction for a registered member.
     *
     * Validates the loan identifier, member registration,
     * item ownership, availability, and borrowing dates.
     *
     * Once validated, a Loan object is created and added
     * to the library and member's borrowing records.
     *
     * The borrowed item is marked as unavailable.
     *
     * @param loanID unique identifier for the loan
     * @param member member borrowing the item
     * @param item library item being borrowed
     * @param borrowDate date the item is borrowed
     * @param dueDate expected return date
     * @return the newly created Loan object
     * @throws IllegalArgumentException if the loan ID or
     *         borrowing dates are invalid
     * @throws IllegalStateException if the member is not
     *         registered, the item is unavailable or not
     *         in the library, or the loan ID already exists
     */
    public Loan borrowItem(
            String loanID,
            Member member,
            LibraryItem item,
            LocalDate borrowDate,
            LocalDate dueDate) {

        // Validate the loan identifier
        if (loanID == null || loanID.isBlank()) {
            throw new IllegalArgumentException(
                "Loan ID cannot be null or blank.");
        }

        // Ensure the member is registered
        if (!members.contains(member)) {
            throw new IllegalStateException(
                "Member is not registered.");
        }

        // Ensure the item belongs to this library
        if (!items.contains(item)) {
            throw new IllegalStateException(
                "Item does not belong to this library.");
        }

        // Prevent borrowing an unavailable item
        if (!item.isAvailable()) {
            throw new IllegalStateException(
                "Item is already borrowed.");
        }

        // Prevent duplicate loan identifiers
        for (Loan existingLoan : loans) {
            if (existingLoan.getLoanID().equals(loanID)) {
                throw new IllegalStateException(
                    "Loan ID already exists.");
            }
        }

        // Ensure both borrowing dates are provided
        if (borrowDate == null || dueDate == null) {
            throw new IllegalArgumentException(
                "Borrow date and due date cannot be null.");
        }

        // Ensure the due date is not before the borrow date
        if (dueDate.isBefore(borrowDate)) {
            throw new IllegalArgumentException(
                "Due date cannot be before borrow date.");
        }

        // Create a new borrowing transaction
        Loan loan = new Loan(
            loanID,
            member,
            item,
            borrowDate,
            dueDate
        );

        // Record the loan in the library
        loans.add(loan);

        // Associate the loan with the borrowing member
        member.addLoan(loan);

        // Mark the borrowed item as unavailable
        item.setAvailable(false);

        return loan;
    }

    /**
     * Processes the return of a borrowed library item.
     *
     * Validates the loan and return date before calculating
     * the number of overdue days.
     *
     * If a late fee is owed, a Fine object is created
     * and added to the library's fine collection.
     *
     * After processing, the loan's return date is recorded
     * and the library item becomes available again.
     *
     * @param loan borrowing transaction being completed
     * @param returnDate actual date the item is returned
     * @param fineID identifier used if a fine is generated
     * @throws IllegalArgumentException if the return date
     *         is invalid or a required fine ID is invalid
     * @throws IllegalStateException if the loan is not
     *         registered, was already returned, or the
     *         generated fine ID already exists
     * @throws ArithmeticException if overdue days exceed
     *         the supported integer range
     */
    public void returnItem(
            Loan loan,
            LocalDate returnDate,
            String fineID) {

        // Verify that the loan belongs to this library
        if (!loans.contains(loan)) {
            throw new IllegalStateException(
                "Loan does not belong to this library.");
        }

        // Prevent the same loan from being returned twice
        if (loan.getReturnDate() != null) {
            throw new IllegalStateException(
                "Loan has already been returned.");
        }

        // Ensure a return date is provided
        if (returnDate == null) {
            throw new IllegalArgumentException(
                "Return date cannot be null.");
        }

        // Prevent return dates before the borrowing date
        if (returnDate.isBefore(loan.getBorrowDate())) {
            throw new IllegalArgumentException(
                "Return date cannot be before borrow date.");
        }

        // Calculate the number of overdue days
        long daysLate = ChronoUnit.DAYS.between(
            loan.getDueDate(), returnDate);

        // Ensure early or on-time returns have zero overdue days
        daysLate = Math.max(0, daysLate);

        // Calculate the late fee using the item's specific rate
        BigDecimal lateFee = loan.getItem().calculateLateFee(
            Math.toIntExact(daysLate));

        // Generate a fine only when a positive fee is owed
        if (lateFee.compareTo(BigDecimal.ZERO) > 0) {

            // Validate the fine identifier
            if (fineID == null || fineID.isBlank()) {
                throw new IllegalArgumentException(
                    "Fine ID cannot be null or blank.");
            }

            // Prevent duplicate fine identifiers
            for (Fine existingFine : fines) {
                if (existingFine.getFineID().equals(fineID)) {
                    throw new IllegalStateException(
                        "Fine ID already exists.");
                }
            }

            // Create and record the overdue fine
            Fine fine = new Fine(fineID, loan, lateFee);
            fines.add(fine);
        }

        // Record the actual return date
        loan.returnItem(returnDate);

        // Make the returned item available for borrowing
        loan.getItem().setAvailable(true);
    }

    /**
     * Processes payment of an existing library fine.
     *
     * Verifies that the fine belongs to the library
     * and has not already been paid.
     *
     * After validation, the fine is marked as paid.
     *
     * @param fine fine to mark as paid
     * @throws IllegalStateException if the fine does not
     *         belong to the library or is already paid
     */
    public void payFine(Fine fine) {

        // Ensure the fine belongs to this library
        if (!fines.contains(fine)) {
            throw new IllegalStateException(
                "Fine does not belong to this library.");
        }

        // Prevent duplicate fine payments
        if (fine.isPaid()) {
            throw new IllegalStateException(
                "Fine has already been paid.");
        }

        // Update the fine's payment status
        fine.markAsPaid();
    }
}
