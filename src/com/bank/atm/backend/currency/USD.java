package com.bank.atm.backend.currency;

import java.text.DecimalFormat;

/**
 * Class USD represents the US Dollar as a Currency.
 *
 * @author: Nathan Lauer
 * @email: lauern@bu.edu
 * Creation Date: 12/9/20
 * <p>
 * Please feel free to ask me any questions. I hope you're having a nice day!
 */
public class USD implements Currency {
    private static final long serialVersionUID = 1L;
    private static USD instance;

    /**
     * Each currency is implemented as a Singleton, so this abstract method
     * helps to enforce that.
     * @return the instance representing the relevant Currency.
     */
    public static Currency getInstance() {
        if(instance == null) {
            instance = new USD();
        }
        return instance;
    }

    /**
     * Private constructor.
     */
    private USD() {
        // Empty, but private to prevent outside access.
    }

    /**
     * Displays the passed in Money as expected for the given Currency.
     *
     * @param money the Money to be displayed
     * @return String representation of the Money. For example, $13,456.89
     */
    @Override
    public String displayMoney(Money money) {
        DecimalFormat formatter = new DecimalFormat("#,###.00");
        return "$" + formatter.format(money.getAmount());
    }

    /**
     * @return a String representation of this Currency
     */
    @Override
    public String toString() {
        return "USD";
    }

    @Override
    public boolean equals(Object o) {
        if(o == null) {
            return false;
        }

        if(!(o instanceof USD)) {
            return false;
        }

        USD other = (USD)o;
        return this.toString().equals(other.toString());
    }
}
