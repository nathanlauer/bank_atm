package com.bank.atm.backend.currency;

import com.bank.atm.util.Validations;

import java.util.HashMap;
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
    private final HashMap<Currency, ExchangeRate> exchangeRateTable;

    /**
     *
     * @return the Singleton instance for the ExchangeRateTable class.
     */
    public ExchangeRateTable getInstance() {
        if(instance == null) {
            instance = new ExchangeRateTable();
        }
        return instance;
    }

    /**
     * Private constructor
     */
    private ExchangeRateTable() {
        exchangeRateTable = new HashMap<>();
    }

    /**
     * Adds the passed in ExchangeRate for the passed in Currency.
     * Note that the Currency exchanged to is contained within the
     * exchangeRate object.
     * @param from the from Currency
     * @param exchangeRate object encapsulating the exchange rate
     */
    public void addExchangeRate(Currency from, ExchangeRate exchangeRate) {
        this.exchangeRateTable.put(from, exchangeRate);
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
        for(Map.Entry<Currency, ExchangeRate> entry : exchangeRateTable.entrySet()) {
            Currency currency = entry.getKey();
            if(currency.equals(from)) {
                ExchangeRate exchangeRate = entry.getValue();
                if(exchangeRate.getTo().equals(to)) {
                    return exchangeRate;
                }
            }
        }
        throw new UnknownExchangeRateException("No Exchange Rate found between " + from.toString() + " and " + to.toString());
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
        boolean found = false;
        for(Map.Entry<Currency, ExchangeRate> entry : exchangeRateTable.entrySet()) {
            Currency currency = entry.getKey();
            if(currency.equals(from)) {
                ExchangeRate exchangeRate = entry.getValue();
                if(exchangeRate.getTo().equals(to)) {
                    found = true;
                    exchangeRate.setRate(rate);
                    break;
                }
            }
        }
        if(!found) {
            throw new UnknownExchangeRateException("No Exchange Rate found between " + from.toString() + " and " + to.toString());
        }
    }

    /**
     * @return String representation of this ExchangeRateTable object.
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for(Map.Entry<Currency, ExchangeRate> entry : exchangeRateTable.entrySet()) {
            builder.append(entry.getValue().toString());
            builder.append("\n"); // newline
        }
        return builder.toString();
    }
}
