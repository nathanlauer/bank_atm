package tests.backend.interest;

import com.bank.atm.backend.accounts.Account;
import com.bank.atm.backend.accounts.AccountFactory;
import com.bank.atm.backend.accounts.loan_accounts.LoanAccount;
import com.bank.atm.backend.accounts.loan_accounts.LoanState;
import com.bank.atm.backend.collections.AccountsCollectionManager;
import com.bank.atm.backend.collections.CollectionsUtil;
import com.bank.atm.backend.collections.InterestCollectionsManager;
import com.bank.atm.backend.collections.UsersCollectionManager;
import com.bank.atm.backend.currency.USD;
import com.bank.atm.backend.interest.InterestEarnable;
import com.bank.atm.backend.interest.InterestEarningExecutor;
import com.bank.atm.backend.users.Client;
import com.bank.atm.backend.users.User;
import com.bank.atm.util.ID;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Class Interest
 *
 * @author: Nathan Lauer
 * @email: lauern@bu.edu
 * Creation Date: 12/15/20
 * <p>
 * Please feel free to ask me any questions. I hope you're having a nice day!
 */
public class Interest {
    @BeforeAll
    public static void init() {
        // Clear the output file
        CollectionsUtil.deleteFile(UsersCollectionManager.dataFileName);
        CollectionsUtil.deleteFile(AccountsCollectionManager.dataFileName);
        CollectionsUtil.deleteFile(InterestCollectionsManager.dataFileName);
    }

    @AfterAll
    public static void clear() {
        UsersCollectionManager.getInstance().clear();
        AccountsCollectionManager.getInstance().clear();
        InterestCollectionsManager.getInstance().clear();
    }

    @Test
    public void testLoanAccountInterest() throws IOException {
        // Create a User, and have them apply for a Loan
        User user = new Client("Nathan", "Lauer");
        UsersCollectionManager.getInstance().add(user);
        ID accountId = new ID();
        AccountFactory.createLoanAccount(USD.getInstance(), 1000.0, user.getID(), accountId,"Watch", 1200.0, 760);

        // At this point, a Loan Account should exist, with apy 17%, and in REQUESTED state
        Account account = AccountsCollectionManager.getInstance().find(accountId);
        assertNotNull(account);

        LoanAccount loanAccount = (LoanAccount)account;
        assertTrue(loanAccount.hasState(LoanState.REQUESTED));


        if(account.earnsInterest()) {
            InterestEarnable earnable = (InterestEarnable)loanAccount;
            InterestEarningExecutor executor = earnable.getInterestEarningExecutor();
            assertEquals(17, executor.getApy());
        } else {
            fail();
        }
    }
}
