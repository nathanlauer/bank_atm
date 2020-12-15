package com.bank.atm.backend.collections;

import com.bank.atm.backend.accounts.Account;
import com.bank.atm.backend.interest.Interest;
import com.bank.atm.util.ID;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Class InterestCollectionsManager
 *
 * @author: Nathan Lauer
 * @email: lauern@bu.edu
 * Creation Date: 12/15/20
 * <p>
 * Please feel free to ask me any questions. I hope you're having a nice day!
 */
public class InterestCollectionsManager implements CollectionManager<Interest> {
    private static InterestCollectionsManager instance;
    public static final String dataFileName = "data/interest.ser";
    private final Set<Interest> interestEarningExecutors;

    /**
     *
     * @return the singleton instance of this AccountsCollectionManager
     */
    public static InterestCollectionsManager getInstance() {
        if(instance == null) {
            instance = new InterestCollectionsManager();
        }
        return instance;
    }

    /**
     * Private constructor
     */
    private InterestCollectionsManager() {
        interestEarningExecutors = new HashSet<>();
        init();
    }

    /**
     * Private helper method which reads all Accounts from disk into memory.
     */
    private void init() {
        try {
            // Create the file if it does not exist
            File outputFile = new File(InterestCollectionsManager.dataFileName);
            outputFile.createNewFile();

            FileInputStream fis = new FileInputStream(outputFile);
            ObjectInputStream ois = new ObjectInputStream(fis);
            Set<Interest> readInInterests = (HashSet<Interest>)ois.readObject();
            interestEarningExecutors.addAll(readInInterests);
        } catch (EOFException e) {
            // This is fine, just means we read EOF (end of file)
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("Failed to read in all accounts from serialized file");
            System.exit(-1);
        }
    }

    /**
     * Saves the passed in obj to a persisted location
     *
     * @param obj the Object to be saved
     */
    @Override
    public void save(Interest obj) throws IOException {
        // Not super efficient, but for the purposes of this project it's simple enough
        // to serialize the entire list just to save one object.
        try{
            // First, create the output File if it doesn't exist
            File outputFile = new File(InterestCollectionsManager.dataFileName);
            outputFile.createNewFile();

            FileOutputStream fos = new FileOutputStream(InterestCollectionsManager.dataFileName);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            interestEarningExecutors.add(obj); // ensure the passed in object is contained in the local cache
            oos.writeObject(interestEarningExecutors);
            oos.close();
            fos.close();
        } catch (IOException e) {
            interestEarningExecutors.remove(obj);
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
    public Interest find(ID id) throws NoSuchElementException {
        for(Interest interest : interestEarningExecutors) {
            if(interest.hasID(id)) {
                return interest;
            }
        }
        throw new NoSuchElementException("Interest with id " + id + " can't be found!");
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
    public List<Interest> findByOwnerID(ID ownerId) {
        // In this case, we treat ownerId as an AccountId
        Stream<Interest> interestStream = interestEarningExecutors.stream().filter(interest -> interest.forAccount(ownerId));
        return interestStream.collect(Collectors.toList());
    }

    /**
     * @return a List of every Object in this Collection.
     */
    @Override
    public List<Interest> all() {
        return new ArrayList<>(interestEarningExecutors);
    }

    /**
     * Adds the passed in Element to the Collection.
     * Internally, this will also call the save method, so the element is
     * persisted
     *
     * @param element the Element to add to the Collection.
     */
    @Override
    public void add(Interest element) throws IOException {
        // Save the Account first, so that if an error occurs, we don't have
        // an erroneous Account in memory
        this.save(element);
        interestEarningExecutors.add(element);
    }

    /**
     * Clears the CollectionManager's local cache
     */
    @Override
    public void clear() {
        interestEarningExecutors.clear();
    }

    /**
     * This method loads all Interest objects, and then calls the computeInterest method
     * on them. This is the method where Accounts actually accrue interest.
     * In a production system, there would be a cron job or scheduler of sorts that would
     * call this method with some frequency, perhaps once a day.
     */
    public void earnInterest() {
        List<Interest> allInterestEarningExecutors = this.all();
        allInterestEarningExecutors.forEach(Interest::computeInterest);
    }
}
