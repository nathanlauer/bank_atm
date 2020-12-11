package com.bank.atm.backend.accounts.loan_accounts;

import com.bank.atm.backend.accounts.Account;
import com.bank.atm.backend.accounts.AccountFactoryCreator;
import com.bank.atm.backend.accounts.interest.InterestCompoundedDaily;
import com.bank.atm.backend.accounts.interest.InterestCompoundedMonthly;
import com.bank.atm.backend.accounts.interest.InterestEarnable;
import com.bank.atm.backend.accounts.interest.InterestEarningExecutor;
import com.bank.atm.backend.accounts.investment_accounts.FourOhOneKAccount;
import com.bank.atm.backend.currency.Currency;
import com.bank.atm.backend.currency.Money;
import com.bank.atm.backend.users.User;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * Class GenericLoanAccountFactory is a Factory which creates a GenericLoanAccount
 *
 * @author: Nathan Lauer
 * @email: lauern@bu.edu
 * Creation Date: 12/10/20
 * <p>
 * Please feel free to ask me any questions. I hope you're having a nice day!
 */
public class GenericLoanAccountFactory implements AccountFactoryCreator {
    private final Currency currency;
    private final double initialAmount;
    private final User user;

    /**
     * Standard constructor
     * @param currency the Currency to use for this Account
     * @param initialAmount the initial monetary value for this account
     * @param user the User creating this Account.
     */
    public GenericLoanAccountFactory(Currency currency, double initialAmount, User user) {
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
        // First, build the basic loan account
        List<User> managers = new ArrayList<>(Collections.singletonList(user));
        Account account = new GenericLoanAccount(currency, new Money(initialAmount), managers);

        // This account earns interest for the bank: 17% interest per year, and it's compounded daily.
        double apy = 17;
        InterestEarningExecutor interestEarningExecutor = new InterestCompoundedDaily(account, new Date(), apy);
        InterestEarnable earnable = (InterestEarnable)account;
        earnable.setInterestEarningExecutor(interestEarningExecutor);

        return account;
    }
}
