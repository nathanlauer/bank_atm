package com.bank.atm.backend.accounts.investment_accounts;

import com.bank.atm.backend.accounts.Account;
import com.bank.atm.backend.accounts.AccountFactoryCreator;
import com.bank.atm.backend.accounts.checking_accounts.PremiumCheckingAccount;
import com.bank.atm.backend.currency.Currency;
import com.bank.atm.backend.currency.Money;
import com.bank.atm.backend.users.User;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Class FourOhOneKAccountFactory is a factory that creates a FourOhOneKAccount
 *
 * @author: Nathan Lauer
 * @email: lauern@bu.edu
 * Creation Date: 12/10/20
 * <p>
 * Please feel free to ask me any questions. I hope you're having a nice day!
 */
public class FourOhOneKAccountFactory implements AccountFactoryCreator {
    private final Currency currency;
    private final double initialAmount;
    private final User user;

    /**
     * Standard constructor
     * @param currency the Currency to use for this Account
     * @param initialAmount the initial monetary value for this account
     * @param user the User creating this Account.
     */
    public FourOhOneKAccountFactory(Currency currency, double initialAmount, User user) {
        this.currency = currency;
        this.initialAmount = initialAmount;
        this.user = user;
    }

    /**
     * Creates the appropriate type of Account
     *
     * @return Account object of appropriate subtype.
     */
    @Override
    public Account createAccount() {
        List<User> managers = new ArrayList<>(Collections.singletonList(user));
        return new FourOhOneKAccount(currency, new Money(initialAmount), managers);
    }
}
