package com.bank.atm.backend.accounts;

import com.bank.atm.backend.currency.Currency;
import com.bank.atm.backend.currency.Money;
import com.bank.atm.backend.users.User;

import java.util.Date;
import java.util.List;

/**
 * Class CheckingAccount
 *
 * @author: Nathan Lauer
 * @email: lauern@bu.edu
 * Creation Date: 12/9/20
 * <p>
 * Please feel free to ask me any questions. I hope you're having a nice day!
 */
public class CheckingAccount extends Account {
    /**
     * Constructor that creates a checking Account with open Date now.
     * @param currency the Currency for this Account
     * @param money the initial Monetary value of this Account
     * @param managers List of Account managers
     */
    public CheckingAccount(Currency currency, Money money, List<User> managers) {
        this(new Date(), currency, money, managers);
    }

    /**
     * Standard Constructor for a Checking Account
     * @param opened the Date that this Account was opened
     * @param currency the Currency used by this Account
     * @param money the initial Monetary value of this Account
     * @param managers List of Users that are managers for this Account
     */
    public CheckingAccount(Date opened, Currency currency, Money money, List<User> managers) {
        super(opened, currency, money, managers);
    }
}
