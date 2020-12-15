package com.bank.atm.backend.accounts.loan_accounts;

import com.bank.atm.backend.accounts.Account;
import com.bank.atm.backend.currency.Currency;
import com.bank.atm.backend.currency.Money;
import com.bank.atm.util.ID;

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
public class LoanAccount extends Account {
    private LoanState loanState;
    private String collateral;
    private double collateralValue;
    private int creditScore;

    // TODO: enum for loan state
    // TODO: CollectionsManager allow banker to approve loan
    // TODO: methods to set loan state
    // TODO: AccountsCollectionManager - view loans by state, also for user
    // TODO: Transaction to deposit money to Loan Account.

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
    }
}
