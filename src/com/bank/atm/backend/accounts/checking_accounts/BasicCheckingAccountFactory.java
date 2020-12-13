package com.bank.atm.backend.accounts.checking_accounts;

import com.bank.atm.backend.accounts.Account;
import com.bank.atm.backend.accounts.AccountFactoryCreator;
import com.bank.atm.backend.accounts.AccountsUtil;
import com.bank.atm.backend.currency.Currency;
import com.bank.atm.backend.currency.Money;
import com.bank.atm.backend.users.User;
import com.bank.atm.util.ID;

import java.util.List;

/**
 * Class BasicCheckingAccountFactory is a Factory which creates a BasicCheckingAccount.
 *
 * @author: Nathan Lauer
 * @email: lauern@bu.edu
 * Creation Date: 12/10/20
 * <p>
 * Please feel free to ask me any questions. I hope you're having a nice day!
 */
public class BasicCheckingAccountFactory implements AccountFactoryCreator {
    private final Currency currency;
    private final double initialAmount;
    private final User user;

    /**
     * Standard constructor
     * @param currency the Currency to use for this Account
     * @param initialAmount the initial monetary value for this account
     * @param user the User creating this Account.
     */
    public BasicCheckingAccountFactory(Currency currency, double initialAmount, User user) {
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
        List<ID> managers = AccountsUtil.buildManagerListFromUser(user);
        return new BasicCheckingAccount(currency, new Money(initialAmount), managers);
    }
}
