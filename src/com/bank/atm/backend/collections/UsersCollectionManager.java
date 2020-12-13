package com.bank.atm.backend.collections;

import com.bank.atm.backend.accounts.Account;
import com.bank.atm.backend.users.User;
import com.bank.atm.util.ID;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Class UsersCollectionManager
 *
 * @author: Nathan Lauer
 * @email: lauern@bu.edu
 * Creation Date: 12/13/20
 * <p>
 * Please feel free to ask me any questions. I hope you're having a nice day!
 */
public class UsersCollectionManager implements CollectionManager<User> {
    private static UsersCollectionManager instance;
    private static final String dataFileName = "data/users.ser";
    private final List<User> users;
    private FileOutputStream fos;
    private FileInputStream fis;
    private ObjectOutputStream oos;
    private ObjectInputStream ois;
    private boolean closed;

    public static UsersCollectionManager getInstance() {
        if(instance == null) {
            instance = new UsersCollectionManager();
        }
        return instance;
    }

    /**
     * Private constructor for a UsersCollectionManager
     */
    public UsersCollectionManager() {
        users = new ArrayList<>();
        closed = false;
        try {
            fos = new FileOutputStream(UsersCollectionManager.dataFileName);
            fis = new FileInputStream(UsersCollectionManager.dataFileName);
            oos = new ObjectOutputStream(fos);
            ois = new ObjectInputStream(fis);
        } catch (IOException e) {
            // Shouldn't happen
            e.printStackTrace();
            System.out.println("Failed to instantiate file or object input/output streams in UsersCollectionManager");
            System.exit(-1);
        }
        init();
    }

    /**
     * Helper function which reads in all Users from disk.
     */
    private void init() {
        boolean finished = false;
        try {
            FileInputStream fis = new FileInputStream(UsersCollectionManager.dataFileName);
            ObjectInputStream ois = new ObjectInputStream(fis);
            while(!finished) {
                Object obj = ois.readObject();
                if(obj != null) {
                    users.add((User) obj);
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
    public void save(User obj) {
        if(closed) {
            return;
        }

        try {
            oos.writeObject(obj);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Failed to save User!");
            System.exit(-1);
        }
    }

    /**
     * Finds the object identified by id
     *
     * @param id the unique ID identifying the desired object to be found
     * @return the Object identified by ID, or null if not found
     */
    @Override
    public User find(ID id) throws NoSuchElementException {
        for(User user : users) {
            if(user.hasID(id)) {
                return user;
            }
        }
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
    public List<User> findByOwnerID(ID ownerId) {
        // In this case, there should only be one User returned in the list
        List<User> output = new ArrayList<>();
        for(User user : users) {
            if(user.hasID(ownerId)) {
                output.add(user);
            }
        }
        return output;
    }

    /**
     * @return a List of every Object in this Collection.
     */
    @Override
    public List<User> all() {
        return users;
    }

    /**
     * Closes the CollectionManager as a resource
     */
    @Override
    public void close() {
        closed = true;
        try {
            fos.close();
            oos.close();
        } catch (IOException e) {
            // Shouldn't happen
            e.printStackTrace();
            System.exit(-1);
        }
    }

    /**
     * Adds the passed in Element to the Collection.
     * Internally, this will also call the save method, so the element is
     * persisted
     *
     * @param element the Element to add to the Collection.
     */
    @Override
    public void add(User element) {
        if(closed) {
            return;
        }

        // Save the user first, so that if an error occurs, we don't have
        // an erroneous user in memory
        this.save(element);
        this.users.add(element);
    }
}
