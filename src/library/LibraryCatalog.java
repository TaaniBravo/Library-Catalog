package library;

import java.util.HashMap;
import java.util.Map;

public class LibraryCatalog {

    Map<String, Book> bookCollection;
    int currentDay = 0;
    int lengthOfCheckoutPeriod = 7;
    double initialLateFee = 0.50;
    double feePerLateDay = 1.00;

    public LibraryCatalog(Map<String,Book> collection) {
        this.bookCollection = collection;
    }

    // Constructor
    public LibraryCatalog(Map<String, Book> collection, int lengthOfCheckoutPeriod, double initialLateFee, double feePerLateDay) {
        this.bookCollection = collection;
        this.lengthOfCheckoutPeriod = lengthOfCheckoutPeriod;
        this.initialLateFee = initialLateFee;
        this.feePerLateDay = feePerLateDay;
    }

    // Getters
    public int getCurrentDay() {
        return this.currentDay;
    }

    public Map<String, Book> getBookCollection() {
        return this.bookCollection;
    }

    public Book getBook(String bookTitle) {
        return getBookCollection().get(bookTitle);
    }

    public int getLengthOfCheckoutPeriod() {
        return this.lengthOfCheckoutPeriod;
    }

    public double getInitialLateFee() {
        return this.initialLateFee;
    }

    public double getFeePerLateDay() {
        return this.feePerLateDay;
    }

    // Setters
    public void nextDay() {
        currentDay++;
    }

    public void setDay(int day) {
        currentDay = day;
    }

    // Instance Methods

    public void checkOut(String title) {
        Book book = getBook(title);
        if (book.getIsCheckedOut()) {
            bookAlreadyCheckedOut(book);
        } else {
            book.setCheckedOut(true, currentDay);
            System.out.println("You just checked out " + title + ". It is due on day " + (getCurrentDay() + getLengthOfCheckoutPeriod()) + ".");
        }
    }

    public void returnBook(String title) {
        Book book = getBook(title);
        int daysLate = currentDay - (book.getDayCheckedOut() - getLengthOfCheckoutPeriod());
        if (daysLate > 0) {
            System.out.println("You owe $" + (getInitialLateFee() + daysLate * getFeePerLateDay()) + " because your book is " + daysLate + "days overdue.");
        } else {
            System.out.println("Book returned on time. Thank you, come again.");
        }

        book.setCheckedOut(false, -1);
    }

    public void bookAlreadyCheckedOut(Book book) {
        System.out.println("Sorry " + book.getTitle() + " is already checked out. " + "It shold be back on day " + (book.getDayCheckedOut() + getLengthOfCheckoutPeriod()) + ".");
    }

    public static void main(String[] args) {
        Map<String, Book> bookCollection = new HashMap<>();
        Book harryPotter = new Book("Harry Potter", 827132, 9999999);
        bookCollection.put("Harry Potter", harryPotter);
        LibraryCatalog lib = new LibraryCatalog(bookCollection);
        lib.checkOut("Harry Potter");
        lib.nextDay();
        lib.nextDay();
        lib.checkOut("Harry Potter");
        lib.setDay(17);
        lib.returnBook("Harry Potter");
        lib.checkOut("Harry Potter");
    }
}