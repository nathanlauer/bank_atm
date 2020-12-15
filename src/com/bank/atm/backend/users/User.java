package com.bank.atm.backend.users;

import com.bank.atm.util.ID;
import com.bank.atm.util.Identifiable;

import java.io.Serializable;

/**
 * Class User is an abstract class that represents someone interacting with the bank.
 *
 * @author: Nathan Lauer
 * @email: lauern@bu.edu
 * Creation Date: 12/9/20
 * <p>
 * Please feel free to ask me any questions. I hope you're having a nice day!
 */
public class User implements Serializable, Identifiable {
    private String firstName;
    private String lastName;
    private final ID userID;

    /**
     * Standard constructor for a user
     * @param firstName the user's first name
     * @param lastName the user's last name
     */
    public User(String firstName, String lastName) {
        this(firstName, lastName, new ID());
    }

    /**
     * Standard constructor that initializes a User with a specific UUID
     * @param firstName the first name of this User
     * @param lastName the User's last name
     * @param userID the ID of this User
     */
    public User(String firstName, String lastName, ID userID) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userID = userID;
    }

    /**
     *
     * @return the ID of this User
     */
    public ID getID() {
        return userID;
    }

    /**
     * Indicates whether or not this Identifiable has the passed in id.
     *
     * @param id the ID in question
     * @return true if this entity has the same id, false otherwise
     */
    @Override
    public boolean hasID(ID id) {
        return userID.equals(id);
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
        return this.getID().equals(other.getID());
    }
}
