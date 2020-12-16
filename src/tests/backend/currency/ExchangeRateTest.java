package tests.backend.currency;

import com.bank.atm.backend.currency.*;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Class ExchangeRateTest
 *
 * @author: Nathan Lauer
 * @email: lauern@bu.edu
 * Creation Date: 12/15/20
 * <p>
 * Please feel free to ask me any questions. I hope you're having a nice day!
 */
public class ExchangeRateTest {
    @Test
    public void testInitExchangeRates() {
        try {
            InitExchangeRates.run();
        } catch (IOException e) {
            e.printStackTrace();
            fail();
        }

        Currency usd = USD.getInstance();
        Currency eur = Euro.getInstance();
        Currency jpy = JPY.getInstance();
        ExchangeRate exchangeRate = ExchangeRateTable.getInstance().getExchangeRate(usd, eur);
        assertNotNull(exchangeRate);
        assertEquals(usd, exchangeRate.getFrom());
        assertEquals(eur, exchangeRate.getTo());
        assertEquals(0.82, exchangeRate.getRate());

        // And test a couple more
        exchangeRate = ExchangeRateTable.getInstance().getExchangeRate(jpy, usd);
        assertNotNull(exchangeRate);
        assertEquals(0.0096, exchangeRate.getRate());

        exchangeRate = ExchangeRateTable.getInstance().getExchangeRate(eur, jpy);
        assertNotNull(exchangeRate);
        assertEquals(125.93, exchangeRate.getRate());
    }
}
