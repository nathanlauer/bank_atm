package com.bank.atm.backend.accounts;

import com.bank.atm.backend.currency.Money;
import com.bank.atm.backend.users.User;

import java.util.Currency;
import java.util.Date;
import java.util.List;

/**
 * Class Account
 *
 * @author: Nathan Lauer
 * @email: lauern@bu.edu
 * Creation Date: 12/9/20
 * <p>
 * Please feel free to ask me any questions. I hope you're having a nice day!
 */
public class Account {
    private Date opened;
    private Currency currency;
    private Money money;
    private List<User> managers;

    /**
     * Defines equality for two Account objects.
     *
     * @param o Other object in consideration for equality
     * @return true if o is an instance of Account, and TODO
     */
    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }

        if (!(o instanceof Account)) {
            return false;
        }

        Account other = (Account) o;
        // TODO
        return false;
    }
}
