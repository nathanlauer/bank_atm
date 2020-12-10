package com.bank.atm.backend.currency;

/**
 * Class Currency is an abstract class which represents some currency.
 *
 * @author: Nathan Lauer
 * @email: lauern@bu.edu
 * Creation Date: 12/9/20
 * <p>
 * Please feel free to ask me any questions. I hope you're having a nice day!
 */
public abstract class Currency {
    /**
     * Each currency is implemented as a Singleton, so this abstract method
     * helps to enforce that.
     * @return the instance representing the relevant Currency.
     */
    public abstract Currency getInstance();

    /**
     * Displays the passed in Money as expected for the given Currency.
     * @param money the Money to be displayed
     * @return String representation of the Money
     */
    public abstract String displayMoney(Money money);

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
    public abstract Money exchange(Money money, Currency other);

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
     * @param other the Currency to demarcate the exchange rate
     * @param rate the exchange rate
     */
    public void setExchangeRate(Currency other, double rate) {

    }

    /**
     *
     * @return a String representation of this Currency
     */
    public abstract String toString();

    @Override
    public boolean equals(Object o) {
        if(o == null) {
            return false;
        }

        if(!(o instanceof Currency)) {
            return false;
        }

        Currency other = (Currency)o;
        // This will compare instances, meaning objects, so it is effectively
        // comparing memory addresses.
        return this.getInstance().equals(other.getInstance());
    }
}
