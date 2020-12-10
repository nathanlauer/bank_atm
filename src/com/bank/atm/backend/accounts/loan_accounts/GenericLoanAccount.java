package com.bank.atm.backend.accounts.loan_accounts;

import com.bank.atm.backend.currency.Currency;
import com.bank.atm.backend.currency.Money;
import com.bank.atm.backend.users.User;

import java.util.List;

/**
 * Class GenericLoanAccount
 *
 * @author: Nathan Lauer
 * @email: lauern@bu.edu
 * Creation Date: 12/10/20
 * <p>
 * Please feel free to ask me any questions. I hope you're having a nice day!
 */
public class GenericLoanAccount extends LoanAccount {
    /**
     * Standard constructor for a GenericLoanAccount
     * @param currency the Currency for this Account
     * @param money the initial Monetary value for this Account
     * @param managers list of Users that are managers for this Account.
     */
    public GenericLoanAccount(Currency currency, Money money, List<User> managers) {
        super(currency, money, managers);
    }
}
