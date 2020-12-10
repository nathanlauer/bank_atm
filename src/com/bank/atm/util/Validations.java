package com.bank.atm.util;

/**
 * Class Validations is a class that contains a series of static methods
 * that act as validators. Generally, each one checks a condition, and throws
 * an exception if that condition fails. Otherwise, they don't do anything.
 *
 * @author: Nathan Lauer
 * @email: lauern@bu.edu
 * Creation Date: 12/9/20
 * <p>
 * Please feel free to ask me any questions. I hope you're having a nice day!
 */
public class Validations {
    /**
     * Checks that the passed in amount is non-negative.
     * @param amount the amount in question
     */
    public static void nonNegative(double amount) {
        if(amount < 0) {
            throw new IllegalArgumentException();
        }
    }

    /**
     * Checks that the value is greater than 0.
     * @param amount the amount is question
     */
    public static void positiveValue(double amount) {
        if(amount <= 0) {
            throw new IllegalArgumentException();
        }
    }
}
