package tests.backend.accounts;

import com.bank.atm.backend.accounts.Account;
import com.bank.atm.backend.accounts.AccountFactory;
import com.bank.atm.backend.accounts.AccountType;
import com.bank.atm.backend.accounts.UnknownAccountTypeException;
import com.bank.atm.backend.collections.UsersCollectionManager;
import com.bank.atm.backend.currency.Money;
import com.bank.atm.backend.currency.USD;
import com.bank.atm.backend.users.User;
import com.bank.atm.util.ID;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Class AccountsTest
 *
 * @author: Nathan Lauer
 * @email: lauern@bu.edu
 * Creation Date: 12/13/20
 * <p>
 * Please feel free to ask me any questions. I hope you're having a nice day!
 */
public class AccountsTest {
    private final static ID accountId = new ID();
    private final static ID userId = new ID();
    private final Account account;
    private final User user;

    public AccountsTest() {
        user = new User("Nathan", "Lauer", userId);
        try {
            UsersCollectionManager.getInstance().add(user);
        } catch (IOException e) {
            e.printStackTrace();
            // Shouldn't happen
            fail();
        }
        account = AccountFactory.createAccount(AccountType.BASIC_CHECKING_ACCOUNT, USD.getInstance(), 1000.0, user.getID());
    }

    @Test
    public void attributes() {
        assertEquals(USD.getInstance(), account.getCurrency());
        Money money = new Money(1000.0);
        assertEquals(money, account.getMoney());
        assertTrue(account.hasAccess(user));
        assertTrue(account.isAccountManager(user));
        assertEquals("$1000.00", account.displayAccountValue());
        assertTrue(account.hasID(accountId));
    }
}
