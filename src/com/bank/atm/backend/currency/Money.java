package com.bank.atm.backend.currency;

import com.bank.atm.util.IllegalTransactionException;
import com.bank.atm.util.Validations;

/**
 * Class Money is an abstract representation of Money - that is, it's just a number.
 * It is up to the various currency classes to display this Money in a meaningful
 * fashion.
 *
 * @author: Nathan Lauer
 * @email: lauern@bu.edu
 * Creation Date: 12/9/20
 * <p>
 * Please feel free to ask me any questions. I hope you're having a nice day!
 */
public class Money implements Comparable<Money> {
    private double amount;

    /**
     * Empty constructor, creates new Money object with zero value.
     */
    public Money() {
        this(0);
    }

    /**
     * Standard constructor.
     * @param amount the initial value of this Money
     */
    public Money(double amount) {
        Validations.nonNegative(amount);
        this.amount = amount;
    }

    /**
     * Sets the amount of this Money to the passed in value.
     * @param amount new value for this Money.
     */
    public void setAmount(double amount) {
        Validations.nonNegative(amount);
        this.amount = amount;
    }

    /**
     *
     * @return the value of this Money.
     */
    public double getAmount() {
        return amount;
    }

    /**
     * Removes the passed in amount of Money.
     * @param amount the amount to remove
     * @throws IllegalTransactionException if attempt to remove more Money than exists
     */
    public void removeAmount(double amount) throws IllegalTransactionException {
        Validations.nonNegative(amount);
        if(amount > this.amount) {
            throw new IllegalTransactionException("Can't remove more Money than is contained!");
        }
        this.amount -= amount;
    }

    /**
     * Adds the indicated amount of Money to this value
     * @param amount the amount to add
     */
    public void addAmount(double amount) {
        Validations.nonNegative(amount);
        this.amount += amount;
    }

    /**
     * @return String representation of this Money object.
     */
    @Override
    public String toString() {
        return String.valueOf(amount);
    }

    /**
     * Defines equality for two Money objects.
     *
     * @param o Other object in consideration for equality
     * @return true if o is an instance of Money, and they contain the same amount
     */
    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }

        if (!(o instanceof Money)) {
            return false;
        }

        Money other = (Money) o;
        return Double.compare(getAmount(), other.getAmount()) == 0;
    }

    /**
     * Compares this object with the specified object for order.  Returns a
     * negative integer, zero, or a positive integer as this object is less
     * than, equal to, or greater than the specified object.
     *
     * @param o the object to be compared.
     * @return a negative integer, zero, or a positive integer as this object
     * is less than, equal to, or greater than the specified object.
     * @throws NullPointerException if the specified object is null
     * @throws ClassCastException   if the specified object's type prevents it
     *                              from being compared to this object.
     */
    @Override
    public int compareTo(Money o) {
        return (int)(getAmount() - o.getAmount());
    }
}
