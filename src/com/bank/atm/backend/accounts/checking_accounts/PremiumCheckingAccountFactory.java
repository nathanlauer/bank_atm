package com.bank.atm.backend.accounts.checking_accounts;

import com.bank.atm.backend.accounts.Account;
import com.bank.atm.backend.accounts.AccountFactoryCreator;
import com.bank.atm.backend.accounts.AccountsUtil;
import com.bank.atm.backend.accounts.interest.InterestCompoundedYearly;
import com.bank.atm.backend.accounts.interest.InterestEarnable;
import com.bank.atm.backend.accounts.interest.InterestEarningExecutor;
import com.bank.atm.backend.currency.Currency;
import com.bank.atm.backend.currency.Money;
import com.bank.atm.backend.users.User;
import com.bank.atm.util.ID;

import java.util.Date;
import java.util.List;

/**
 * Class PremiumCheckingAccountFactory is a Factory that creates a PremiumCheckingAccount
 *
 * @author: Nathan Lauer
 * @email: lauern@bu.edu
 * Creation Date: 12/10/20
 * <p>
 * Please feel free to ask me any questions. I hope you're having a nice day!
 */
public class PremiumCheckingAccountFactory implements AccountFactoryCreator {
    private final Currency currency;
    private final double initialAmount;
    private final User user;

    /**
     * Standard constructor
     * @param currency the Currency to use for this Account
     * @param initialAmount the initial monetary value for this account
     * @param user the User creating this Account.
     */
    public PremiumCheckingAccountFactory(Currency currency, double initialAmount, User user) {
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
        // First, build the basic account
        List<ID> managers = AccountsUtil.buildManagerListFromUser(user);
        Account account = new PremiumCheckingAccount(currency, new Money(initialAmount), managers);

        // Premium checking accounts earn a small amount of interest: 0.5% interest per year
        double apy = 0.5;
        InterestEarningExecutor interestEarningExecutor = new InterestCompoundedYearly(account, new Date(), apy);
        InterestEarnable earnable = (InterestEarnable)account;
        earnable.setInterestEarningExecutor(interestEarningExecutor);

        return account;
    }
}
