package com.bank.atm.util;

/**
 * Class IllegalTransactionException is an exception that is thrown when some
 * transaction is attempted that is Illegal.
 *
 * @author: Nathan Lauer
 * @email: lauern@bu.edu
 * Creation Date: 12/9/20
 * <p>
 * Please feel free to ask me any questions. I hope you're having a nice day!
 */
public class IllegalTransactionException extends Exception {
    public IllegalTransactionException(String message) {
        super(message);
    }
}
