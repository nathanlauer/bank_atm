package com.bank.atm.backend.transactions;

import com.bank.atm.backend.accounts.Account;
import com.bank.atm.backend.accounts.loan_accounts.LoanAccount;
import com.bank.atm.backend.collections.AccountsCollectionManager;
import com.bank.atm.util.ID;
import com.bank.atm.util.IllegalTransactionException;
import com.bank.atm.util.Validations;

import java.io.IOException;
import java.util.NoSuchElementException;

/**
 * Class LoanPayment is a Transaction that represents a User's payment towards a loan.
 *
 * @author: Nathan Lauer
 * @email: lauern@bu.edu
 * Creation Date: 12/15/20
 * <p>
 * Please feel free to ask me any questions. I hope you're having a nice day!
 */
public class LoanPayment extends Transaction {
    private static final long serialVersionUID = 1L;
    /**
     * Standard constructor
     * @param userId the ID of the user associated with this LoanPayment Transaction
     * @param toAccountId the ID of the Account for this LoanPayment Transaction
     * @param amount the amount of Money to be paid
     */
    public LoanPayment(ID userId, ID toAccountId, double amount) {
        this(userId, toAccountId, amount, new ID());
    }

    /**
     * Standard constructor with specified transactionId
     * @param userId the ID of the user associated with this LoanPayment Transaction
     * @param toAccountId the ID of the Account for this LoanPayment Transaction
     * @param amount the amount of Money to be paid
     * @param transactionId the ID of this LoanPayment Transaction
     */
    public LoanPayment(ID userId, ID toAccountId, double amount, ID transactionId) {
        super(userId, null, toAccountId, amount, transactionId, TransactionType.LOAN_PAYMENT);
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
            LoanAccount loanAccount = (LoanAccount)account;
            loanAccount.makePayment(getAmount());
            AccountsCollectionManager.getInstance().save(account);
        } catch (NoSuchElementException | IOException e) {
            throw new IllegalTransactionException(e.getMessage());
        }
    }
}
