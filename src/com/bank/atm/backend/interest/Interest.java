package com.bank.atm.backend.interest;

import com.bank.atm.backend.accounts.Account;
import com.bank.atm.backend.collections.AccountsCollectionManager;
import com.bank.atm.util.ID;
import com.bank.atm.util.Identifiable;
import com.bank.atm.util.Validations;

import java.io.Serializable;
import java.util.Date;

/**
 * Class Interest
 *
 * @author: Nathan Lauer
 * @email: lauern@bu.edu
 * Creation Date: 12/15/20
 * <p>
 * Please feel free to ask me any questions. I hope you're having a nice day!
 */
public abstract class Interest implements Serializable, InterestEarningExecutor, Identifiable {
    private final ID id;
    private final ID accountId;
    private Date lastCompounded;
    private final int intervalDays;
    private final int numCompoundingsPerYear;
    private double apy;

    /**
     * Standard constructor for an Interest object
     * @param accountId the ID of the Account that this Interest object is for
     * @param lastCompounded the Date at which this Interest object was last compounded
     * @param apy the apy of this Interest
     * @param intervalDays number of days between one earning of interest and the next
     * @param numCompoundingsPerYear number of times Interest is compounded per year
     */
    public Interest(ID accountId, Date lastCompounded, double apy, int intervalDays, int numCompoundingsPerYear) {
        Validations.nonNegative(apy);
        Validations.nonNegative(intervalDays);
        Validations.nonNegative(numCompoundingsPerYear);
        id = new ID();
        this.accountId = accountId;
        this.lastCompounded = lastCompounded;
        this.intervalDays = intervalDays;
        this.numCompoundingsPerYear = numCompoundingsPerYear;
        this.apy = apy;
    }

    /**
     *
     * @return the ID of this Identifiable entity
     */
    public ID getID() {
        return id;
    }

    /**
     * Indicates whether or not this Identifiable has the passed in id.
     * @param id the ID in question
     * @return true if this entity has the same id, false otherwise
     */
    public boolean hasID(ID id) {
        return getID().equals(id);
    }

    /**
     * Indicates whether or not this Interest object is for the Account identified by accountId
     * @param accountId the Account in question
     * @return true if this Interest is for the Account
     */
    public boolean forAccount(ID accountId) {
        return this.accountId.equals(accountId);
    }

    /**
     * Checks if the compounding period has passed, and if so,
     * calculates the amount of interest earned and updates the
     * associated account.
     */
    @Override
    public void computeInterest() {
        InterestEarningExecutor.computeInterestAndUpdateAccount(this);
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
        this.lastCompounded = date;
    }

    /**
     * @return the Account associated with this InterestEarningExecutor
     */
    @Override
    public Account getAccount() {
        return AccountsCollectionManager.getInstance().find(accountId);
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
