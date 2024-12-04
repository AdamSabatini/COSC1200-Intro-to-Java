/*Names: Adam Sabatini
Student Number: 100945612
File Name: CardTester.java
Date: Mar 4,2024
Description: display credit card information for a card with default values, correct values and incorrect values.
 */
public class CardTester {
    public static void main(String[] args) {
        // creating CreditCard objects
        CreditCard defaultCard = new CreditCard();
        CreditCard correctCard = new CreditCard(5312264554231345L,1 ,24 , "Jane Smith");
        CreditCard incorrectCard = new CreditCard(10234L, 4, 22, "Neva Read");

        // displaying credit card info
        System.out.println("Displaying Default Card Info:");
        defaultCard.displayCardInfo();

        System.out.println("\nDisplaying Correct Card Info:");
        correctCard.displayCardInfo();

        System.out.println("\nDisplaying Incorrect Card Info:");
        incorrectCard.displayCardInfo();

        // calling upon the verifyAccountNumber method to verify account numbers
        System.out.println("\nChecking if Default Card number is correct: " + defaultCard.verifyAccountNumber());
        System.out.println("Checking if Jane Smith's Card number is correct: " + correctCard.verifyAccountNumber());
        System.out.println("Checking if Neva Read's Card number is correct: " + incorrectCard.verifyAccountNumber());
    }
}
