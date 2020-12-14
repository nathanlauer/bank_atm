package com.bank.atm.backend.authentication;

/**
 * Class AuthenticationConfig is a static class that defines the configuration
 * requirements for an authentication password
 *
 * @author: Nathan Lauer
 * @email: lauern@bu.edu
 * Creation Date: 12/14/20
 * <p>
 * Please feel free to ask me any questions. I hope you're having a nice day!
 */
public class AuthenticationConfig {
    /**
     *
     * @return the minimum length requirement for a password
     */
    public static int minPasswordLength() {
        return 4;
    }

    /**
     *
     * @return whether or not capital letters are required
     */
    public static boolean needsCapitalLetters() {
        return true;
    }

    /**
     *
     * @return whether or not special characters are required
     */
    public static boolean needsSpecialCharacters() {
        return true;
    }
}
