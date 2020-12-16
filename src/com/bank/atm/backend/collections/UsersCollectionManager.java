package com.bank.atm.backend.collections;

import com.bank.atm.backend.users.User;
import com.bank.atm.util.ID;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
    public static final String dataFileName = "data/users.ser";
    private final Set<User> users;

    /**
     *
     * @return the Singleton instance of this class
     */
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
        users = new HashSet<>();
        init();
    }

    /**
     * Helper function which reads in all Users from disk.
     */
    private void init() {
        try {
            // Create the file if it does not exist
            File outputFile = new File(UsersCollectionManager.dataFileName);
            outputFile.createNewFile();

            FileInputStream fis = new FileInputStream(outputFile);
            ObjectInputStream ois = new ObjectInputStream(fis);
            Set<User> readInUsers = (HashSet<User>)ois.readObject();
            users.addAll(readInUsers);
        } catch (EOFException e) {
            // This is fine, just means we read EOF (end of file)
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
    public void save(User obj) throws IOException {
        // Not super efficient, but for the purposes of this project it's simple enough
        // to serialize the entire list just to save one object.
        try{
            // First, create the output File if it doesn't exist
            File outputFile = new File(UsersCollectionManager.dataFileName);
            outputFile.createNewFile();

            FileOutputStream fos = new FileOutputStream(UsersCollectionManager.dataFileName);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            users.add(obj); // ensure the passed in object is contained in the local cache
            oos.writeObject(users);
            oos.close();
            fos.close();
        } catch (IOException e) {
            users.remove(obj);
            throw new IOException(e.getMessage());
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
        throw new NoSuchElementException("User with id " + id + " can't be found!");
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

    public List<User> findByFirstName(String firstName){
        List<User> output = new ArrayList<>();
        for(User user : users) {
            if(user.getFirstName().equalsIgnoreCase(firstName)) {
                output.add(user);
            }
        }
        return output;
    }

    public List<User> findByLastName(String lastName){
        List<User> output = new ArrayList<>();
        for(User user : users) {
            if(user.getLastName().equalsIgnoreCase(lastName)) {
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
        return new ArrayList<>(users);
    }

    /**
     *
     * @return a List of all clients
     */
    public List<User> allClients() {
        Stream<User> clientStream = all().stream().filter(User::isAClient);
        return clientStream.collect(Collectors.toList());
    }

    /**
     *
     * @return a List of all Admins
     */
    public List<User> allAdmins() {
        Stream<User> adminStream = all().stream().filter(User::isAnAdmin);
        return adminStream.collect(Collectors.toList());
    }

    /**
     * Adds the passed in Element to the Collection.
     * Internally, this will also call the save method, so the element is
     * persisted
     *
     * @param element the Element to add to the Collection.
     */
    @Override
    public void add(User element) throws IOException {
        // Save the user first, so that if an error occurs, we don't have
        // an erroneous user in memory
        this.save(element);
        this.users.add(element);
    }

    /**
     * Clears the CollectionManager's local cache
     */
    @Override
    public void clear() {
        this.users.clear();
    }

    /**
     * Indicates whether or not a default admin exists
     * @return true if a default admin exists
     */
    public boolean defaultAdminExists() {
        List<User> admins = this.allAdmins();
        for(User admin : admins) {
            if(admin.getFirstName().toLowerCase().equals("admin")) {
                return true;
            }
        }
        return false;
    }
}
