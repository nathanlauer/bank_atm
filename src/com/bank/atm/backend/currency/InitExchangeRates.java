package com.bank.atm.backend.currency;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

/**
 * Class InitExchangeRates is a class that reads in Exchange rates from disk
 *
 * @author: Nathan Lauer
 * @email: lauern@bu.edu
 * Creation Date: 12/15/20
 * <p>
 * Please feel free to ask me any questions. I hope you're having a nice day!
 */
public class InitExchangeRates {
    public static final String datafileName = "data/exchangeRates.txt";

    /**
     * Reads in all exchange rates from disk, and initializes the exchange rates
     * table class
     */
    public static void run() throws IOException {
        File file = new File(datafileName);
        List<String> lines = Files.readAllLines(file.toPath());
        String regex = "[A-Z]+,[A-Z]+,[0-9.]*"; // USD,EUR,0.82
        for(String line : lines) {
            if(line.matches(regex)) {
                String[] items = line.split(",");
                Currency from = currencyFromString(items[0]);
                Currency to = currencyFromString(items[1]);
                double rate = Double.parseDouble(items[2]);
                ExchangeRate exchangeRate = new ExchangeRate(from, to, rate);
                ExchangeRateTable.getInstance().addExchangeRate(from, exchangeRate);
            }
        }
    }

    /**
     * Given a String name, returns the correct Currency
     * @param name the name of the Currency
     * @return Currency class represented by name
     */
    public static Currency currencyFromString(String name) throws UnknownExchangeRateException {
        Currency currency;
        switch (name) {
            case "USD":
                currency = USD.getInstance();
                break;
            case "EUR":
                currency = Euro.getInstance();
                break;
            case "JPY":
                currency = JPY.getInstance();
                break;
            default:
                throw new UnknownExchangeRateException("Unknown currency for " + name);
        }
        return currency;
    }
}
