
package librarymanagement;

import java.time.LocalDate;

/**
 * Demonstrates and tests the main functionality of the
 * Library Management System.
 *
 * This class uses a main method to execute 15 test scenarios
 * covering library initialization, item registration,
 * member management, borrowing, returns, overdue fines,
 * exception handling, and polymorphism.
 *
 * Expected exceptions are caught and displayed to verify
 * that invalid operations are rejected.
 *
 * Tests use console output rather than an automated
 * assertion framework.
 */
public class Test {

    /**
     * Executes the Library Management System test scenarios.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {

        // Test 1: Verify library initialization
        Library library = new Library();

        System.out.println("Library created successfully.");

        // Test 2: Create and register a book
        Book book1 = new Book(
            "B001",
            "Effective Java",
            2018,
            "Joshua Bloch",
            "9780134685991",
            "Programming"
        );

        library.addItem(book1);

        // Verify inventory count, title, and initial availability
        System.out.println("Total library items: "
            + library.getItems().size());

        System.out.println("Book title: "
            + library.getItems().get(0).getTitle());

        System.out.println("Book available: "
            + book1.isAvailable());

        // Test 3: Register a library member
        Member member1 = new Member(
            "M001",
            "John",
            "Smith",
            "john.smith@email.com",
            "2025550123"
        );

        library.registerMember(member1);

        // Verify successful member registration
        System.out.println("Total registered members: "
            + library.getMembers().size());

        System.out.println("Member ID: "
            + member1.getMemberID());

        // Test 4: Borrow a library book
        Loan loan1 = library.borrowItem(
            "L001",
            member1,
            book1,
            LocalDate.of(2026, 11, 11),
            LocalDate.of(2026, 11, 25)
        );

        // Verify loan creation and item availability
        System.out.println("Total loans: "
            + library.getLoans().size());

        System.out.println("Member loans: "
            + member1.getLoans().size());

        System.out.println("Loan ID: "
            + loan1.getLoanID());

        System.out.println("Book available after borrowing: "
            + book1.isAvailable());

        // Test 5: Prevent borrowing an unavailable item
        try {
            library.borrowItem(
                "L002",
                member1,
                book1,
                LocalDate.of(2026, 11, 12),
                LocalDate.of(2026, 11, 26)
            );

            System.out.println(
                "ERROR: Duplicate borrowing allowed.");

        } catch (IllegalStateException e) {

            // Confirm that duplicate borrowing is rejected
            System.out.println(
                "Expected exception: " + e.getMessage());
        }

        // Verify that the failed attempt did not create a loan
        System.out.println("Total loans after failed attempt: "
            + library.getLoans().size());

        System.out.println("Book available: "
            + book1.isAvailable());

        // Test 6: Return a book before its due date
        library.returnItem(
            loan1,
            LocalDate.of(2026, 11, 20),
            null
        );

        // Verify return date, availability, and absence of fines
        System.out.println("Book available after return: "
            + book1.isAvailable());

        System.out.println("Loan return date: "
            + loan1.getReturnDate());

        System.out.println("Total fines: "
            + library.getFines().size());

        // Test 7: Process a late return and calculate its fine
        Loan loan3 = library.borrowItem(
            "L003",
            member1,
            book1,
            LocalDate.of(2026, 11, 21),
            LocalDate.of(2026, 11, 25)
        );

        // Return the book five days after its due date
        library.returnItem(
            loan3,
            LocalDate.of(2026, 11, 30),
            "F001"
        );

        // Verify overdue days, fee amount, and item availability
        System.out.println("Days late: "
            + loan3.calculateDaysLate());

        System.out.println("Late fee: $"
            + loan3.calculateLateFee());

        System.out.println("Total fines: "
            + library.getFines().size());

        System.out.println("Book available after late return: "
            + book1.isAvailable());

        // Test 8: Process payment of an overdue fine
        Fine fine1 = library.getFines().get(0);

        System.out.println("Fine ID: "
            + fine1.getFineID());

        System.out.println("Fine amount: $"
            + fine1.getAmount());

        System.out.println("Fine paid before payment: "
            + fine1.isPaid());

        // Mark the fine as paid
        library.payFine(fine1);

        System.out.println("Fine paid after payment: "
            + fine1.isPaid());

        // Test 9: Prevent duplicate library item identifiers
        try {
            Book duplicateBook = new Book(
                "B001",
                "Java Programming",
                2024,
                "Jane Smith",
                "9781234567890",
                "Programming"
            );

            library.addItem(duplicateBook);

            System.out.println(
                "ERROR: Duplicate book ID allowed.");

        } catch (IllegalStateException e) {

            // Confirm that duplicate item IDs are rejected
            System.out.println(
                "Expected exception: " + e.getMessage());
        }

        System.out.println(
            "Total library items after duplicate attempt: "
            + library.getItems().size());

        // Test 10: Prevent duplicate member identifiers
        try {
            Member duplicateMember = new Member(
                "M001",
                "Jane",
                "Doe",
                "jane.doe@email.com",
                "2025550199"
            );

            library.registerMember(duplicateMember);

            System.out.println(
                "ERROR: Duplicate member ID allowed.");

        } catch (IllegalStateException e) {

            // Confirm that duplicate member IDs are rejected
            System.out.println(
                "Expected exception: " + e.getMessage());
        }

        System.out.println(
            "Total members after duplicate attempt: "
            + library.getMembers().size());

        // Test 11: Prevent duplicate loan identifiers

        // Register another book for the borrowing test
        Book book2 = new Book(
            "B002",
            "Clean Code",
            2008,
            "Robert C. Martin",
            "9780132350884",
            "Programming"
        );

        library.addItem(book2);

        try {
            // L001 already belongs to an existing loan
            library.borrowItem(
                "L001",
                member1,
                book2,
                LocalDate.of(2026, 12, 1),
                LocalDate.of(2026, 12, 15)
            );

            System.out.println(
                "ERROR: Duplicate loan ID allowed.");

        } catch (IllegalStateException e) {

            // Confirm that duplicate loan IDs are rejected
            System.out.println(
                "Expected exception: " + e.getMessage());
        }

        System.out.println("Total loans after duplicate attempt: "
            + library.getLoans().size());

        System.out.println("Second book available: "
            + book2.isAvailable());

        // Test 12A: Reject a due date before the borrow date
        try {
            library.borrowItem(
                "L004",
                member1,
                book2,
                LocalDate.of(2026, 12, 15),
                LocalDate.of(2026, 12, 1)
            );

            System.out.println(
                "ERROR: Invalid due date allowed.");

        } catch (IllegalArgumentException e) {

            // Confirm that invalid borrowing dates are rejected
            System.out.println(
                "Expected exception: " + e.getMessage());
        }

        // Test 12B: Reject a return date before the borrow date

        // Create a valid loan for return-date validation
        Loan loan4 = library.borrowItem(
            "L004",
            member1,
            book2,
            LocalDate.of(2026, 12, 1),
            LocalDate.of(2026, 12, 15)
        );

        try {
            library.returnItem(
                loan4,
                LocalDate.of(2026, 11, 25),
                null
            );

            System.out.println(
                "ERROR: Invalid return date allowed.");

        } catch (IllegalArgumentException e) {

            // Confirm that invalid return dates are rejected
            System.out.println(
                "Expected exception: " + e.getMessage());
        }

        // Verify that the invalid return did not complete the loan
        System.out.println("Total loans after date validation: "
            + library.getLoans().size());

        System.out.println("Book2 available: "
            + book2.isAvailable());

        System.out.println("Loan4 return date: "
            + loan4.getReturnDate());

        // Test 13A: Successfully return the second book
        library.returnItem(
            loan4,
            LocalDate.of(2026, 12, 10),
            null
        );

        System.out.println("Book2 available after return: "
            + book2.isAvailable());

        System.out.println("Loan4 return date: "
            + loan4.getReturnDate());

        // Test 13B: Prevent returning the same loan twice
        try {
            library.returnItem(
                loan4,
                LocalDate.of(2026, 12, 12),
                null
            );

            System.out.println(
                "ERROR: Double return allowed.");

        } catch (IllegalStateException e) {

            // Confirm that duplicate returns are rejected
            System.out.println(
                "Expected exception: " + e.getMessage());
        }

        // Verify that the original return record remains unchanged
        System.out.println(
            "Book2 available after duplicate return: "
            + book2.isAvailable());

        System.out.println("Loan4 final return date: "
            + loan4.getReturnDate());

        System.out.println("Total fines: "
            + library.getFines().size());

        // Test 14: Prevent duplicate fine payments
        try {
            // Fine F001 was already paid in Test 8
            library.payFine(fine1);

            System.out.println(
                "ERROR: Duplicate fine payment allowed.");

        } catch (IllegalStateException e) {

            // Confirm that repeat payments are rejected
            System.out.println(
                "Expected exception: " + e.getMessage());
        }

        System.out.println("Fine ID: "
            + fine1.getFineID());

        System.out.println("Fine paid status: "
            + fine1.isPaid());

        System.out.println(
            "Total fines after duplicate payment: "
            + library.getFines().size());

        // Test 15: Verify polymorphism in late-fee calculations

        // Store different item subclasses using LibraryItem references
        LibraryItem testBook = new Book(
            "B003",
            "Java Fundamentals",
            2023,
            "James Wilson",
            "9781234567891",
            "Programming"
        );

        LibraryItem testMagazine = new Magazine(
            "M002",
            "Science Today",
            2025,
            12,
            "Science Publishing",
            "Science"
        );

        LibraryItem testDVD = new DVD(
            "D001",
            "Java Programming Tutorial",
            2022,
            "Michael Johnson",
            120,
            "G"
        );

        // Each subclass applies its own overdue fee rate
        // Book: $0.25/day, Magazine: $0.15/day, DVD: $1.00/day
        System.out.println("Book late fee: $"
            + testBook.calculateLateFee(5));

        System.out.println("Magazine late fee: $"
            + testMagazine.calculateLateFee(5));

        System.out.println("DVD late fee: $"
            + testDVD.calculateLateFee(5));
    }
}
