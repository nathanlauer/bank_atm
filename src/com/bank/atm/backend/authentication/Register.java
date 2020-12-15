package com.bank.atm.backend.authentication;

import com.bank.atm.backend.collections.CredentialsCollectionManager;
import com.bank.atm.backend.collections.UsersCollectionManager;
import com.bank.atm.backend.users.Admin;
import com.bank.atm.backend.users.Client;
import com.bank.atm.backend.users.User;
import com.bank.atm.backend.users.UserType;
import com.bank.atm.util.ID;

import java.io.IOException;

/**
 * Class Register is a class which handles the logic for registering
 * a new user
 *
 * @author: Nathan Lauer
 * @email: lauern@bu.edu
 * Creation Date: 12/14/20
 * <p>
 * Please feel free to ask me any questions. I hope you're having a nice day!
 */
public class Register {
    public final static String passwordAndConfirmationDoNotMatch = "Password and PasswordConfirmation are not the same!";
    public final static String passwordTooShort = "Password is too short!";
    public final static String missingCapitalLetters = "Password is missing capital letters!";
    public final static String missingSpecialCharacters = "Password is missing special characters!";
    public final static String usernameAlreadyTaken = "Username is already taken!";
    private final String firstName;
    private final String lastName;
    private final String username;
    private final String password;
    private final String passwordConfirmation;
    private final UserType userType;

    /**
     * The information required to login this user
     * @param firstName the User's first name
     * @param lastName the User's last name
     * @param username the User's username for logging in
     * @param password the User's password
     * @param passwordConfirmation confirmation of the password
     */
    public Register(String firstName, String lastName, String username, String password, String passwordConfirmation, UserType userType) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username.trim();
        this.password = password.trim();
        this.passwordConfirmation = passwordConfirmation.trim();
        this.userType = userType;
    }

    /**
     * "Main" method which registers this new user.
     * @throws AuthenticationException if unable to register this User
     */
    public ID run() throws AuthenticationException {
        runChecks();

        // Build the User
        User user = null;
        switch (userType) {
            case ADMIN:
                user = new Admin(firstName, lastName);
                break;
            case CLIENT:
                user = new Client(firstName, lastName);
                break;
            default:
                throw new AuthenticationException("Unknown user type!");
        }

        // Update the UserCollectionManager with this user
        try {
            UsersCollectionManager.getInstance().add(user);
        } catch (IOException e) {
            throw new AuthenticationException("Unable to save User set to disk!");
        }

        // Build a new Credentials for this User, and add it to the CredentialsCollectionManager
        Credentials credentials = new Credentials(username, password, user.getID());
        try {
            CredentialsCollectionManager.getInstance().add(credentials);
        } catch (IOException e) {
            throw new AuthenticationException("Unable to save Credentials set to disk!");
        }

        return user.getID();
    }

    private void runChecks() throws AuthenticationException {
        if(passwordTooShort()) {
            throw new AuthenticationException(passwordTooShort);
        }

        if(passwordAndConfirmationDoNotMatch()) {
            throw new AuthenticationException(passwordAndConfirmationDoNotMatch);
        }

        if(missingCapitalLetters()) {
            throw new AuthenticationException(missingCapitalLetters);
        }

        if(missingSpecialCharacters()) {
            throw new AuthenticationException(missingSpecialCharacters);
        }

        if(CredentialsCollectionManager.getInstance().usernameAlreadyExists(username)) {
            throw new AuthenticationException(usernameAlreadyTaken);
        }
    }

    /**
     * Indicates whether or not the password is long enough
     * @return true if the password does not have enough characters, false otherwise.
     */
    private boolean passwordTooShort() {
        return password.length() < AuthenticationConfig.minPasswordLength();
    }

    /**
     * Indicates whether or not the password and confirmation match
     * @return true if password and confirmation do not match, false otherwise
     */
    private boolean passwordAndConfirmationDoNotMatch() {
        return !password.equals(passwordConfirmation);
    }

    /**
     *
     * @return true if capital letters are required, and the password does not contain a Capital letter.
     */
    private boolean missingCapitalLetters() {
        if(AuthenticationConfig.needsCapitalLetters()) {
            for(int i = 0; i < password.length(); i++) {
                char c = password.charAt(i);
                if(Character.isUpperCase(c)) {
                    return false;
                }
            }
            return true;
        }
        // Doesn't require capital letters, hence can't be missing
        return false;
    }

    /**
     *
     * @return true if special characters are required, and the password does not contain a special character.
     */
    private boolean missingSpecialCharacters() {
        String[] specialChars = new String[]{"!", "@", "#", "$", "%", "^", "&", "[", "]", "\\", ",", "?", ";", ":"};
        if(AuthenticationConfig.needsSpecialCharacters()) {
            for(String specialCharacter : specialChars) {
                if(password.contains(specialCharacter)) {
                    // The password has a special character
                    return false;
                }
            }
            // Requires special characters, but none are present
            return true;
        }
        // Special characters not required, hence can't be missing
        return false;
    }
}
