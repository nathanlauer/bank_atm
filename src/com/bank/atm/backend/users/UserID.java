package com.bank.atm.backend.users;

import java.io.Serializable;
import java.util.UUID;

/**
 * Class UserID is a class which is effectively a wrapper around a UUID.
 * However, since the concept of a UserID is canonical -- that is, it is
 * the definitive demarcater of a User -- it is a useful abstraction
 * to have this encapsulated within a class.
 *
 * @author: Nathan Lauer
 * @email: lauern@bu.edu
 * Creation Date: 12/12/20
 * <p>
 * Please feel free to ask me any questions. I hope you're having a nice day!
 */
public class UserID implements Serializable {
    private final UUID uuid;

    /**
     * Standard constructor: builds a unique identifier for a User.
     */
    public UserID() {
        uuid = UUID.randomUUID();
    }

    /**
     *
     * @return the UUID of this UserID
     */
    public UUID getUuid() {
        return uuid;
    }

    /**
     * @return String representation of this UserID object.
     */
    @Override
    public String toString() {
        return uuid.toString();
    }

    /**
     * Defines equality for two UserID objects.
     *
     * @param o Other object in consideration for equality
     * @return true if o is an instance of UserID, and they wrap the same UUID.
     */
    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }

        if (!(o instanceof UserID)) {
            return false;
        }

        UserID other = (UserID) o;
        return getUuid().equals(other.getUuid());
    }
}
