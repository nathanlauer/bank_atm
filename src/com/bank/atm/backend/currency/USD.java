package com.bank.atm.backend.currency;

import java.text.DecimalFormat;

/**
 * Class USD
 *
 * @author: Nathan Lauer
 * @email: lauern@bu.edu
 * Creation Date: 12/9/20
 * <p>
 * Please feel free to ask me any questions. I hope you're having a nice day!
 */
public class USD extends Currency {
    private static USD instance;

    /**
     * Each currency is implemented as a Singleton, so this abstract method
     * helps to enforce that.
     * @return the instance representing the relevant Currency.
     */
    public Currency getInstance() {
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
        return formatter.format(money.getAmount());
    }

    /**
     * Exchanges the passed in Money to the passed in Currency.
     * For example, if this Currency is worth twice as much as the
     * other Currency (meaning: Money with amount 2 in this currency
     * is equal to Money with amount 4 in the other currency), then
     * returns a Money with twice the value as the passed in Money.
     *
     * @param money the Money to be exchanged
     * @param other the new Currency for that Money
     * @return Money representation of the exchanged Money.
     */
    @Override
    public Money exchange(Money money, Currency other) {
        return null;
    }

    /**
     * @return a String representation of this Currency
     */
    @Override
    public String toString() {
        return "USD";
    }
}
