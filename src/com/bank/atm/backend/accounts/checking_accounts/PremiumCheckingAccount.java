package com.bank.atm.backend.accounts.checking_accounts;

import com.bank.atm.backend.accounts.AccountType;
import com.bank.atm.backend.interest.InterestEarnable;
import com.bank.atm.backend.interest.InterestEarningExecutor;
import com.bank.atm.backend.currency.Currency;
import com.bank.atm.backend.currency.Money;
import com.bank.atm.util.ID;

import java.util.List;

/**
 * Class PremiumCheckingAccount
 *
 * @author: Nathan Lauer
 * @email: lauern@bu.edu
 * Creation Date: 12/10/20
 * <p>
 * Please feel free to ask me any questions. I hope you're having a nice day!
 */
public class PremiumCheckingAccount extends CheckingAccount implements InterestEarnable {
    private static final long serialVersionUID = 1L;
    private InterestEarningExecutor interestEarningExecutor;
    /**
     * Standard constructor for a PremiumCheckingAccount
     * @param currency the Currency for this Account
     * @param money the initial Monetary value for this Account
     * @param managers list of Users that are managers for this Account.
     */
    public PremiumCheckingAccount(Currency currency, Money money, List<ID> managers, ID accountId) {
        super(currency, money, managers, accountId);
    }

    /**
     * Any entity which can earn interest must be able to provide
     * an instance of an InterestEarningExecutor, which computes
     * the interest for said entity.
     *
     * @return the InterestEarningExecutor associated with the relevant Account.
     */
    @Override
    public InterestEarningExecutor getInterestEarningExecutor() {
        return interestEarningExecutor;
    }

    /**
     * Sets the InterestEarningExecutor for this Account
     *
     * @param executor the new InterestEarningExecutor for this Account
     */
    @Override
    public void setInterestEarningExecutor(InterestEarningExecutor executor) {
        this.interestEarningExecutor = executor;
    }

    /**
     * @return the Type of Account
     */
    @Override
    public AccountType getAccountType() {
        return AccountType.PREMIUM_CHECKING_ACCOUNT;
    }

    /**
     * Indicates whether or not this Account earns interest
     *
     * @return true if this Account earns interest, false otherwise
     */
    @Override
    public boolean earnsInterest() {
        return true;
    }
}
