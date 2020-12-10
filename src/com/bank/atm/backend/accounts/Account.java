package com.bank.atm.backend.accounts;

import com.bank.atm.backend.currency.Currency;
import com.bank.atm.backend.currency.Money;
import com.bank.atm.backend.currency.UnknownExchangeRateException;
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
    private Currency currency;
    private Money money;
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
     * Private method which sets the Currency for this Account. It is
     * private because it should only be called the switchCurrency() method.
     * @param other the new Currency for this Account
     */
    private void setCurrency(Currency other) {
        this.currency = other;
    }

    /**
     *
     * @return the Money object representing the Monetary value of this Account.
     */
    public Money getMoney() {
        return money;
    }

    /**
     * Sets the Money for this Account.
     * @param other the new Money for this Account
     */
    public void setMoney(Money other) {
        this.money = other;
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
     * Indicates whether or not a certain user has access to this Account.
     * In general, if the user is an Admin, then they will have access (Admins
     * have universal access).
     * Otherwise, the user must be a Manager of this Account.
     * @param user the User in question
     * @return true if this User is an Admin or an Account manager, false otherwise.
     */
    public boolean hasAccess(User user) {
        return user.isAnAdmin() || this.isAccountManager(user);
    }

    /**
     * Indicates whether or not the passed in User is an Account manager.
     * @param user the User in question
     * @return true if the User is an Account manager, false otherwise.
     */
    public boolean isAccountManager(User user) {
        return managers.contains(user);
    }

    /**
     *
     * @return a String representing the value of this Account. For example: $1,897.23
     */
    public String displayAccountValue() {
        return decorator.displayAccountValue();
    }

    /**
     * Switches this Account from the currency Currency to a new one.
     * This doesn't "create" or "remove" money from an Account - the Money
     * is exchanged from what it currently is to its exchanged value.
     * @param next the new Currency for this Account
     */
    public void switchCurrencies(Currency next) throws UnknownExchangeRateException {
        Money exchanged = Currency.exchange(this.getMoney(), this.getCurrency(), next);
        this.setMoney(exchanged);
        this.setCurrency(next);
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
