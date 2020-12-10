package com.bank.atm.backend.accounts;

import com.bank.atm.backend.accounts.Account;

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
    Account createAccount();
}
