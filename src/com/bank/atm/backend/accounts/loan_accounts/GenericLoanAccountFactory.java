package com.bank.atm.backend.accounts.loan_accounts;

import com.bank.atm.backend.accounts.Account;
import com.bank.atm.backend.accounts.AccountFactoryCreator;
import com.bank.atm.backend.accounts.AccountsUtil;
import com.bank.atm.backend.collections.InterestCollectionsManager;
import com.bank.atm.backend.interest.Interest;
import com.bank.atm.backend.interest.InterestCompoundedDaily;
import com.bank.atm.backend.interest.InterestEarnable;
import com.bank.atm.backend.interest.InterestEarningExecutor;
import com.bank.atm.backend.currency.Currency;
import com.bank.atm.backend.currency.Money;
import com.bank.atm.backend.users.User;
import com.bank.atm.util.ID;

import java.io.IOException;
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
    private final ID accountId;
    private final String collateral;
    private final double collateralValue;
    private final int creditScore;

    /**
     * Standard constructor
     * @param currency the Currency to use for this Account
     * @param initialAmount the initial monetary value for this account
     * @param user the User creating this Account.
     */
    public GenericLoanAccountFactory(Currency currency, double initialAmount, User user, ID accountId, String collateral, double collateralValue, int creditScore) {
        this.currency = currency;
        this.initialAmount = initialAmount;
        this.user = user;
        this.accountId = accountId;
        this.collateral = collateral;
        this.collateralValue = collateralValue;
        this.creditScore = creditScore;
    }

    /**
     * Creates the appropriate type of Account
     *
     * @return Account object of appropriate subtype.
     */
    @Override
    public Account createAccount() {
        // First, build the basic loan account
        List<ID> managers = AccountsUtil.buildManagerListFromUser(user);
        Account account = new GenericLoanAccount(currency, new Money(initialAmount), managers, accountId, collateral, collateralValue, creditScore);

        // This account earns interest for the bank: 17% interest per year, and it's compounded daily.
        double apy = 17;
        Interest interest = new InterestCompoundedDaily(account, new Date(), apy);
        InterestEarnable earnable = (InterestEarnable)account;
        earnable.setInterestEarningExecutor(interest);

        // Add the interest to the InterestCollectionsManager, so we can persist it
        try {
            InterestCollectionsManager.getInstance().add(interest);
        } catch (IOException e) {
            e.printStackTrace();
            // Shouldn't happen
        }

        return account;
    }
}
