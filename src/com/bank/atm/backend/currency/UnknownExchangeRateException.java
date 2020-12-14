package com.bank.atm.backend.currency;

/**
 * Class UnknownExchangeRateException is an exception that is thrown when some
 * unknown exchange rate is queried.
 *
 * @author: Nathan Lauer
 * @email: lauern@bu.edu
 * Creation Date: 12/10/20
 * <p>
 * Please feel free to ask me any questions. I hope you're having a nice day!
 */
public class UnknownExchangeRateException extends RuntimeException {
    public UnknownExchangeRateException(String message) {
        super(message);
    }
}
