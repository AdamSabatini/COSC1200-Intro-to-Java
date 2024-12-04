/*Names: Adam Sabatini
Student Number: 100945612
File Name: CreditCard.java
Date: Mar 4,2024
Description: create methods that stores credit card information with custom parameters, default parameters and a check for the information.
 */
public class CreditCard {
    // declare private attributes
    private long accountNumber;
    private int month;
    private int year;
    private String holder;

    // declare private constants with provided values
    private static final String DEFAULT_NAME = "Bob Loblaws";
    private static final long DEFAULT_ACCOUNT_NUMBER = 1234567890123456L;
    private static final int DEFAULT_MONTH = 1;
    private static final int DEFAULT_YEAR = 99;

    // declare public constant attribute for ACCOUNT_NUMBER_LENGTH to compare to
    public static final long ACCOUNT_NUMBER_LENGTH = 16;

    // constructor with custom parameters
    public CreditCard(long accountNumber, int month, int year, String holder) {
        this.accountNumber = accountNumber;
        this.month = month;
        this.year = year;
        this.holder = holder;
    }

    // default constructor
    public CreditCard() {
        this.accountNumber = DEFAULT_ACCOUNT_NUMBER;
        this.month = DEFAULT_MONTH;
        this.year = DEFAULT_YEAR;
        this.holder = DEFAULT_NAME;
    }

    // display credit card information
    public void displayCardInfo() {
        System.out.println("Card Holder Name: " + holder);
        System.out.println("Account Number: " + accountNumber);
        // month and year display with leading zero if needed
        String formattedMonth = String.format("%02d", month);
        String formattedYear = String.format("%02d", year);
        System.out.println("Expiration Date: " + formattedMonth + "/" + formattedYear);
    }

    // verify account number length
    public boolean verifyAccountNumber() {
        // Convert account number to string
        String accountStr = Long.toString(accountNumber);
        // Check if the length matches ACCOUNT_NUMBER_LENGTH
        return accountStr.length() == ACCOUNT_NUMBER_LENGTH;
    }
}
