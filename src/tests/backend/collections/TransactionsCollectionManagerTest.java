package tests.backend.collections;

import com.bank.atm.backend.accounts.Account;
import com.bank.atm.backend.accounts.AccountFactory;
import com.bank.atm.backend.accounts.AccountType;
import com.bank.atm.backend.accounts.checking_accounts.CheckingAccount;
import com.bank.atm.backend.collections.AccountsCollectionManager;
import com.bank.atm.backend.collections.CollectionsUtil;
import com.bank.atm.backend.collections.TransactionsCollectionManager;
import com.bank.atm.backend.collections.UsersCollectionManager;
import com.bank.atm.backend.currency.USD;
import com.bank.atm.backend.transactions.Deposit;
import com.bank.atm.backend.transactions.Transaction;
import com.bank.atm.backend.transactions.Transfer;
import com.bank.atm.backend.transactions.Withdraw;
import com.bank.atm.backend.users.User;
import com.bank.atm.util.ID;
import org.junit.jupiter.api.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Class TransactionsCollectionManagerTest
 *
 * @author: Nathan Lauer
 * @email: lauern@bu.edu
 * Creation Date: 12/15/20
 * <p>
 * Please feel free to ask me any questions. I hope you're having a nice day!
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TransactionsCollectionManagerTest {
    private final static ID firstId = new ID();
    private final static ID secondId = new ID();
    private final static ID thirdId = new ID();
    private final static ID userId = new ID();
    private static User user;
    private static Account firstAccount;
    private static Account secondAccount;
    private final static List<Transaction> transactions = new ArrayList<>();

    @BeforeAll
    public static void init() throws IOException {
        // Create a new User
        user = new User("Nathan", "Lauer", userId);
        UsersCollectionManager.getInstance().add(user);

        // Create a couple Accounts
        firstAccount = AccountFactory.createAccount(AccountType.BASIC_CHECKING_ACCOUNT, USD.getInstance(), 50.0, user.getID());
        secondAccount = AccountFactory.createAccount(AccountType.LOW_INTEREST_SAVINGS_ACCOUNT, USD.getInstance(), 1000.0, user.getID());
        AccountsCollectionManager.getInstance().add(firstAccount);
        AccountsCollectionManager.getInstance().add(secondAccount);

        // Create a few transactions
        Transaction deposit = new Deposit(user.getID(), firstAccount.getID(), 25, firstId);
        Transaction withdraw = new Withdraw(user.getID(), secondAccount.getID(), 200, secondId);
        // Note that this transfer is not technically legal, because the new user Id does not belong
        // to these accounts. Nonetheless, we are not executing the transaction, so for the purposes
        // of this test to differentiate users, this is fine.
        // TODO: ensure that user is proper owner of account on transaction
        Transaction transfer = new Transfer(new ID(), secondAccount.getID(), firstAccount.getID(), 100, thirdId);
        transactions.addAll(Arrays.asList(deposit, withdraw, transfer));

        // Clear the output file
        CollectionsUtil.deleteFile(UsersCollectionManager.dataFileName);
        CollectionsUtil.deleteFile(AccountsCollectionManager.dataFileName);
        CollectionsUtil.deleteFile(TransactionsCollectionManager.dataFileName);
    }

    @AfterAll
    public static void clear() {
        UsersCollectionManager.getInstance().clear();
        AccountsCollectionManager.getInstance().clear();
        TransactionsCollectionManager.getInstance().clear();
    }

    @Test
    @Order(1)
    public void testSaveTransactions() {
        // Save each of the Transactions to disk
        for(Transaction transaction : TransactionsCollectionManagerTest.transactions) {
            try {
                TransactionsCollectionManager.getInstance().save(transaction);
            } catch (IOException e) {
                e.printStackTrace();
                fail();
            }
        }
        // Test passes as long as no exceptions are thrown
    }

    @Test
    @Order(2)
    public void testAll() {
        List<Transaction> allTransactions = TransactionsCollectionManager.getInstance().all();
        assertEquals(TransactionsCollectionManagerTest.transactions.size(), allTransactions.size());
        for(Transaction transaction : allTransactions) {
            assertTrue(TransactionsCollectionManagerTest.transactions.contains(transaction));
        }
    }

    @Test
    @Order(3)
    public void testAllForUser() {
        List<Transaction> transactionsForUser = TransactionsCollectionManager.getInstance().allTransactionsForUser(userId);
        assertEquals(TransactionsCollectionManagerTest.transactions.size() - 1, transactionsForUser.size());
        for(Transaction transaction : transactionsForUser) {
            assertTrue(TransactionsCollectionManagerTest.transactions.contains(transaction));
        }
    }

    @Test
    @Order(4)
    public void testAllForAccount() {
        List<Transaction> transactionsForAccount = TransactionsCollectionManager.getInstance().allTransactionsForAccount(firstAccount.getID());
        assertEquals(2, transactionsForAccount.size());
        for(Transaction transaction : transactionsForAccount) {
            assertTrue(TransactionsCollectionManagerTest.transactions.contains(transaction));
        }
    }
}
