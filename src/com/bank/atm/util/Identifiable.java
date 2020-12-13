package com.bank.atm.util;

/**
 * Interface Identifiable specifies that any implementing entity must have
 * an ID that uniquely identifies them.
 *
 * @author: Nathan Lauer
 * @email: lauern@bu.edu
 * Creation Date: 12/13/20
 * <p>
 * Please feel free to ask me any questions. I hope you're having a nice day!
 */
public interface Identifiable {
    /**
     *
     * @return the ID of this Identifiable entity
     */
    public ID getID();

    /**
     * Indicates whether or not this Identifiable has the passed in id.
     * @param id the ID in question
     * @return true if this entity has the same id, false otherwise
     */
    public boolean hasID(ID id);
}
