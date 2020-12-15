package com.bank.atm.util;

import java.io.Serializable;
import java.util.UUID;

/**
 * Class ID is a class that acts as a globally unique identifier for some entity.
 * It is effectively a wrapper around a UUID, but the naming convention makes
 * this a useful abstraction.
 *
 * @author: Nathan Lauer
 * @email: lauern@bu.edu
 * Creation Date: 12/13/20
 * <p>
 * Please feel free to ask me any questions. I hope you're having a nice day!
 */
public class ID implements Serializable {
    private static final long serialVersionUID = 1L;
    private final UUID uuid;

    /**
     * Standard constructor for an ID
     */
    public ID() {
        uuid = UUID.randomUUID();
    }

    /**
     *
     * @return the UUID of this ID
     */
    public UUID getUuid() {
        return uuid;
    }

    /**
     * @return String representation of this ID object.
     */
    @Override
    public String toString() {
        return getUuid().toString();
    }

    /**
     * Defines equality for two ID objects.
     *
     * @param o Other object in consideration for equality
     * @return true if o is an instance of ID, and wrap the same UUID
     */
    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }

        if (!(o instanceof ID)) {
            return false;
        }

        ID other = (ID) o;
        return getUuid().equals(other.getUuid());
    }
}
