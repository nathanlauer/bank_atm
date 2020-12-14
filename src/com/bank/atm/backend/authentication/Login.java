package com.bank.atm.backend.authentication;

import com.bank.atm.backend.collections.CredentialsCollectionManager;
import com.bank.atm.util.ID;

import java.util.NoSuchElementException;

/**
 * Class Login encapsulates the process of logging in a User
 *
 * @author: Nathan Lauer
 * @email: lauern@bu.edu
 * Creation Date: 12/14/20
 * <p>
 * Please feel free to ask me any questions. I hope you're having a nice day!
 */
public class Login {
    public final static String invalidCredentials = "Invalid login credentials!";
    private final String username;
    private final String password;

    /**
     * Login constructor
     * @param username the username entered by the User
     * @param password the password entered by the User
     */
    public Login(String username, String password) {
        this.username = username;
        this.password = password;
    }

    /**
     * "Main" method for logging in - attempts to log the user in
     * @return the ID of the logged in user, on success
     * @throws AuthenticationException if the login attempt fails
     */
    public ID run() throws AuthenticationException {
        Credentials credentials = null;
        try {
            credentials = CredentialsCollectionManager.getInstance().matchesUsernameAndPassword(username, password);
        } catch (NoSuchElementException e) {
            throw new AuthenticationException(invalidCredentials);
        }
        return credentials.getUserId();
    }
}
