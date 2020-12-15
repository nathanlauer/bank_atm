package com.bank.atm.backend.transactions;

import com.bank.atm.backend.accounts.Account;
import com.bank.atm.backend.collections.AccountsCollectionManager;
import com.bank.atm.backend.collections.UsersCollectionManager;
import com.bank.atm.backend.users.User;
import com.bank.atm.util.ID;
import com.bank.atm.util.Identifiable;
import com.bank.atm.util.IllegalTransactionException;

import java.io.Serializable;
import java.util.Date;
import java.util.NoSuchElementException;

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
    private final Date date;

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
        date = new Date();
    }

    /**
     * Indicates whether or not this Transaction has a To Account
     * @return true if toAccount is not null, false otherwise
     */
    public boolean hasToAccount() {
        return getToAccountId() != null;
    }

    /**
     * Indicates whether or not this Transaction has a From Account
     * @return true if fromAccountId is not null, false otherwise
     */
    public boolean hasFromAccount() {
        return getFromAccountId() != null;
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
        if(hasFromAccount()) {
            if(getFromAccountId().equals(accountId)) {
                return true;
            }
        }

        if(hasFromAccount()) {
            return getToAccountId().equals(accountId);
        }

        return false;
    }

    /**
     *
     * @return the Date at which this Transaction occurred
     */
    public Date getDate() {
        return date;
    }

    /**
     * Indicates whether or not this Transaction happened after the passed in Date.
     * @param other the other Date in question
     * @return true if the Date associated with this Transaction is greater than
     * the passed in Date
     */
    public boolean occurredAfterDate(Date other) {
        return date.after(other);
    }

    /**
     * Indicates whether or not this Transaction happened before the passed in Date
     * @param other the other Date in question
     * @return true if the Date associated with this Transaction is less than the
     * passed in Date.
     */
    public boolean occurredBeforeDate(Date other) {
        return date.before(other);
    }

    /**
     * Protected helper function for use by concrete subclasses, to determine
     * whether or not the User associated with this Transaction has access
     * rights.
     * @return true if the User associated with this Transaction is a manager
     * for all the accounts associated with this Transaction.
     */
    protected boolean userCanExecute() {
        boolean userManagesToAccount = false;
        boolean userManagesFromAccount = false;

        try {
            User user = UsersCollectionManager.getInstance().find(getUserId());
            if(hasToAccount()) {
                Account toAccount = AccountsCollectionManager.getInstance().find(getToAccountId());
                if(toAccount.isAccountManager(user)) {
                    userManagesToAccount = true;
                }
            } else {
                // There is no toAccount, so just simulate that the user has access to "it"
                userManagesToAccount = true;
            }

            if(hasFromAccount()) {
                Account fromAccount = AccountsCollectionManager.getInstance().find(getFromAccountId());
                if(fromAccount.isAccountManager(user)) {
                    userManagesFromAccount = true;
                }
            } else {
                // There is no fromAccount, so just simulate that the user has access to "it"
                userManagesFromAccount = true;
            }
        } catch (NoSuchElementException e) {
            return false;
        }

        return userManagesToAccount && userManagesFromAccount;
    }

    /**
     * Executes the transaction. That is, for the specific semantics of the type of
     * Transaction, performs the associated logic and effectual changes.
     */
    public abstract void execute() throws IllegalTransactionException;
}
