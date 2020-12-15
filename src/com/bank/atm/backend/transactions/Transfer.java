package com.bank.atm.backend.transactions;

import com.bank.atm.backend.accounts.Account;
import com.bank.atm.backend.collections.AccountsCollectionManager;
import com.bank.atm.util.ID;
import com.bank.atm.util.IllegalTransactionException;
import com.bank.atm.util.Validations;

import java.util.NoSuchElementException;

/**
 * Class Transfer
 *
 * @author: Nathan Lauer
 * @email: lauern@bu.edu
 * Creation Date: 12/15/20
 * <p>
 * Please feel free to ask me any questions. I hope you're having a nice day!
 */
public class Transfer extends Transaction {

    /**
     * Standard constructor
     * @param userId the ID of the user associated with this Withdraw Transfer
     * @param fromAccountId the ID of the Account where money is being taken from
     * @param toAccountId the ID of the Account where money is being moved to
     * @param amount the amount of Money to be transferred
     */
    public Transfer(ID userId, ID fromAccountId, ID toAccountId, double amount) {
        this(userId, fromAccountId, toAccountId, amount, new ID());
    }

    /**
     * Standard constructor with specified transactionId
     * @param userId the ID of the user associated with this Withdraw Transfer
     * @param fromAccountId the ID of the Account where money is being taken from
     * @param toAccountId the ID of the Account where money is being moved to
     * @param amount the amount of Money to be transferred
     * @param transactionId the ID of this Withdraw Transfer
     */
    public Transfer(ID userId, ID fromAccountId, ID toAccountId, double amount, ID transactionId) {
        super(userId, fromAccountId, toAccountId, amount, transactionId);
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
            throw new IllegalTransactionException("Cannot transfer negative amount of money!");
        }

        try {
            Account fromAccount = AccountsCollectionManager.getInstance().find(getFromAccountId());
            Account toAccount = AccountsCollectionManager.getInstance().find(getToAccountId());

            // Performing the removeValue method will internally check that there is enough money
            // If there isn't, it will throw an IllegalTransactionException
            fromAccount.removeValue(getAmount());
            toAccount.addValue(getAmount());
        } catch (NoSuchElementException e) {
            throw new IllegalTransactionException(e.getMessage());
        }
    }
}
