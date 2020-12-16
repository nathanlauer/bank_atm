package com.bank.atm.backend.interest;

import com.bank.atm.backend.accounts.Account;
import com.bank.atm.util.ID;
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
public class InterestCompoundedYearly extends Interest {
    private static final long serialVersionUID = 1L;
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
        super(account.getID(), lastCompounded, apy, 365, 1);
    }
}
