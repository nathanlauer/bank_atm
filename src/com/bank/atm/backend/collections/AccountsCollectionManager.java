package com.bank.atm.backend.collections;

import com.bank.atm.backend.accounts.Account;
import com.bank.atm.backend.accounts.loan_accounts.LoanAccount;
import com.bank.atm.backend.accounts.loan_accounts.LoanState;
import com.bank.atm.backend.interest.Interest;
import com.bank.atm.backend.interest.InterestEarnable;
import com.bank.atm.backend.users.User;
import com.bank.atm.util.ID;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Class AccountsCollectionManager
 *
 * @author: Nathan Lauer
 * @email: lauern@bu.edu
 * Creation Date: 12/13/20
 * <p>
 * Please feel free to ask me any questions. I hope you're having a nice day!
 */
public class AccountsCollectionManager implements CollectionManager<Account>  {
    private static AccountsCollectionManager instance;
    public static final String dataFileName = "data/accounts.ser";
    private final Set<Account> accounts;

    /**
     *
     * @return the singleton instance of this AccountsCollectionManager
     */
    public static AccountsCollectionManager getInstance() {
        if(instance == null) {
            instance = new AccountsCollectionManager();
        }
        return instance;
    }

    /**
     * Private constructor
     */
    private AccountsCollectionManager() {
        accounts = new HashSet<>();
        init();
    }

    /**
     * Private helper method which reads all Accounts from disk into memory.
     */
    private void init() {
        try {
            // Create the file if it does not exist
            File outputFile = new File(AccountsCollectionManager.dataFileName);
            outputFile.createNewFile();

            FileInputStream fis = new FileInputStream(outputFile);
            ObjectInputStream ois = new ObjectInputStream(fis);
            Set<Account> readInAccounts = (HashSet<Account>)ois.readObject();
            accounts.addAll(readInAccounts);

            // Once we have read in all Accounts, we also need to attach
            // the associated Interest objects to each of them, as necessary
            attachInterestToAccounts();
        } catch (EOFException e) {
            // This is fine, just means we read EOF (end of file)
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("Failed to read in all accounts from serialized file");
            System.exit(-1);
        }
    }

    /**
     * Helper method which attaches each Interest object to the correct Account
     */
    private void attachInterestToAccounts() {
        List<Interest> interestList = InterestCollectionsManager.getInstance().all();
        for(Interest interest : interestList) {
            ID accountId = interest.getAccountId();
            Account account = this.find(accountId);
            InterestEarnable earnable = (InterestEarnable)account;
            earnable.setInterestEarningExecutor(interest);
        }
    }

    /**
     * Saves the passed in obj to a persisted location
     *
     * @param obj the Object to be saved
     */
    @Override
    public void save(Account obj) throws IOException {
        // Not super efficient, but for the purposes of this project it's simple enough
        // to serialize the entire list just to save one object.
        try{
            // First, create the output File if it doesn't exist
            File outputFile = new File(UsersCollectionManager.dataFileName);
            outputFile.createNewFile();

            FileOutputStream fos = new FileOutputStream(AccountsCollectionManager.dataFileName);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            accounts.add(obj); // ensure the passed in object is contained in the local cache
            oos.writeObject(accounts);
            oos.close();
            fos.close();
        } catch (IOException e) {
            accounts.remove(obj);
            throw new IOException(e.getMessage());
        }
    }

    /**
     * Finds the object identified by id
     *
     * @param id the unique ID identifying the desired object to be found
     * @return the Object identified by ID
     */
    @Override
    public Account find(ID id) throws NoSuchElementException {
        for(Account account : accounts) {
            if(account.hasID(id)) {
                return account;
            }
        }
        throw new NoSuchElementException("Account with id " + id + " can't be found!");
    }

    /**
     * Given an Owner ID, finds all objects in this Collection that are associated
     * with the ownerID. It is left to the specific CollectionManager to decide
     * what "associated with" means within its context.
     *
     * @param ownerId the ID of the associated owner
     * @return List of all objects in this collection that are associated with ownerId.
     */
    @Override
    public List<Account> findByOwnerID(ID ownerId) {
        User user = UsersCollectionManager.getInstance().find(ownerId);
        Stream<Account> accountStream = accounts.stream().filter(account -> account.isAccountManager(user));
        return accountStream.collect(Collectors.toList());
    }

    /**
     * @return a List of every Object in this Collection.
     */
    @Override
    public List<Account> all() {
        return new ArrayList<>(accounts);
    }

    /**
     * Adds the passed in Element to the Collection.
     * Internally, this will also call the save method, so the element is
     * persisted
     *
     * @param element the Element to add to the Collection.
     */
    @Override
    public void add(Account element) throws IOException {
        // Save the Account first, so that if an error occurs, we don't have
        // an erroneous Account in memory
        this.save(element);
        this.accounts.add(element);
    }

    /**
     * Clears the CollectionManager's local cache
     */
    @Override
    public void clear() {
        this.accounts.clear();
    }

    /**
     *
     * @return a List of all the Loans in the collection of Accounts
     */
    public List<Account> allLoans() {
        Stream<Account> accountStream = accounts.stream().filter(account -> account instanceof LoanAccount);
        return accountStream.collect(Collectors.toList());
    }

    /**
     * List of all loans that are for a certain state
     * @param loanState the relevant loan state
     * @return List of all such loans
     */
    public List<Account> allLoansInState(LoanState loanState) {
        List<Account> loans = this.allLoans();
        List<Account> relevantLoans = new ArrayList<>();
        for(Account account : loans) {
            LoanAccount loan = (LoanAccount)account;
            if(loan.hasState(loanState)) {
                relevantLoans.add(loan);
            }
        }
        return relevantLoans;
    }

    /**
     *
     * @return List of all requested loans
     */
    public List<Account> allRequestLoans() {
        return allLoansInState(LoanState.REQUESTED);
    }

    /**
     *
     * @return List of all approved loans
     */
    public List<Account> allApprovedLoans() {
        return allLoansInState(LoanState.APPROVED);
    }

    /**
     *
     * @return List of all rejected loans
     */
    public List<Account> allRejectedLoans() {
        return allLoansInState(LoanState.REJECTED);
    }

    /**
     * Returns a list of all loans for the specified User that are in the relevant state
     * @param userId the ID of the user
     * @param loanState the loanState in question
     * @return List of all such loans
     */
    public List<Account> userLoansInState(ID userId, LoanState loanState) {
        User user = UsersCollectionManager.getInstance().find(userId);
        List<Account> relevantLoans = new ArrayList<>();
        for(Account account : allLoans()) {
            LoanAccount loan = (LoanAccount)account;
            if(loan.hasState(loanState) && loan.isAccountManager(user)) {
                relevantLoans.add(loan);
            }
        }
        return relevantLoans;
    }

    /**
     * Approves the relevant loan
     * @param loan the Loan to be approved
     */
    public void approveLoan(Account loan) {
        LoanAccount loanAccount = (LoanAccount)loan;
        loanAccount.approve();
        try {
            this.add(loanAccount);
        } catch (IOException e) {
            e.printStackTrace();
            // Shouldn't happen
        }
    }

    /**
     * Rejects the relevant loan
     * @param loan the Loan to be rejected
     */
    public void rejectLoan(Account loan) {
        LoanAccount loanAccount = (LoanAccount)loan;
        loanAccount.reject();
        try {
            this.add(loanAccount);
        } catch (IOException e) {
            e.printStackTrace();
            // Shouldn't happen
        }
    }
}
