package com.bank.atm.backend.collections;

import com.bank.atm.backend.authentication.Credentials;
import com.bank.atm.backend.users.User;
import com.bank.atm.util.ID;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Class CredentialsCollectionManager is a class that managers the collection of all Credentials.
 *
 * @author: Nathan Lauer
 * @email: lauern@bu.edu
 * Creation Date: 12/14/20
 * <p>
 * Please feel free to ask me any questions. I hope you're having a nice day!
 */
public class CredentialsCollectionManager implements CollectionManager<Credentials> {
    private static CredentialsCollectionManager instance;
    public static final String dataFileName = "data/credentials.ser";
    private final Set<Credentials> credentials;

    /**
     *
     * @return the Singleton instance of this class
     */
    public static CredentialsCollectionManager getInstance() {
        if(instance == null) {
            instance = new CredentialsCollectionManager();
        }
        return instance;
    }

    /**
     * Private constructor
     */
    private CredentialsCollectionManager() {
        credentials = new HashSet<>();
        init();
    }

    /**
     * Init helper method that reads in all credentials from disk.
     */
    private void init() {
        try {
            // Create the file if it does not exist
            File outputFile = new File(CredentialsCollectionManager.dataFileName);
            outputFile.createNewFile();

            FileInputStream fis = new FileInputStream(outputFile);
            ObjectInputStream ois = new ObjectInputStream(fis);
            Set<Credentials> readInCredentials = (HashSet<Credentials>)ois.readObject();
            credentials.addAll(readInCredentials);
        } catch (EOFException e) {
            // This is fine, just means we read EOF (end of file)
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("Failed to read in all credentials from serialized file");
            System.exit(-1);
        }
    }

    /**
     * Saves the passed in obj to a persisted location
     *
     * @param obj the Object to be saved
     */
    @Override
    public void save(Credentials obj) throws IOException {
        // Not super efficient, but for the purposes of this project it's simple enough
        // to serialize the entire list just to save one object.
        try{
            // First, create the output File if it doesn't exist
            File outputFile = new File(CredentialsCollectionManager.dataFileName);
            outputFile.createNewFile();

            FileOutputStream fos = new FileOutputStream(CredentialsCollectionManager.dataFileName);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            credentials.add(obj); // ensure the passed in object is contained in the local cache
            oos.writeObject(credentials);
            oos.close();
            fos.close();
        } catch (IOException e) {
            credentials.remove(obj);
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
    public Credentials find(ID id) throws NoSuchElementException {
        for(Credentials creds : credentials) {
            if(creds.hasID(id)) {
                return creds;
            }
        }
        throw new NoSuchElementException("Credentials with id " + id + " can't be found!");
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
    public List<Credentials> findByOwnerID(ID ownerId) {
        Stream<Credentials> credentialsStream = credentials.stream().filter(creds -> creds.hasUserId(ownerId));
        return credentialsStream.collect(Collectors.toList());
    }

    /**
     * @return a List of every Object in this Collection.
     */
    @Override
    public List<Credentials> all() {
        return new ArrayList<>(credentials);
    }

    /**
     * Adds the passed in Element to the Collection.
     * Internally, this will also call the save method, so the element is
     * persisted
     *
     * @param element the Element to add to the Collection.
     */
    @Override
    public void add(Credentials element) throws IOException {
        this.save(element);
        this.credentials.add(element);
    }

    /**
     * Clears the CollectionManager's local cache
     */
    @Override
    public void clear() {
        this.credentials.clear();
    }
}
