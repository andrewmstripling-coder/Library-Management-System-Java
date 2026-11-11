Library Management System | Java

A comprehensive Java-based Library Management System that efficiently manages library inventory, member registration, borrowing and return transactions, and overdue fine processing.

This project showcases essential Object-Oriented Programming (OOP) principles—abstraction, inheritance, polymorphism, and encapsulation—while leveraging exception handling, Java collections, and accurate date-based financial calculations.

Features

* Inventory Management: Seamlessly register, organize, and manage books, magazines, and DVDs.
* Member Registration: Capture and maintain detailed member information and comprehensive borrowing records.
* Borrowing Management: Create loans, assign due dates, monitor item status, and ensure availability tracking.
* Return Processing: Handle item returns efficiently and update their availability in real time.
* Late-Fee Calculation: Automatically compute fines for overdue items using precise date calculations.
* Fine Management: Generate fines and monitor payment statuses for outstanding dues.
* Data Validation: Enforce unique IDs, validate dates, and restrict unauthorized transactions.
* Exception Handling: Robustly handle errors, rejecting invalid borrowing, return, and payment operations to ensure data integrity.

Technologies Used

* Java
* Object-Oriented Programming
* Java Collections Framework (ArrayList)
* Java Date and Time API (LocalDate, ChronoUnit)
* BigDecimal
* Eclipse IDE

Object-Oriented Design

Abstraction

The abstract LibraryItem class defines common properties and behaviors shared by all library items.

It declares an abstract calculateLateFee() method that subclasses must implement.

Inheritance

Three classes extend LibraryItem:

* Book
* Magazine
* DVD

Each subclass inherits common library item attributes while introducing its own specialized properties.

Polymorphism

The calculateLateFee() method is overridden in each subclass to calculate overdue fines using different daily rates.

Item Type	Daily Late Fee
	
Book	$0.25
Magazine	$0.15
DVD	$1.00


Encapsulation

Class attributes are private and accessed through public methods. Defensive copies of collections help prevent external modification of internal lists.

Project Structure

Library-Management-System-Java/
└── src/
    └── librarymanagement/
        ├── LibraryItem.java
        ├── Book.java
        ├── Magazine.java
        ├── DVD.java
        ├── Member.java
        ├── Loan.java
        ├── Fine.java
        ├── Library.java
        └── Test.java

Class Descriptions

Class	Description
	
LibraryItem	Abstract superclass defining shared item properties and late-fee behavior
Book	Represents books and calculates book-specific late fees
Magazine	Represents magazines and calculates magazine-specific late fees
DVD	Represents DVDs and calculates DVD-specific late fees
Member	Stores member information and associated loans
Loan	Manages borrowing dates, returns, and overdue calculations
Fine	Stores fine amounts and payment status
Library	Coordinates inventory, registration, loans, returns, and fines
Test	Executes functional test scenarios


Borrowing and Return Workflow

1. A library item is registered in the inventory.
2. A member is registered with the library.
3. The member borrows an available item.
4. A loan record is created with borrowing and due dates.
5. The borrowed item is marked unavailable.
6. The member returns the item.
7. The system calculates overdue days and any applicable fine.
8. The returned item becomes available again.
9. Any generated fine can be marked as paid.

Testing

The project includes 15 console-based functional test scenarios covering:

Test	Functionality
	
1	Library initialization
2	Book registration
3	Member registration
4	Item borrowing
5	Duplicate borrowing prevention
6	On-time return
7	Late return and fine calculation
8	Fine payment
9	Duplicate item ID prevention
10	Duplicate member ID prevention
11	Duplicate loan ID prevention
12	Invalid borrowing and return dates
13	Duplicate return prevention
14	Duplicate fine payment prevention
15	Polymorphic late-fee calculations


Tests use console output and expected exception handling to demonstrate application behavior.

Example Output

Library created successfully.
Total library items: 1
Book available: true
Total registered members: 1
Total loans: 1
Book available after borrowing: false
Book available after return: true
Days late: 5
Late fee: $1.25
Fine paid after payment: true
Book late fee: $1.25
Magazine late fee: $0.75
DVD late fee: $5.00

How to Run

Requirements

* Java Development Kit (JDK 11 or later)
* Eclipse IDE or another Java-compatible development environment

Instructions

1. Clone or download the repository.
2. Open Eclipse.
3. Import the project or create a Java project containing the source files.
4. Ensure all classes are in the librarymanagement package.
5. Open Test.java.
6. Select Run As → Java Application.
7. Review the console output to verify the test scenarios.

Future Enhancements

* Implement persistent storage using files or a database.
* Develop a graphical user interface.
* Add search and filtering functionality for library items.
* Introduce borrowing limits and member account restrictions.
* Implement automated unit testing with JUnit.
* Add detailed reporting for borrowing activity and outstanding fines.

Project Purpose

This project was developed to strengthen Java programming and software design skills by implementing a multi-class, object-oriented application.

It demonstrates the ability to model real-world entities, manage object relationships, validate business rules, handle exceptions, and implement reusable application logic.
