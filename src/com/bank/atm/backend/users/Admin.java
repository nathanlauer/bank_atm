package com.bank.atm.backend.users;

import com.bank.atm.util.ID;

/**
 * Class Admin is a concrete type of User, specifically a worker who has high level
 * authorization rights for the bank.
 *
 * @author: Nathan Lauer
 * @email: lauern@bu.edu
 * Creation Date: 12/9/20
 * <p>
 * Please feel free to ask me any questions. I hope you're having a nice day!
 */
public class Admin extends User {
    private static final long serialVersionUID = 1L;
    /**
     * Standard constructor for an Admin
     * @param firstName the first name of the Admin
     * @param lastName the last name of the Admin
     */
    public Admin(String firstName, String lastName) {
        this(firstName, lastName, new ID());
    }

    /**
     * Standard constructor for a Admin, that includes a specific UUID
     * @param firstName the first name of this Admin
     * @param lastName the last name of this Admin
     * @param userID the ID of this Admin
     */
    public Admin(String firstName, String lastName, ID userID) {
        super(firstName, lastName, userID);
    }

    /**
     * Defines equality for two Admin objects.
     *
     * @param o Other object in consideration for equality
     * @return true if o is an instance of Admin, and is the same user
     */
    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }

        if (!(o instanceof Admin)) {
            return false;
        }

        Admin other = (Admin) o;
        return super.equals(other);
    }
}
