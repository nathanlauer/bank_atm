package com.bank.atm.backend.interest;

import com.bank.atm.backend.accounts.Account;
import com.bank.atm.backend.accounts.loan_accounts.LoanAccount;

import java.io.Serializable;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Interface InterestEarningExecutor is an interface that describes the behavior
 * of each of the various classes that calculate interest
 *
 * @author: Nathan Lauer
 * @email: lauern@bu.edu
 * Creation Date: 12/11/20
 * <p>
 * Please feel free to ask me any questions. I hope you're having a nice day!
 */
public interface InterestEarningExecutor {
    /**
     * Checks if the compounding period has passed, and if so,
     * calculates the amount of interest earned and updates the
     * associated account.
     */
    void computeInterest();

    /**
     * Sets the new APY (Annual Percentage Yield) for this InterestEarningExecutor
     * @param apy the new APY for this Account
     */
    void setApy(double apy);

    /**
     *
     * @return the Average Percentage Yield of this InterestEarningExecutor
     */
    double getApy();

    /**
     *
     * @return the Date at which this Account had interest compounded
     */
    Date getLastCompounded();

    /**
     * Sets the last Date at which this InterestEarningExecutor was compounded,
     * meaning the last Date at which interest was earned.
     * @param date the last Date at which interest was earned.
     */
    void setLastCompoundedDate(Date date);

    /**
     *
     * @return the Account associated with this InterestEarningExecutor
     */
    Account getAccount();

    /**
     *
     * @return the number of days between compounding periods
     */
    int getIntervalDays();

    /**
     *
     * @return the number of times this InterestEarningExecutor is compounded per year.
     * For example, if it is compounded monthly, then this should return 12.
     */
    int getNumCompoundingsPerYear();

    /**
     * Indicates whether or not the required compounding interval has passed.
     * @param lastCompounded Date when the account was last compounded
     * @param intervalDays days between compounding instances
     * @return true if more time has passed since the last compounding, false otherwise.
     */
    static boolean compoundingIntervalHasNotPassed(Date lastCompounded, int intervalDays) {
        Date current = new Date();
        long timePassed = current.getTime() - lastCompounded.getTime();
        int daysPassed = (int)TimeUnit.MILLISECONDS.toDays(timePassed);
        return daysPassed < intervalDays;
    }

    /**
     * Computes the amount of interest earned for the given Account.
     * @param account the Account to earn interest
     * @param apy the Average Percentage Yield of the InterestEarningExecutor, in the range [0, 100]
     * @param numCompoundingsPerYear number of times an Account is compounded per year - for example,
     *                               daily is 365, monthly is 12, yearly is 1.
     * @return the amount of interest earned, not including the principal of the Account.
     */
    static double calculateInterestEarned(Account account, double apy, int numCompoundingsPerYear) {
        double rate = apy / 100.0 / numCompoundingsPerYear;
        return account.getMoney().getAmount() * rate;
    }

    /**
     * The actual logic for computing interest amongst the various different
     * executors is the same - the only difference are the specific amounts and
     * compounding periods. Hence, we can provide a single static method here,
     * that implements the logic for computing interest and updating an
     * Account's value, without duplicating this code throughout each of the
     * various different InterestEarningExecutors.
     * @param executor the InterestEarningExecutor that encapsulates interest
     *                 calculation for some Account.
     */
    static void computeInterestAndUpdateAccount(InterestEarningExecutor executor) {
        // Ensure that enough time has passed
        Date lastCompounded = executor.getLastCompounded();
        int intervalDays = executor.getIntervalDays();
        if(InterestEarningExecutor.compoundingIntervalHasNotPassed(lastCompounded, intervalDays)) {
            return;
        }

        // Otherwise, we need to calculate the interest earned.
        Account account = executor.getAccount();
        double interestEarned = InterestEarningExecutor.calculateInterestEarned(account, executor.getApy(), executor.getNumCompoundingsPerYear());
        if(account instanceof LoanAccount) {
            // For loans, increase the amount of Money owed
            LoanAccount loanAccount = (LoanAccount)account;
            loanAccount.increaseMoneyOwedByAmount(interestEarned);
        } else {
            account.addValue(interestEarned);
        }
        executor.setLastCompoundedDate(new Date());
    }
}