package com.bank.atm.backend.users;

import com.bank.atm.backend.authentication.Credentials;
import com.bank.atm.util.Identifiable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Class User is an abstract class that represents someone interacting with the bank.
 *
 * @author: Nathan Lauer
 * @email: lauern@bu.edu
 * Creation Date: 12/9/20
 * <p>
 * Please feel free to ask me any questions. I hope you're having a nice day!
 */
public class User implements Identifiable, Serializable {
    private String firstName;
    private String lastName;
    private final UUID uuid;
    private final List<Credentials> credentialsList;

    /**
     * Standard constructor for a user
     * @param firstName the user's first name
     * @param lastName the user's last name
     */
    public User(String firstName, String lastName) {
        this(firstName, lastName, UUID.randomUUID());
    }

    /**
     * Standard constructor that initializes a User with a specific UUID
     * @param firstName the first name of this User
     * @param lastName the User's last name
     * @param uuid the UUID of this user
     */
    public User(String firstName, String lastName, UUID uuid) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.uuid = uuid;
        this.credentialsList = new ArrayList<>();
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
        return this.uuid.equals(other.uuid);
    }

    /**
     * @return the UUID of this entity
     */
    @Override
    public UUID getUUID() {
        return uuid;
    }
}
