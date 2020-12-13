package com.bank.atm.backend.users;

import com.bank.atm.backend.authentication.Credentials;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Class User is an abstract class that represents someone interacting with the bank.
 *
 * @author: Nathan Lauer
 * @email: lauern@bu.edu
 * Creation Date: 12/9/20
 * <p>
 * Please feel free to ask me any questions. I hope you're having a nice day!
 */
public class User implements Serializable {
    private String firstName;
    private String lastName;
    private final UserID userID;
    private final List<Credentials> credentialsList;

    /**
     * Standard constructor for a user
     * @param firstName the user's first name
     * @param lastName the user's last name
     */
    public User(String firstName, String lastName) {
        this(firstName, lastName, new UserID());
    }

    /**
     * Standard constructor that initializes a User with a specific UUID
     * @param firstName the first name of this User
     * @param lastName the User's last name
     * @param userID the UserID of this User
     */
    public User(String firstName, String lastName, UserID userID) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userID = userID;
        this.credentialsList = new ArrayList<>();
    }

    /**
     *
     * @return the UserID of this User
     */
    public UserID getUserID() {
        return userID;
    }

    /**
     *
     * @return this User's first name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets the firstName of this User to the passed in value.
     * @param firstName the new first name for this User
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     *
     * @return the lastName for this User.
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets the lastName of this User to the passed in value.
     * @param lastName the new first name for this User
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     *
     * @return true if this User is an Admin, false otherwise.
     */
    public boolean isAnAdmin() {
        return this instanceof Admin;
    }

    /**
     *
     * @return true if this User is a Client, false otherwise.
     */
    public boolean isAClient() {
        return this instanceof Client;
    }

    /**
     * @return String representation of this User object.
     */
    @Override
    public String toString() {
        return getFirstName() + " " + getLastName();
    }

    /**
     * Defines equality for two User objects.
     *
     * @param o Other object in consideration for equality
     * @return true if o is an instance of User, and this User has the same UUID.
     */
    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }

        if (!(o instanceof User)) {
            return false;
        }

        User other = (User) o;
        return this.userID.equals(other.userID);
    }
}
