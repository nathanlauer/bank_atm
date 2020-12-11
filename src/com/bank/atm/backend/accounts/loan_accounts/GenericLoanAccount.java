package com.bank.atm.backend.accounts.loan_accounts;

import com.bank.atm.backend.accounts.interest.InterestEarnable;
import com.bank.atm.backend.accounts.interest.InterestEarningExecutor;
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
public class GenericLoanAccount extends LoanAccount implements InterestEarnable {
    private InterestEarningExecutor interestEarningExecutor;

    /**
     * Standard constructor for a GenericLoanAccount
     * @param currency the Currency for this Account
     * @param money the initial Monetary value for this Account
     * @param managers list of Users that are managers for this Account.
     */
    public GenericLoanAccount(Currency currency, Money money, List<User> managers) {
        super(currency, money, managers);
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
        interestEarningExecutor = executor;
    }
}
