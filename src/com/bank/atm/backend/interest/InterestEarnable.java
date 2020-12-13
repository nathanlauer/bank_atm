package com.bank.atm.backend.interest;

/**
 * Interface InterestEarnable
 *
 * @author: Nathan Lauer
 * @email: lauern@bu.edu
 * Creation Date: 12/9/20
 * <p>
 * Please feel free to ask me any questions. I hope you're having a nice day!
 */
public interface InterestEarnable {
    /**
     * The InterestEarningExecutor is the class which contains the necessary
     * data and information required to properly compute and earn interest.
     *
     * @return the executor for this InterestEarnable
     */
    public InterestEarningExecutor getInterestEarningExecutor();
}
