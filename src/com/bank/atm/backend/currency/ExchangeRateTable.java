package com.bank.atm.backend.currency;

import com.bank.atm.util.Validations;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Class ExchangeRateTable is a Singleton class that stores the relevant exchange rates
 * for all currencies.
 *
 * @author: Nathan Lauer
 * @email: lauern@bu.edu
 * Creation Date: 12/10/20
 * <p>
 * Please feel free to ask me any questions. I hope you're having a nice day!
 */
public class ExchangeRateTable {
    private static ExchangeRateTable instance;
    private final List<ExchangeRate> exchangeRates;

    /**
     *
     * @return the Singleton instance for the ExchangeRateTable class.
     */
    public static ExchangeRateTable getInstance() {
        if(instance == null) {
            instance = new ExchangeRateTable();
        }
        return instance;
    }

    /**
     * Private constructor
     */
    private ExchangeRateTable() {
        exchangeRates = new ArrayList<>();
    }

    /**
     * Adds the passed in ExchangeRate for the passed in Currency.
     * @param exchangeRate object encapsulating the exchange rate
     */
    public void addExchangeRate(ExchangeRate exchangeRate) {
        this.exchangeRates.add(exchangeRate);
    }

    /**
     * Private helper function which traverses the List of known exchanges rates, and
     * finds the desired one for the passed in Currencies
     * @param from the Currency from
     * @param to the Currency to
     * @return the ExchangeRate between from and to
     * @throws UnknownExchangeRateException if no such exchange rate is found
     */
    private ExchangeRate findExchangeRate(Currency from, Currency to) throws UnknownExchangeRateException {
        for(ExchangeRate exchangeRate : exchangeRates) {
            if(exchangeRate.hasFromCurrency(from) && exchangeRate.hasToCurrency(to)) {
                return exchangeRate;
            }
        }
        throw new UnknownExchangeRateException("No Exchange Rate found between " + from.toString() + " and " + to.toString());
    }

    /**
     * Retrieves the exchange rate between the passed in Currencies.
     *
     * @param from the Currency being exchanged from
     * @param to the Currency being exchanged to
     * @throws UnknownExchangeRateException if this table does not have an ExchangeRate for the two Currencies.
     * @return the ExchangeRate between the two Currencies.
     */
    public ExchangeRate getExchangeRate(Currency from, Currency to) throws UnknownExchangeRateException {
        return findExchangeRate(from, to);
    }

    /**
     * Updates the exchange rate between the two Currencies. Note that this goes "both directions"
     * meaning that we update the rate between from and to, as well as 1/rate for to and from.
     * This helps to ensure consistency, and makes the client's jobs a little bit easier.
     *
     * @param from the Currency being exchanged from
     * @param to the Currency being exchanged to
     * @param rate the new rate between these two Currencies.
     * @throws UnknownExchangeRateException if this table does not have an ExchangeRate for the two Currencies.
     */
    public void updateExchangeRate(Currency from, Currency to, double rate) throws UnknownExchangeRateException {
        Validations.positiveValue(rate);
        double oppositeRate = 1.0 / rate;

        // Update in both directions
        this.updateExchangeRateHelper(from, to, rate);
        this.updateExchangeRateHelper(to, from, oppositeRate);
    }

    /**
     * Helper function which does the heavy lifting for updating exchange rates.
     * See the above function for documentation.
     * @param from the Currency being exchanged from
     * @param to the Currency being exchanged to
     * @param rate the new rate between these two Currencies.
     * @throws UnknownExchangeRateException if this table does not have an ExchangeRate for the two Currencies.
     */
    private void updateExchangeRateHelper(Currency from, Currency to, double rate) throws UnknownExchangeRateException {
        ExchangeRate exchangeRate = findExchangeRate(from, to);
        exchangeRate.setRate(rate);
    }

    /**
     * @return String representation of this ExchangeRateTable object.
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for(ExchangeRate exchangeRate : exchangeRates) {
            builder.append(exchangeRate.toString());
            builder.append("\n"); // newline
        }
        return builder.toString();
    }
}
