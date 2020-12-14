package com.bank.atm.backend.accounts.checking_accounts;

import com.bank.atm.backend.currency.Currency;
import com.bank.atm.backend.currency.Money;
import com.bank.atm.util.ID;

import java.util.List;

/**
 * Class BasicCheckingAccount
 *
 * @author: Nathan Lauer
 * @email: lauern@bu.edu
 * Creation Date: 12/10/20
 * <p>
 * Please feel free to ask me any questions. I hope you're having a nice day!
 */
public class BasicCheckingAccount extends CheckingAccount {
    /**
     * Standard constructor for a BasicCheckingAccount
     * @param currency the Currency for this Account
     * @param money the initial Monetary value for this Account
     * @param managers list of Users that are managers for this Account.
     */
    public BasicCheckingAccount(Currency currency, Money money, List<ID> managers, ID accountId) {
        super(currency, money, managers, accountId);
    }
}
