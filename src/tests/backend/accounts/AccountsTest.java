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
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.io.*;

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
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
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
        account = AccountFactory.createAccount(AccountType.BASIC_CHECKING_ACCOUNT, USD.getInstance(), 1000.0, userId, accountId);
    }

    @Test
    public void attributes() {
        assertEquals(USD.getInstance(), account.getCurrency());
        Money money = new Money(1000.0);
        assertEquals(money, account.getMoney());
        assertTrue(account.hasAccess(user));
        assertTrue(account.isAccountManager(user));
        assertEquals("$1,000.00", account.displayAccountValue());
        assertTrue(account.hasID(accountId));
    }

    @Test
    @Order(1)
    public void serialize() {
        // Passes as long as the object is successfully serialized
        try {
            FileOutputStream fos = new FileOutputStream("data/account.ser");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(account);
            oos.close();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    @Order(2)
    public void deserialization() {
        Account readInAccount = null;
        try {
            FileInputStream fis = new FileInputStream("data/account.ser");
            ObjectInputStream ois = new ObjectInputStream(fis);
            readInAccount = (Account) ois.readObject();
            ois.close();
            fis.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            fail();
        }
        assertEquals(new Money(1000.0), readInAccount.getMoney());
        assertTrue(readInAccount.hasAccess(user));
        assertTrue(readInAccount.isAccountManager(user));
        assertEquals("$1,000.00", readInAccount.displayAccountValue());
        assertTrue(readInAccount.hasID(accountId));
    }
}
