package com.bank.atm.backend.accounts;

/**
 * Class UnknownAccountTypeException is an Exception thrown when some unknown type
 * of Account is requested.
 *
 * @author: Nathan Lauer
 * @email: lauern@bu.edu
 * Creation Date: 12/10/20
 * <p>
 * Please feel free to ask me any questions. I hope you're having a nice day!
 */
public class UnknownAccountTypeException extends Exception {
    public UnknownAccountTypeException(String message) {
        super(message);
    }
}
