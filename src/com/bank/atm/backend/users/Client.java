package com.bank.atm.backend.users;

import com.bank.atm.util.ID;

/**
 * Class Client is a concrete type of User, representing a client of the bank.
 *
 * @author: Nathan Lauer
 * @email: lauern@bu.edu
 * Creation Date: 12/9/20
 * <p>
 * Please feel free to ask me any questions. I hope you're having a nice day!
 */
public class Client extends User {
    private static final long serialVersionUID = 1L;
    /**
     * Standard constructor for a Client
     * @param firstName the first name of this Client
     * @param lastName the last name of this Client
     */
    public Client(String firstName, String lastName) {
        this(firstName, lastName, new ID());
    }

    /**
     * Standard constructor for a Client, that includes a specific UUID
     * @param firstName the first name of this Client
     * @param lastName the last name of this Client
     * @param userID the ID of this Client
     */
    public Client(String firstName, String lastName, ID userID) {
        super(firstName, lastName, userID);
    }

    /**
     * Defines equality for two Client objects.
     *
     * @param o Other object in consideration for equality
     * @return true if o is an instance of Client, and is the same User.
     */
    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }

        if (!(o instanceof Client)) {
            return false;
        }

        Client other = (Client) o;
        return super.equals(other);
    }
}
