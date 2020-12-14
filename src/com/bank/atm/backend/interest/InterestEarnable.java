package com.bank.atm.backend.interest;

/**
 * Interface InterestEarnable describes the behavior of any entity which
 * can earn interest.
 *
 * @author: Nathan Lauer
 * @email: lauern@bu.edu
 * Creation Date: 12/11/20
 * <p>
 * Please feel free to ask me any questions. I hope you're having a nice day!
 */
public interface InterestEarnable {
    /**
     * Any entity which can earn interest must be able to provide
     * an instance of an InterestEarningExecutor, which computes
     * the interest for said entity.
     * @return the InterestEarningExecutor associated with the relevant Account.
     */
    InterestEarningExecutor getInterestEarningExecutor();

    /**
     * Sets the InterestEarningExecutor for this Account
     * @param executor the new InterestEarningExecutor for this Account
     */
    void setInterestEarningExecutor(InterestEarningExecutor executor);
}
