package com.bank.atm.backend.currency;

import com.bank.atm.util.Validations;

/**
 * Class ExchangeRate is a class that represents a rate of exchange between two Currencies.
 *
 * @author: Nathan Lauer
 * @email: lauern@bu.edu
 * Creation Date: 12/10/20
 * <p>
 * Please feel free to ask me any questions. I hope you're having a nice day!
 */
public class ExchangeRate {
    private final Currency from;
    private final Currency to;
    private double rate;

    /**
     * Standard constructor
     * @param from the Currency being converted from
     * @param to the Currency being converted to
     * @param rate the rate at which the exchange occurs
     */
    public ExchangeRate(Currency from, Currency to, double rate) {
        Validations.positiveValue(rate);
        this.from = from;
        this.to = to;
        this.rate = rate;
    }

    /**
     * Updates the exchange rate between the two Currencies.
     * @param rate the new rate to exchange between the Currencies.
     */
    public void setRate(double rate) {
        Validations.positiveValue(rate);
        this.rate = rate;
    }

    /**
     *
     * @return the rate of exchange
     */
    public double getRate() {
        return rate;
    }

    /**
     *
     * @return the Currency being exchanges from.
     */
    public Currency getFrom() {
        return from;
    }

    /**
     * Indicates whether or no this ExchangeRate has the From Currency
     * @param currency the relevant Currency
     * @return true if this Exchange has this Currency as its From Currency, false otherwise.
     */
    public boolean hasFromCurrency(Currency currency) {
        return getFrom().equals(currency);
    }

    /**
     *
     * @return the Currency being exchanged to
     */
    public Currency getTo() {
        return to;
    }

    /**
     * Indicates whether or no this ExchangeRate has the To Currency
     * @param currency the relevant Currency
     * @return true if this Exchange has this Currency as its To Currency, false otherwise.
     */
    public boolean hasToCurrency(Currency currency) {
        return getTo().equals(currency);
    }

    /**
     * Defines equality for an Exchange rate
     * @param o Object in question
     * @return True if o is an exchange rate, with the same from Currency, to Currency, and rate.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ExchangeRate that = (ExchangeRate) o;
        return Double.compare(that.rate, rate) == 0 &&
                from.equals(that.from) &&
                to.equals(that.to);
    }

    /**
     *
     * @return a String representation of this Exchange RAte
     */
    @Override
    public String toString() {
        return from.toString() + " -> " + to.toString() + " @" + rate;
    }
}
