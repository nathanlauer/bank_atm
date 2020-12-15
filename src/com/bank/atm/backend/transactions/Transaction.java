package com.bank.atm.backend.transactions;

import com.bank.atm.util.ID;
import com.bank.atm.util.Identifiable;
import com.bank.atm.util.IllegalTransactionException;

import java.io.Serializable;

/**
 * Class Transaction
 *
 * @author: Nathan Lauer
 * @email: lauern@bu.edu
 * Creation Date: 12/15/20
 * <p>
 * Please feel free to ask me any questions. I hope you're having a nice day!
 */
public abstract class Transaction implements Serializable, Identifiable {
    private final ID transactionId;
    private final ID fromAccountId;
    private final ID toAccountId;
    private final ID userId;
    private final double amount;

    /**
     * Standard constructor
     * @param userId the ID of the User associated with this Transaction
     * @param fromAccountId the ID of the Account that this Transaction is from
     * @param toAccountId the ID of the Account that this Transaction is to
     * @param amount the amount of money associated with this Transaction
     */
    public Transaction(ID userId, ID fromAccountId, ID toAccountId, double amount) {
        this(userId, fromAccountId, toAccountId, amount, new ID());
    }

    /**
     * Constructor with specified transactionId
     * @param userId the ID of the User associated with this transaction
     * @param fromAccountId the ID of the Account that this Transaction is from
     * @param toAccountId the ID of the Account that this Transaction is to
     * @param amount the amount of money associated with this Transaction
     * @param transactionId the ID of this transaction
     */
    public Transaction(ID userId, ID fromAccountId, ID toAccountId, double amount, ID transactionId) {
        this.userId = userId;
        this.fromAccountId = fromAccountId;
        this.toAccountId = toAccountId;
        this.amount = amount;
        this.transactionId = transactionId;
    }

    /**
     *
     * @return the ID of this Identifiable entity
     */
    @Override
    public ID getID() {
        return transactionId;
    }

    /**
     * Indicates whether or not this Identifiable has the passed in id.
     * @param id the ID in question
     * @return true if this entity has the same id, false otherwise
     */
    @Override
    public boolean hasID(ID id) {
        return getID().equals(id);
    }

    /**
     *
     * @return the Amount of Money associated with this Transaction
     */
    public double getAmount() {
        return amount;
    }

    /**
     *
     * @return the ID of the Account that this Transaction is from
     */
    public ID getFromAccountId() {
        return fromAccountId;
    }

    /**
     *
     * @return the ID of the Account that this Transaction is to
     */
    public ID getToAccountId() {
        return toAccountId;
    }

    /**
     *
     * @return the ID of the User associated with this Transaction
     */
    public ID getUserId() {
        return this.userId;
    }

    /**
     * Indicates whether or not this Transaction belongs to this User
     * @param userId the ID of the relevant User
     * @return true if this transaction is for this User, false otherwise
     */
    public boolean isForUser(ID userId) {
        return getUserId().equals(userId);
    }


    /**
     * Indicates whether or not this Transaction belongs to this Account
     * @param accountId the Id of the relevant Account
     * @return true if one of the Accounts associated with this Transaction is identified by accountId, false otherwise
     */
    public boolean isForAccount(ID accountId) {
        return getFromAccountId().equals(accountId) || getToAccountId().equals(accountId);
    }

    /**
     * Executes the transaction. That is, for the specific semantics of the type of
     * Transaction, performs the associated logic and effectual changes.
     */
    public abstract void execute() throws IllegalTransactionException;
}
