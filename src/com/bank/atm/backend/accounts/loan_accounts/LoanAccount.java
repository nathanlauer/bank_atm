package com.bank.atm.backend.accounts.loan_accounts;

import com.bank.atm.backend.accounts.Account;
import com.bank.atm.backend.currency.Currency;
import com.bank.atm.backend.currency.Money;
import com.bank.atm.util.ID;
import com.bank.atm.util.IllegalTransactionException;
import com.bank.atm.util.Validations;

import java.util.Date;
import java.util.List;

/**
 * Class LoanAccount
 *
 * @author: Nathan Lauer
 * @email: lauern@bu.edu
 * Creation Date: 12/9/20
 * <p>
 * Please feel free to ask me any questions. I hope you're having a nice day!
 */
public abstract class LoanAccount extends Account {
    private static final long serialVersionUID = 1L;
    private LoanState loanState;
    private final String collateral;
    private final double collateralValue;
    private final int creditScore;
    private final Money moneyPaid;
    private final Money moneyOwed;

    /**
     * Constructor that creates a LoanAccount with open Date now.
     * @param currency the Currency for this Account
     * @param money the initial Monetary value of this Account
     * @param managers List of Account managers
     */
    public LoanAccount(Currency currency, Money money, List<ID> managers, ID accountId, String collateral, double collateralValue, int creditScore) {
        this(new Date(), currency, money, managers, accountId, collateral, collateralValue, creditScore);
    }

    /**
     * Standard Constructor for a LoanAccount
     * @param opened the Date that this Account was opened
     * @param currency the Currency used by this Account
     * @param money the initial Monetary value of this Account
     * @param managers List of Users that are managers for this Account
     */
    public LoanAccount(Date opened, Currency currency, Money money, List<ID> managers, ID accountId, String collateral, double collateralValue, int creditScore) {
        super(opened, currency, money, managers, accountId);
        this.collateral = collateral;
        this.collateralValue = collateralValue;
        this.creditScore = creditScore;
        this.loanState = LoanState.REQUESTED;
        this.moneyOwed = new Money(money.getAmount());
        this.moneyPaid = new Money(0);
    }

    /**
     * Indicates whether or not this Loan has been completed
     * @return true if this Account has state COMPLETED, false otherwise
     */
    public boolean finished() {
        return this.getLoanState().equals(LoanState.COMPLETED);
    }

    /**
     *
     * @return the Amount of Money that still needs to be paid
     */
    public Money getMoneyPaid() {
        return moneyPaid;
    }

    /**
     *
     * @return the Money that the User still owes in this Account
     */
    public Money getMoneyOwed() {
        return moneyOwed;
    }

    /**
     * Increases the Amount of Money owed on this Account by the specified Amount.
     * @param amount the Amount of Money owed to increase
     */
    public void increaseMoneyOwedByAmount(double amount) {
        Validations.nonNegative(amount);
        if(this.hasState(LoanState.APPROVED)) {
            this.getMoneyOwed().addAmount(amount);
        }
    }

    /**
     * Makes a payment of a certain amount of Money towards this Account
     */
    public void makePayment(double amount) throws IllegalTransactionException {
        Validations.nonNegative(amount);

        // Increase Money paid, and decrease Money owed
        getMoneyPaid().addAmount(amount);
        if(this.moneyOwed.getAmount() <= amount) {
            this.moneyOwed.removeAmount(this.moneyOwed.getAmount());
            this.setLoanState(LoanState.COMPLETED);
        } else {
            this.moneyOwed.removeAmount(amount);
        }
    }

    /**
     *
     * @return the loanState of this Account
     */
    public LoanState getLoanState() {
        return loanState;
    }

    /**
     * Sets the loanState of this Loan Account to the passed in value
     * @param loanState the new state for this loan
     */
    public void setLoanState(LoanState loanState) {
        this.loanState = loanState;
    }

    /**
     * Switches the state of this Loan to an approved state
     */
    public void approve() {
        this.setLoanState(LoanState.APPROVED);
    }

    /**
     * Switches the state of this Loan to a rejected state
     */
    public void reject() {
        this.setLoanState(LoanState.REJECTED);
    }

    /**
     *
     * @return the collateral for this Loan
     */
    public String getCollateral() {
        return collateral;
    }

    /**
     *
     * @return the value of the collateral for this Loan
     */
    public double getCollateralValue() {
        return collateralValue;
    }

    /**
     *
     * @return the User's credit score
     */
    public int getCreditScore() {
        return creditScore;
    }

    /**
     * Indicates whether or not this Loan has the passed in state
     * @param state the loanState in question
     * @return true if this Loan has the same LoanState as that passed in, false otherwise.
     */
    public boolean hasState(LoanState state) {
        return getLoanState().equals(state);
    }

    /**
     * A Loan Account can be withdrawn from only if it is in an Approved state
     * @return true if this Account is approved, false otherwise
     */
    @Override
    public boolean canWithdraw() {
        return getLoanState().equals(LoanState.APPROVED);
    }
}
