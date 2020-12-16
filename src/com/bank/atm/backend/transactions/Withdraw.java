package com.bank.atm.backend.transactions;

import com.bank.atm.backend.accounts.Account;
import com.bank.atm.backend.collections.AccountsCollectionManager;
import com.bank.atm.util.ID;
import com.bank.atm.util.IllegalTransactionException;
import com.bank.atm.util.Validations;

import java.io.IOException;
import java.util.NoSuchElementException;

/**
 * Class Withdraw is a class that represents the withdrawal of money from an account.
 *
 * @author: Nathan Lauer
 * @email: lauern@bu.edu
 * Creation Date: 12/15/20
 * <p>
 * Please feel free to ask me any questions. I hope you're having a nice day!
 */
public class Withdraw extends Transaction {
    private static final long serialVersionUID = 1L;
    /**
     * Standard constructor
     * @param userId the ID of the user associated with this Withdraw Transaction
     * @param fromAccountId the ID of the Account for this Withdraw Transaction
     * @param amount the amount of Money to be withdrawn
     */
    public Withdraw(ID userId, ID fromAccountId, double amount) {
        this(userId, fromAccountId, amount, new ID());
    }

    /**
     * Standard constructor with specified transactionId
     * @param userId the ID of the user associated with this Withdraw Transaction
     * @param fromAccountId the ID of the Account for this Withdraw Transaction
     * @param amount the amount of Money to be withdrawn
     * @param transactionId the ID of this Withdraw Transaction
     */
    public Withdraw(ID userId, ID fromAccountId, double amount, ID transactionId) {
        super(userId, fromAccountId, null, amount, transactionId, TransactionType.WITHDRAW);
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
            throw new IllegalTransactionException("Cannot withdraw negative amount of money!");
        }

        if(!userCanExecute()) {
            throw new IllegalTransactionException("User is not allowed to execute this Transaction!");
        }

        try {
            Account account = AccountsCollectionManager.getInstance().find(getFromAccountId());

            // Ensure money can be withdrawn - specifically, this is mostly for loans that
            // are in a requested or rejected state.
            if(!account.canWithdraw()) {
                throw new IllegalTransactionException("Cannot withdraw money from this Account!");
            }

            // Performing the removeValue method will internally check that there is enough money
            // If there isn't, it will throw an IllegalTransactionException
            account.removeValue(getAmount());
            AccountsCollectionManager.getInstance().save(account);
        } catch (NoSuchElementException | IOException e) {
            throw new IllegalTransactionException(e.getMessage());
        }
    }
}
