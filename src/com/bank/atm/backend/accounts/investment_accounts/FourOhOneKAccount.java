package com.bank.atm.backend.accounts.investment_accounts;

import com.bank.atm.backend.currency.Currency;
import com.bank.atm.backend.currency.Money;
import com.bank.atm.util.ID;

import java.util.List;

/**
 * Class FourOhOneKAccount
 *
 * @author: Nathan Lauer
 * @email: lauern@bu.edu
 * Creation Date: 12/10/20
 * <p>
 * Please feel free to ask me any questions. I hope you're having a nice day!
 */
public class FourOhOneKAccount extends InvestmentAccount {
    /**
     * Standard constructor for a FourOhOneKAccount
     * @param currency the Currency for this Account
     * @param money the initial Monetary value for this Account
     * @param managers list of Users that are managers for this Account.
     */
    public FourOhOneKAccount(Currency currency, Money money, List<ID> managers, ID accountId) {
        super(currency, money, managers, accountId);
    }

    /**
     * Indicates whether or not this Account earns interest
     *
     * @return true if this Account earns interest, false otherwise
     */
    @Override
    public boolean earnsInterest() {
        return false;
    }
}
