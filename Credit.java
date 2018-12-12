/** 
 * A program to check the authenticity of a credit card number
*/

package credit;

import java.util.Scanner;

/**
 * Credit problem solution from cs50 written in java
 * @author Captain Espada
 */
public class Credit {

    public static void main(String[] args) {
        
        Scanner stdin = new Scanner(System.in);
        
        long ccNumber, ccNumberCopy; // the credit card number entered by the user, and a copy
        int digitCount = 0, product, sum = 0, pDigit, lastSDigit; 
        
        // Prompt user for creditcard number
        do {
            System.out.print("Number: ");
            ccNumber = stdin.nextLong();
        }
        while (ccNumber < 0);
        
        // Count the number of digits
        ccNumberCopy = ccNumber;
        while (ccNumberCopy > 0) {
            ccNumberCopy /= 10;
            digitCount++;
        }
        
        // CheckSum
        // Get every Digit in the ccNumber
        long number[] = new long[digitCount]; // An arraylist to store individual digit of the card number
        long numberCopy[] = new long[digitCount];
        
        for (int i = 0; i < digitCount; i++) {
            number[i] = ccNumber % 10; // The last digit in ccNumber becomes 1st in the array, the penultimate the 2nd, etc.
            numberCopy[i] = number[i];
            ccNumber /= 10;
        }
        
        /**
        * Multiply every other by 2, starting from the 2nd in the array (i.e. the penultimate in the ccNumber)
        * and then, sum up the "digits" of the products
        */
        for (int i = 1; i < digitCount; i += 2) {
            product = (int) number[i] * 2; 
            // break into individual digit if products consist of two digits
            if (product >= 10) {
                for (int j = 0; j < 2; j++) {
                    pDigit = product % 10; // the last digit of product
                    product /= 10;
                    sum += pDigit; // total number of all products' digits
                }
            }
            else {
                sum += product; //
            }
        }
        
        // Sum the remaining digits that were not multiplied by 2 with the products digit's sum
        for (int i = 0; i < digitCount; i += 2) {
            sum += number[i];
        }
        
        lastSDigit = sum % 10; // The last digit of the total sum
        
        
        // Check the validity and type of the card using the digitCount, CheckSum and lastSDigit
        int firstDigit = (int) number[digitCount - 1]; // The first digit of ccNumber entered by user.
        int secondDigit = (int) number[digitCount - 2]; // The second digit of ccNumber entered by user.
        
        if ((digitCount == 13 || digitCount == 16) && firstDigit == 4 && lastSDigit == 0) {
            System.out.printf("VISA\n");
        }
        else if (digitCount == 15 && firstDigit == 3 && 
                (secondDigit == 4 || secondDigit == 7) && lastSDigit == 0) {
            System.out.printf("AMEX\n");
        }
        else if (digitCount == 16 && firstDigit == 5 && 
                (secondDigit == 1 || secondDigit == 2 || secondDigit == 3 || secondDigit == 4 || secondDigit == 5)
                && lastSDigit == 0) {
            System.out.printf("MASTERCARD\n");
        }
        else {
            System.out.printf("INVALID\n");
        }
        
    }
    
}
