package com.bank.atm.backend.currency;

import java.io.Serializable;

/**
 * Interface Currency is a description of the entities which represent some currency.
 *
 * @author: Nathan Lauer
 * @email: lauern@bu.edu
 * Creation Date: 12/9/20
 * <p>
 * Please feel free to ask me any questions. I hope you're having a nice day!
 */
public interface Currency extends Serializable {
    /**
     * Displays the passed in Money as expected for the given Currency.
     * @param money the Money to be displayed
     * @return String representation of the Money
     */
    String displayMoney(Money money);

    /**
     * Exchanges the passed in Money to the passed in Currency.
     * For example, if this Currency is worth twice as much as the
     * other Currency (meaning: Money with amount 2 in this currency
     * is equal to Money with amount 4 in the other currency), then
     * returns a Money with twice the value as the passed in Money.
     *
     * @param money the Money to be exchanged
     * @param from the current Currency for that Money
     * @param to the desired Currency for that Money
     * @return Money representation of the exchanged Money.
     */
    static Money exchange(Money money, Currency from, Currency to) throws UnknownExchangeRateException {
        ExchangeRate exchangeRate = ExchangeRateTable.getInstance().getExchangeRate(from, to);
        double currentAmount = money.getAmount();
        double exchangedAmount = currentAmount * exchangeRate.getRate();
        return new Money(exchangedAmount);
    }

    /**
     * Sets the exchange rate between one currency and another.
     *
     * For example, one might do:
     * USD.getInstance().setExchangeRate(Euro.getInstance(), 0.82);
     * Whenever one Currency has its exchange rate set to another,
     * this method also performs the inverse, to guarantee that
     * exchanges can happen between currencies. Thus, in the example
     * above, this method would also do the equivalent of:
     * Euro.getInstance().setExchangeRate(USD.getInstance(), 1/0.82);
     *
     * @param from the Currency which is being exchanged from
     * @param to the Currency which is being exchanged to
     * @param rate the exchange rate
     */
    static void setExchangeRate(Currency from, Currency to, double rate) {
        ExchangeRate forward = new ExchangeRate(from, to, rate);
        ExchangeRateTable.getInstance().addExchangeRate(forward);

        ExchangeRate backward = new ExchangeRate(to, from, 1.0 / rate);
        ExchangeRateTable.getInstance().addExchangeRate(backward);
    }

    /**
     *
     * @return a String representation of this Currency
     */
    @Override
    String toString();

    @Override
    boolean equals(Object o);
}
