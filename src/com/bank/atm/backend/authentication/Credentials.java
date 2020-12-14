package com.bank.atm.backend.authentication;

import com.bank.atm.util.ID;
import com.bank.atm.util.Identifiable;

import java.io.Serializable;
import java.util.UUID;

/**
 * Class Credentials
 *
 * @author: Nathan Lauer
 * @email: lauern@bu.edu
 * Creation Date: 12/9/20
 * <p>
 * Please feel free to ask me any questions. I hope you're having a nice day!
 */
public class Credentials implements Identifiable, Serializable {
    private final String username;
    private final String passwordHash;
    private final ID userId;
    private final ID id;

    /**
     * Standard constructor
     * @param username the username of this Credentials set
     * @param password the password of this Credentials set
     * @param userId the UserId that this Credentials set corresponds to
     */
    public Credentials(String username, String password, ID userId) {
        this(username, password, userId, new ID());
    }

    /**
     * Constructor that specifies the id of this Credentials set
     * @param username the username of this Credentials set
     * @param password the password of this Credentials set
     * @param userId the UserId that this Credentials set corresponds to
     * @param id the ID of this Credentials set
     */
    public Credentials(String username, String password, ID userId, ID id) {
        this.username = username;
        this.passwordHash = PasswordHash.hashPassword(password);
        this.userId = userId;
        this.id = id;
    }

    /**
     * @return the ID of this Identifiable entity
     */
    @Override
    public ID getID() {
        return id;
    }

    /**
     * Indicates whether or not this Identifiable has the passed in id.
     *
     * @param id the ID in question
     * @return true if this entity has the same id, false otherwise
     */
    @Override
    public boolean hasID(ID id) {
        return getID().equals(id);
    }

    /**
     *
     * @return the UserID of this Credentials set
     */
    public ID getUserId() {
        return userId;
    }

    /**
     * Indicates whether or not this Credentials has the passed in userId
     * @param userId the userId in question
     * @return true if this Credentials set "belongs" to the User with ID userId, false otherwise
     */
    public boolean hasUserId(ID userId) {
        return getUserId().equals(userId);
    }
}
