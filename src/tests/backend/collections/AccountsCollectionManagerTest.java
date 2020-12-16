package tests.backend.collections;

import com.bank.atm.backend.accounts.Account;
import com.bank.atm.backend.accounts.AccountFactory;
import com.bank.atm.backend.accounts.AccountType;
import com.bank.atm.backend.collections.AccountsCollectionManager;
import com.bank.atm.backend.collections.CollectionsUtil;
import com.bank.atm.backend.collections.UsersCollectionManager;
import com.bank.atm.backend.currency.InitExchangeRates;
import com.bank.atm.backend.currency.JPY;
import com.bank.atm.backend.currency.USD;
import com.bank.atm.backend.currency.UnknownExchangeRateException;
import com.bank.atm.backend.users.User;
import com.bank.atm.util.ID;
import jdk.nashorn.internal.ir.annotations.Ignore;
import org.junit.jupiter.api.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

/**
 * Class AccountsCollectionManagerTest
 *
 * @author: Nathan Lauer
 * @email: lauern@bu.edu
 * Creation Date: 12/13/20
 * <p>
 * Please feel free to ask me any questions. I hope you're having a nice day!
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AccountsCollectionManagerTest {
    private final static ID firstId = new ID();
    private final static ID secondId = new ID();
    private final static ID thirdId = new ID();
    private final static ID userId = new ID();
    private static User user;
    private final static List<Account> accounts = new ArrayList<>();

    @BeforeAll
    public static void init() throws IOException {
        // Initialize exchange rates
        InitExchangeRates.run();

        user = new User("Nathan", "Lauer", userId);
        UsersCollectionManager.getInstance().add(user);

        // Create a few accounts, and store them
        Account checking = AccountFactory.createAccount(AccountType.BASIC_CHECKING_ACCOUNT, USD.getInstance(), 1000.0, userId, firstId);
        Account savings = AccountFactory.createAccount(AccountType.HIGH_INTEREST_SAVINGS_ACCOUNT, USD.getInstance(), 10000.0, userId, secondId);
        Account investment = AccountFactory.createAccount(AccountType.FOUR_OH_ONE_K_ACCOUNT, USD.getInstance(), 342.0, userId, thirdId);
        accounts.add(checking);
        accounts.add(savings);
        accounts.add(investment);

        // Clear the output file
        CollectionsUtil.deleteFile(UsersCollectionManager.dataFileName);
        CollectionsUtil.deleteFile(AccountsCollectionManager.dataFileName);
    }

    @AfterAll
    public static void clear() {
        UsersCollectionManager.getInstance().clear();
        AccountsCollectionManager.getInstance().clear();
    }

    @Test
    @Order(1)
    public void testSaveAccounts() {
        // Save each of the Accounts to disk
        for(Account account : AccountsCollectionManagerTest.accounts) {
            try {
                AccountsCollectionManager.getInstance().save(account);
            } catch (IOException e) {
                e.printStackTrace();
                fail();
            }
        }
        // Test passes as long as no exceptions are thrown
    }

    @Ignore
    @Test
    @Order(2)
    public void testFindAccountAndModify() {
        // Find the first Account
        Account account = AccountsCollectionManager.getInstance().find(firstId);
        assertEquals(account, accounts.get(0));
        account.switchCurrencies(JPY.getInstance());

        try {
            AccountsCollectionManager.getInstance().save(account);
        } catch (IOException e) {
            e.printStackTrace();
            fail();
        }
    }
}
