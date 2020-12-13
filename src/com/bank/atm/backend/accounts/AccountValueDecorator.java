package com.bank.atm.backend.accounts;

/**
 * Class AccountValueDecorator is a class which acts as a Decorator for displaying the
 * value in an account. It is expected to be composed within an Account, so the nominal
 * usage is something like:
 * String output = account.displayAccountValue();
 * where the account internally uses this class to actually handle the display semantics.
 *
 * @author: Nathan Lauer
 * @email: lauern@bu.edu
 * Creation Date: 12/10/20
 * <p>
 * Please feel free to ask me any questions. I hope you're having a nice day!
 */
public class AccountValueDecorator {
    private final Account account;

    /**
     * Standard constructor
     * @param account the Account that this class decorates
     */
    public AccountValueDecorator(Account account) {
        this.account = account;
    }

    /**
     * Displays the value in the account using using the Account's currency
     * @return String representing the Account's value, for example: $13,453.23
     */
    public String displayAccountValue() {
        return account.getCurrency().displayMoney(account.getMoney());
    }
}
