package com.bank.atm.backend.accounts;

/**
 * Class AccountFactoryCreator is an interface that describes all Account Factories.
 *
 * @author: Nathan Lauer
 * @email: lauern@bu.edu
 * Creation Date: 12/10/20
 * <p>
 * Please feel free to ask me any questions. I hope you're having a nice day!
 */
public interface AccountFactoryCreator {
    /**
     * Creates the appropriate type of Account
     * @return Account object of appropriate subtype.
     */
    Account createAccount();
}
