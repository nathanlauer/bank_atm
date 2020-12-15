package com.bank.atm.backend.transactions;

import com.bank.atm.backend.accounts.Account;
import com.bank.atm.backend.collections.AccountsCollectionManager;
import com.bank.atm.util.ID;
import com.bank.atm.util.IllegalTransactionException;
import com.bank.atm.util.Validations;

import java.io.IOException;
import java.util.NoSuchElementException;

/**
 * Class Deposit is a class that represents a User's deposit of money into an Account.
 *
 * @author: Nathan Lauer
 * @email: lauern@bu.edu
 * Creation Date: 12/15/20
 * <p>
 * Please feel free to ask me any questions. I hope you're having a nice day!
 */
public class Deposit extends Transaction {
    /**
     * Standard constructor
     * @param userId the ID of the user associated with this Deposit Transaction
     * @param toAccountId the ID of the Account for this Deposit Transaction
     * @param amount the amount of Money to be deposited
     */
    public Deposit(ID userId, ID toAccountId, double amount) {
        this(userId, toAccountId, amount, new ID());
    }

    /**
     * Standard constructor with specified transactionId
     * @param userId the ID of the user associated with this Deposit Transaction
     * @param toAccountId the ID of the Account for this Deposit Transaction
     * @param amount the amount of Money to be deposited
     * @param transactionId the ID of this Deposit Transaction
     */
    public Deposit(ID userId, ID toAccountId, double amount, ID transactionId) {
        super(userId, null, toAccountId, amount, transactionId);
    }

    /**
     * Executes the transaction. That is, for the specific semantics of the type of
     * Transaction, performs the associated logic and effectual changes.
     */
    @Override
    public void execute() throws IllegalTransactionException {
        try {
            Validations.nonNegative(getAmount());
        } catch (IllegalArgumentException e) {
            throw new IllegalTransactionException("Cannot deposit negative amount of money!");
        }

        if(!userCanExecute()) {
            throw new IllegalTransactionException("User is not allowed to execute this Transaction!");
        }

        try {
            Account account = AccountsCollectionManager.getInstance().find(getToAccountId());
            account.addValue(getAmount());
            AccountsCollectionManager.getInstance().save(account);
        } catch (NoSuchElementException | IOException e) {
            throw new IllegalTransactionException(e.getMessage());
        }
    }
}
