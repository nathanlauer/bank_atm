package com.bank.atm.backend.accounts.loan_accounts;

import com.bank.atm.backend.accounts.Account;
import com.bank.atm.backend.currency.Currency;
import com.bank.atm.backend.currency.Money;
import com.bank.atm.backend.users.User;
import com.bank.atm.backend.users.UserID;

import java.util.Date;
import java.util.List;

/**
 * Class LoanAccount
 *
 * @author: Nathan Lauer
 * @email: lauern@bu.edu
 * Creation Date: 12/9/20
 * <p>
 * Please feel free to ask me any questions. I hope you're having a nice day!
 */
public class LoanAccount extends Account {
    /**
     * Constructor that creates a LoanAccount with open Date now.
     * @param currency the Currency for this Account
     * @param money the initial Monetary value of this Account
     * @param managers List of Account managers
     */
    public LoanAccount(Currency currency, Money money, List<UserID> managers) {
        this(new Date(), currency, money, managers);
    }

    /**
     * Standard Constructor for a LoanAccount
     * @param opened the Date that this Account was opened
     * @param currency the Currency used by this Account
     * @param money the initial Monetary value of this Account
     * @param managers List of Users that are managers for this Account
     */
    public LoanAccount(Date opened, Currency currency, Money money, List<UserID> managers) {
        super(opened, currency, money, managers);
    }
}
