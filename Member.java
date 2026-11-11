
package librarymanagement;

import java.util.ArrayList;

/**
 * Represents a registered member in the Library Management System.
 *
 * The Member class stores personal information about a library
 * member, including their unique ID, name, email address,
 * and phone number.
 *
 * Each member maintains a collection of Loan objects representing
 * their borrowing records.
 *
 * The class provides methods for adding and removing loans
 * and retrieving a defensive copy of the loan collection.
 */
public class Member {

    // Unique identifier assigned to the library member
    private String memberID;

    // Member's first name
    private String firstName;

    // Member's last name
    private String lastName;

    // Member's email address
    private String email;

    // Member's contact phone number
    private String phoneNumber;

    // Collection of loans associated with the member
    private ArrayList<Loan> loans;

    /**
     * Constructs a new Member with the specified information.
     *
     * Initializes the member's personal information and creates
     * an empty collection to store their borrowing records.
     *
     * @param memberID unique identifier for the member
     * @param firstName member's first name
     * @param lastName member's last name
     * @param email member's email address
     * @param phoneNumber member's contact phone number
     */
    public Member(
            String memberID,
            String firstName,
            String lastName,
            String email,
            String phoneNumber) {

        this.memberID = memberID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;

        // Initialize an empty collection of member loans
        this.loans = new ArrayList<>();
    }

    /**
     * Returns the unique identifier of the member.
     *
     * @return the member ID
     */
    public String getMemberID() {
        return memberID;
    }

    /**
     * Returns the member's first name.
     *
     * @return the first name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Returns the member's last name.
     *
     * @return the last name
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Returns the member's email address.
     *
     * @return the email address
     */
    public String getEmail() {
        return email;
    }

    /**
     * Returns the member's phone number.
     *
     * @return the phone number
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Adds a loan to the member's borrowing records.
     *
     * This method is used when a member borrows a library item.
     *
     * @param loan the Loan object to add
     */
    public void addLoan(Loan loan) {
        loans.add(loan);
    }

    /**
     * Removes a loan from the member's borrowing records.
     *
     * @param loan the Loan object to remove
     */
    public void removeLoan(Loan loan) {
        loans.remove(loan);
    }

    /**
     * Returns a defensive copy of the member's loan collection.
     *
     * Creating a new ArrayList prevents external code from
     * directly modifying the member's internal collection.
     *
     * Changes made to the returned list do not affect
     * the original list stored in this class.
     *
     * @return a new ArrayList containing the member's loans
     */
    public ArrayList<Loan> getLoans() {
        return new ArrayList<>(loans);
    }
}
