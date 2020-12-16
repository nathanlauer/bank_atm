package com.bank.atm.backend.accounts;

/**
 * Enum AccountTypes
 *
 * @author: Nathan Lauer
 * @email: lauern@bu.edu
 * Creation Date: 12/10/20
 * <p>
 * Please feel free to ask me any questions. I hope you're having a nice day!
 */
public enum AccountType {
    BASIC_CHECKING_ACCOUNT("Basic Checking"),
    FOUR_OH_ONE_K_ACCOUNT("401K"),
    GENERIC_LOAN_ACCOUNT("Generic Loan"),
    HIGH_INTEREST_SAVINGS_ACCOUNT("High-Interest Savings"),
    LOW_INTEREST_SAVINGS_ACCOUNT("Low-Interest Savings"),
    PREMIUM_CHECKING_ACCOUNT("Premium Checking");

    private final String accountName;
    private AccountType(String name) {
        accountName = name;
    }

    @Override
    public String toString() {
        return accountName;
    }
}
