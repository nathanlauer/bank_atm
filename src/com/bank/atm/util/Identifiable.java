package com.bank.atm.util;

import java.util.UUID;

/**
 * Interface identifiable is an interface that specifies that every implementing
 * instance must have a UUID, and that UUID must be retrievable
 *
 * @author: Nathan Lauer
 * @email: lauern@bu.edu
 * Creation Date: 12/12/20
 * <p>
 * Please feel free to ask me any questions. I hope you're having a nice day!
 */
public interface Identifiable {
    /**
     *
     * @return the UUID of this entity
     */
    public UUID getUUID();
}
