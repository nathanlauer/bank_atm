package com.bank.atm.backend.authentication;

/**
 * Class PasswordHash is a class which hashes input passwords using a private key,
 * and provides some useful helper methods.
 *
 * @author: Nathan Lauer
 * @email: lauern@bu.edu
 * Creation Date: 12/9/20
 * <p>
 * Please feel free to ask me any questions. I hope you're having a nice day!
 */
public class PasswordHash {
    private static final String key = "sd@#$f~klj";

    /**
     * Given a password, return a hash version of it, so we don't store
     * plain text obvious passwords
     * @param password the password to hash
     * @return Hash version of the password
     */
    public static String hashPassword(String password) {
        String combined = PasswordHash.key + password;
        return String.valueOf(combined.hashCode());
    }

    /**
     * For the passed in password, indicates whether or not it matches
     * the stored version for this user.
     * @param hashedPassword the hashed value stored for the actual password
     * @param passwordAttempt the String attempted password
     * @return true if the password is correct, false otherwise.
     */
    public static boolean passwordMatches(String hashedPassword, String passwordAttempt) {
        String combined = PasswordHash.key + passwordAttempt;
        String hashed = String.valueOf(combined.hashCode());
        return hashed.equals(hashedPassword);
    }
}
