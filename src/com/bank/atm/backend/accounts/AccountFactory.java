package com.bank.atm.backend.accounts;

import com.bank.atm.backend.accounts.checking_accounts.BasicCheckingAccountFactory;
import com.bank.atm.backend.accounts.checking_accounts.PremiumCheckingAccountFactory;
import com.bank.atm.backend.accounts.investment_accounts.FourOhOneKAccountFactory;
import com.bank.atm.backend.accounts.loan_accounts.GenericLoanAccountFactory;
import com.bank.atm.backend.accounts.savings_accounts.HighInterestSavingsAccountFactory;
import com.bank.atm.backend.accounts.savings_accounts.LowInterestSavingsAccountFactory;
import com.bank.atm.backend.currency.Currency;
import com.bank.atm.backend.users.User;

/**
 * Class AccountFactory is a Factory that constructs an appropriate type of Account.
 *
 * @author: Nathan Lauer
 * @email: lauern@bu.edu
 * Creation Date: 12/10/20
 * <p>
 * Please feel free to ask me any questions. I hope you're having a nice day!
 */
public class AccountFactory {
    /**
     * Creates an Account, calling the appropriate Factory depending on
     * the type of Account indicated
     * @param accountType the Type of Account to create
     * @return an Account of the appropriate subtype.
     */
    public static Account createAccount(AccountType accountType, Currency currency, double initialValue, User user) throws UnknownAccountTypeException {
        Account account;
        switch (accountType) {
            case GENERIC_LOAN_ACCOUNT:
                account = new GenericLoanAccountFactory(currency, initialValue, user).createAccount();
                break;
            case FOUR_OH_ONE_K_ACCOUNT:
                account = new FourOhOneKAccountFactory(currency, initialValue, user).createAccount();
                break;
            case BASIC_CHECKING_ACCOUNT:
                account = new BasicCheckingAccountFactory(currency, initialValue, user).createAccount();
                break;
            case PREMIUM_CHECKING_ACCOUNT:
                account = new PremiumCheckingAccountFactory(currency, initialValue, user).createAccount();
                break;
            case LOW_INTEREST_SAVINGS_ACCOUNT:
                account = new LowInterestSavingsAccountFactory(currency, initialValue, user).createAccount();
                break;
            case HIGH_INTEREST_SAVINGS_ACCOUNT:
                account = new HighInterestSavingsAccountFactory(currency, initialValue, user).createAccount();
                break;
            default:
                throw new UnknownAccountTypeException("Unknown type of Account!");
        }
        return account;
    }
}
