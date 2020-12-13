package com.bank.atm.backend.collections;

import com.bank.atm.backend.accounts.Account;
import com.bank.atm.backend.users.User;
import com.bank.atm.util.ID;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
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
    private final List<Account> accounts;
    private static final String dataFileName = "data/accounts.ser";
    private FileOutputStream fos;
    private ObjectOutputStream oos;
    private boolean closed;


    /**
     *
     * @return the singleton instance of this AccountsCollectionManager
     */
    public AccountsCollectionManager getInstance() {
        if(instance == null) {
            instance = new AccountsCollectionManager();
        }
        return instance;
    }

    /**
     * Private constructor
     */
    private AccountsCollectionManager() {
        accounts = new ArrayList<>();
        try {
            fos = new FileOutputStream(AccountsCollectionManager.dataFileName);
            oos = new ObjectOutputStream(fos);
        } catch (IOException e) {
            // Shouldn't happen
            e.printStackTrace();
            System.out.println("Failed to instantiate file or object input/output streams in AccountsCollectionManager");
            System.exit(-1);
        }
        init();
    }

    /**
     * Private helper method which reads all Accounts from disk into memory.
     */
    private void init() {
        boolean finished = false;
        try {
            FileInputStream fis = new FileInputStream(AccountsCollectionManager.dataFileName);
            ObjectInputStream ois = new ObjectInputStream(fis);
            while(!finished) {
                Object obj = ois.readObject();
                if(obj != null) {
                    accounts.add((Account) obj);
                } else {
                    finished = true;
                }
            }
            ois.close();
            fis.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("Failed to read in all users from serialized file");
            System.exit(-1);
        }
    }

    /**
     * Saves the passed in obj to a persisted location
     *
     * @param obj the Object to be saved
     */
    @Override
    public void save(Account obj) {

    }

    /**
     * Finds the object identified by id
     *
     * @param id the unique ID identifying the desired object to be found
     * @return the Object identified by ID
     */
    @Override
    public Account find(ID id) throws NoSuchElementException {
        return null;
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
        return accounts;
    }

    /**
     * Closes the CollectionManager as a resource
     */
    @Override
    public void close() {

    }

    /**
     * Adds the passed in Element to the Collection.
     * Internally, this will also call the save method, so the element is
     * persisted
     *
     * @param element the Element to add to the Collection.
     */
    @Override
    public void add(Account element) {

    }
}
