package com.bank.atm.backend.accounts;

import com.bank.atm.backend.currency.Currency;
import com.bank.atm.backend.currency.Money;
import com.bank.atm.backend.users.User;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

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
    private final UUID uuid;
    private final Date opened;
    private final Currency currency;
    private final Money money;
    private final List<User> managers;
    private final AccountValueDecorator decorator;

    public Account(Date opened, Currency currency, Money money, List<User> managers) {
        this.uuid = UUID.randomUUID();
        this.opened = opened;
        this.currency = currency;
        this.money = money;
        this.managers = new ArrayList<>(managers); // copy to preserve encapsulation
        this.decorator = new AccountValueDecorator(this);
    }

    /**
     *
     * @return the Currency that this Account uses
     */
    public Currency getCurrency() {
        return currency;
    }

    /**
     *
     * @return the Money object representing the Monetary value of this Account.
     */
    public Money getMoney() {
        return money;
    }

    /**
     *
     * @return the Date that this Account was opened
     */
    public Date getOpened() {
        return opened;
    }

    /**
     * Adds an authorized User to this Account
     * @param manager the new User to be authorized as a manager for this Account.
     */
    public void addManager(User manager) {
        managers.add(manager);
    }

    /**
     * Removes a User from being an authorized manager of this Account
     * @param manager the User to be removed
     */
    public void removeManager(User manager) {
        managers.remove(manager);
    }

    /**
     *
     * @return a String representing the value of this Account. For example: $1,897.23
     */
    public String displayAccountValue() {
        return decorator.displayAccountValue();
    }

    /**
     * Defines equality for two Account objects.
     *
     * @param o Other object in consideration for equality
     * @return true if o is an instance of Account, and they have the same UUID.
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
        return this.uuid.equals(other.uuid);
    }
}
