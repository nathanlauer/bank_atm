package com.bank.atm.backend.interest;

import com.bank.atm.backend.accounts.Account;
import com.bank.atm.util.Validations;

import java.util.Date;

/**
 * Class InterestCompoundedYearly
 *
 * @author: Nathan Lauer
 * @email: lauern@bu.edu
 * Creation Date: 12/11/20
 * <p>
 * Please feel free to ask me any questions. I hope you're having a nice day!
 */
public class InterestCompoundedYearly implements InterestEarningExecutor {
    transient private final Account account;
    private Date lastCompounded;
    private final int intervalDays;
    private final int numCompoundingsPerYear;
    private double apy;

    /**
     * Standard constructor
     * @param account the Account that will receive the calculated interest
     * @param lastCompounded Date when interest was last compounded. Normally,
     *                       this would refer to when an Account was opened.
     *                       However, it is possible that this interest executor
     *                       was dynamically added to the Account, so it is thus
     *                       important to specify when the Account was last compounded.
     * @param apy the Average Percentage Yield, given as a number in the range [0, 100]
     *            For example, apy = 3 means that the account will grow by approximately
     *            3% over the course of one year.
     */
    public InterestCompoundedYearly(Account account, Date lastCompounded, double apy) {
        Validations.nonNegative(apy);
        this.account = account;
        this.lastCompounded = lastCompounded;
        this.intervalDays = 365;
        this.numCompoundingsPerYear = 1;
    }

    /**
     * Checks if the compounding period has passed, and if so,
     * calculates the amount of interest earned and updates the
     * associated account.
     */
    @Override
    public void computeInterest() {
        // Ensure that enough time has passed
        if(InterestEarningExecutor.compoundingIntervalHasNotPassed(lastCompounded, intervalDays)) {
            return;
        }

        // Otherwise, we need to compute the interest earned.
        double interestEarned = InterestEarningExecutor.calculateInterestEarned(account, apy, numCompoundingsPerYear);
        double accountValue = account.getMoney().getAmount() + interestEarned;
        account.getMoney().setAmount(accountValue);
        lastCompounded = new Date();
    }

    /**
     * Sets the new APY (Annual Percentage Yield) for this InterestEarningExecutor
     *
     * @param apy the new APY for this Account
     */
    @Override
    public void setApy(double apy) {
        Validations.nonNegative(apy);
        this.apy = apy;
    }

    /**
     * @return the Average Percentage Yield of this InterestEarningExecutor
     */
    @Override
    public double getApy() {
        return apy;
    }

    /**
     * @return the Date at which this Account had interest compounded
     */
    @Override
    public Date getLastCompounded() {
        return lastCompounded;
    }

    /**
     * Sets the last Date at which this InterestEarningExecutor was compounded,
     * meaning the last Date at which interest was earned.
     *
     * @param date the last Date at which interest was earned.
     */
    @Override
    public void setLastCompoundedDate(Date date) {
        lastCompounded = date;
    }

    /**
     * @return the Account associated with this InterestEarningExecutor
     */
    @Override
    public Account getAccount() {
        return account;
    }

    /**
     * @return the number of days between compounding periods
     */
    @Override
    public int getIntervalDays() {
        return intervalDays;
    }

    /**
     * @return the number of times this InterestEarningExecutor is compounded per year.
     * For example, if it is compounded monthly, then this should return 12.
     */
    @Override
    public int getNumCompoundingsPerYear() {
        return numCompoundingsPerYear;
    }
}
