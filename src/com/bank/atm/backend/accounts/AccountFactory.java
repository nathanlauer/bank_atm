package com.bank.atm.backend.accounts;

import com.bank.atm.backend.accounts.checking_accounts.BasicCheckingAccountFactory;
import com.bank.atm.backend.accounts.checking_accounts.PremiumCheckingAccountFactory;
import com.bank.atm.backend.accounts.investment_accounts.FourOhOneKAccountFactory;
import com.bank.atm.backend.accounts.loan_accounts.GenericLoanAccountFactory;
import com.bank.atm.backend.accounts.savings_accounts.HighInterestSavingsAccountFactory;
import com.bank.atm.backend.accounts.savings_accounts.LowInterestSavingsAccountFactory;
import com.bank.atm.backend.collections.AccountsCollectionManager;
import com.bank.atm.backend.collections.UsersCollectionManager;
import com.bank.atm.backend.currency.Currency;
import com.bank.atm.backend.users.User;
import com.bank.atm.util.ID;

import java.io.IOException;

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
    public static Account createAccount(AccountType accountType, Currency currency, double initialValue, ID userId) throws UnknownAccountTypeException {
        ID accountId = new ID();
        return AccountFactory.createAccount(accountType, currency, initialValue, userId, accountId);
    }

    /**
     * Creates an Account, with the passed in accountId, for the User specified by userId.
     * @param accountType the type of Account
     * @param currency the Currency for this Account
     * @param initialValue the initial value of this Account
     * @param userId the ID of the User creating this Account
     * @param accountId the ID of the Account to be created
     * @return an Account of the appropriate subtype
     * @throws UnknownAccountTypeException if requesting an unknown type of Account
     */
    public static Account createAccount(AccountType accountType, Currency currency, double initialValue, ID userId, ID accountId) throws UnknownAccountTypeException {
        User user = UsersCollectionManager.getInstance().find(userId);
        AccountFactoryCreator factory;
        switch (accountType) {
            case GENERIC_LOAN_ACCOUNT:
                throw new UnknownAccountTypeException("Loan Accounts should be created in createLoanAccount method");
            case FOUR_OH_ONE_K_ACCOUNT:
                factory = new FourOhOneKAccountFactory(currency, initialValue, user, accountId);
                break;
            case BASIC_CHECKING_ACCOUNT:
                factory = new BasicCheckingAccountFactory(currency, initialValue, user, accountId);
                break;
            case PREMIUM_CHECKING_ACCOUNT:
                factory = new PremiumCheckingAccountFactory(currency, initialValue, user, accountId);
                break;
            case LOW_INTEREST_SAVINGS_ACCOUNT:
                factory = new LowInterestSavingsAccountFactory(currency, initialValue, user, accountId);
                break;
            case HIGH_INTEREST_SAVINGS_ACCOUNT:
                factory = new HighInterestSavingsAccountFactory(currency, initialValue, user, accountId);
                break;
            default:
                throw new UnknownAccountTypeException("Unknown type of Account!");
        }
        Account account = factory.createAccount();
        try {
            AccountsCollectionManager.getInstance().add(account);
        } catch (IOException e) {
            throw new UnknownAccountTypeException(e.getMessage());
        }
        return account;
    }

    /**
     * Creates a Loan Account
     * @param currency the Currency for this Account
     * @param initialValue the initialValue of this Account - that is, the amount of money being loaned
     * @param userId the Id of the User
     * @param collateral collateral for this Loan
     * @param collateralValue value of the collateral for this Loan
     * @param creditScore User's credit score
     * @return Account representing the Loan
     */
    public static Account createLoanAccount(Currency currency, double initialValue, ID userId,
                                            String collateral, double collateralValue, int creditScore) {
        ID accountId = new ID();
        return AccountFactory.createLoanAccount(currency, initialValue, userId, accountId, collateral, collateralValue, creditScore);
    }

    /**
     * Creates a Loan Account
     * @param currency the Currency for this Account
     * @param initialValue the initialValue of this Account - that is, the amount of money being loaned
     * @param userId the Id of the User
     * @param accountId the Id of the Account
     * @param collateral collateral for this Loan
     * @param collateralValue value of the collateral for this Loan
     * @param creditScore User's credit score
     * @return Account representing the Loan
     */
    public static Account createLoanAccount(Currency currency, double initialValue, ID userId, ID accountId,
                                            String collateral, double collateralValue, int creditScore) {
        User user = UsersCollectionManager.getInstance().find(userId);
        AccountFactoryCreator factory = new GenericLoanAccountFactory(currency, initialValue, user, accountId, collateral, collateralValue, creditScore);
        Account account = factory.createAccount();
        try {
            AccountsCollectionManager.getInstance().add(account);
        } catch (IOException e) {
            throw new UnknownAccountTypeException(e.getMessage());
        }
        return account;
    }
}
