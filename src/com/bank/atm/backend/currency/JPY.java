package com.bank.atm.backend.currency;

import java.text.DecimalFormat;

/**
 * Class JPY represents Japanese Yen as a Currency.
 *
 * @author: Nathan Lauer
 * @email: lauern@bu.edu
 * Creation Date: 12/10/20
 * <p>
 * Please feel free to ask me any questions. I hope you're having a nice day!
 */
public class JPY implements Currency {
    private static final long serialVersionUID = 1L;
    private static JPY instance;

    /**
     * Each currency is implemented as a Singleton, so this abstract method
     * helps to enforce that.
     *
     * @return the instance representing the relevant Currency.
     */
    public static Currency getInstance() {
        if(instance == null) {
            instance = new JPY();
        }
        return instance;
    }

    private JPY() {
        // empty, but private to prevent outside access
    }

    /**
     * Displays the passed in Money as expected for the given Currency.
     *
     * @param money the Money to be displayed
     * @return String representation of the Money
     */
    @Override
    public String displayMoney(Money money) {
        DecimalFormat formatter = new DecimalFormat("#,###.00");
        return "\u00A5" + formatter.format(money.getAmount());
    }

    /**
     * @return a String representation of this Currency
     */
    @Override
    public String toString() {
        return "JPY";
    }
}
