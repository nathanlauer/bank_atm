package com.bank.atm.backend.collections;

import com.bank.atm.backend.transactions.Transaction;
import com.bank.atm.backend.transactions.TransactionType;
import com.bank.atm.util.ID;
import com.bank.atm.util.IllegalTransactionException;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Class TransactionsCollectionManager
 *
 * @author: Nathan Lauer
 * @email: lauern@bu.edu
 * Creation Date: 12/15/20
 * <p>
 * Please feel free to ask me any questions. I hope you're having a nice day!
 */
public class TransactionsCollectionManager implements CollectionManager<Transaction> {
    private static TransactionsCollectionManager instance;
    public static final String dataFileName = "data/transactions.ser";
    private final Set<Transaction> transactions;

    /**
     *
     * @return the Singleton instance of this TransactionCollectionManager
     */
    public static TransactionsCollectionManager getInstance() {
        if(instance == null) {
            instance = new TransactionsCollectionManager();
        }
        return instance;
    }

    /**
     * Private constructor
     */
    private TransactionsCollectionManager() {
        transactions = new HashSet<>();
        init();
    }

    /**
     * Private helper method which reads all Transactions from disk into memory.
     */
    private void init() {
        try {
            // Create the file if it does not exist
            File outputFile = new File(TransactionsCollectionManager.dataFileName);
            outputFile.createNewFile();

            FileInputStream fis = new FileInputStream(outputFile);
            ObjectInputStream ois = new ObjectInputStream(fis);
            Set<Transaction> readInTransactions = (HashSet<Transaction>)ois.readObject();
            transactions.addAll(readInTransactions);
        } catch (EOFException e) {
            // This is fine, just means we read EOF (end of file)
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("Failed to read in all transactions from serialized file");
            System.exit(-1);
        }
    }

    /**
     * Saves the passed in obj to a persisted location
     *
     * @param obj the Object to be saved
     */
    @Override
    public void save(Transaction obj) throws IOException {
        // Not super efficient, but for the purposes of this project it's simple enough
        // to serialize the entire list just to save one object.
        try{
            // First, create the output File if it doesn't exist
            File outputFile = new File(TransactionsCollectionManager.dataFileName);
            outputFile.createNewFile();

            FileOutputStream fos = new FileOutputStream(TransactionsCollectionManager.dataFileName);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            transactions.add(obj); // ensure the passed in object is contained in the local cache
            oos.writeObject(transactions);
            oos.close();
            fos.close();
        } catch (IOException e) {
            transactions.remove(obj);
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
    public Transaction find(ID id) throws NoSuchElementException {
        for(Transaction transaction : transactions) {
            if(transaction.hasID(id)) {
                return transaction;
            }
        }
        throw new NoSuchElementException("Transaction with id " + id + " can't be found!");
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
    public List<Transaction> findByOwnerID(ID ownerId) {
        Stream<Transaction> transactionStream = transactions.stream().filter(transaction -> transaction.isForUser(ownerId));
        return transactionStream.collect(Collectors.toList());
    }

    /**
     * @return a List of every Object in this Collection.
     */
    @Override
    public List<Transaction> all() {
        return new ArrayList<>(transactions);
    }

    /**
     * Adds the passed in Element to the Collection.
     * Internally, this will also call the save method, so the element is
     * persisted
     *
     * @param element the Element to add to the Collection.
     */
    @Override
    public void add(Transaction element) throws IOException {
        // Save the Account first, so that if an error occurs, we don't have
        // an erroneous Account in memory
        this.save(element);
        this.transactions.add(element);
    }

    /**
     * Clears the CollectionManager's local cache
     */
    @Override
    public void clear() {
        this.transactions.clear();
    }

    /**
     * Returns a List of all the Transactions that are associated with a specific Account
     * @param accountId the ID that identifies the Account
     * @return List of Transactions for the Account
     */
    public List<Transaction> allTransactionsForAccount(ID accountId) {
        Stream<Transaction> transactionStream = transactions.stream().filter(transaction -> transaction.isForAccount(accountId));
        return transactionStream.collect(Collectors.toList());
    }

    /**
     * Returns a List of all the Transactions that are associated with a specific User
     * @param userId the ID that identifies the User
     * @return List of Transactions for the relevant User
     */
    public List<Transaction> allTransactionsForUser(ID userId) {
        return this.findByOwnerID(userId);
    }

    /**
     * Attempts to execute the Transaction. If the Transaction is executed,
     * then adds it to the cache and saves it
     * @param transaction the Transaction to be executed
     * @throws IllegalTransactionException if the Transaction cannot be executed
     */
    public void executeTransaction(Transaction transaction) throws IllegalTransactionException {
        transaction.execute();
        try {
            this.add(transaction);
        } catch (IOException e) {
            // This should not happen. If it does, we're a bit stuck, as
            // we don't really have a way of undoing a transaction.
            // For now, this is ok, as it is a fair assumption that
            // this Transaction will be successfully persisted to disk.
            throw new IllegalTransactionException(e.getMessage());
        }
    }

    /**
     * List of all Transactions that are of a certain type
     * @param transactionType the desired type of Transaction
     * @return List of all such Transactions
     */
    public List<Transaction> transactionsOfType(TransactionType transactionType) {
        List<Transaction> relevantTransactions = new ArrayList<>();
        for(Transaction transaction : all()) {
            if(transaction.hasTransactionType(transactionType)) {
                relevantTransactions.add(transaction);
            }
        }
        return relevantTransactions;
    }
}
