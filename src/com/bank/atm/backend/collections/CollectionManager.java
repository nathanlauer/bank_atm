package com.bank.atm.backend.collections;

import com.bank.atm.util.ID;

import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Interface CollectionManager describes the behavior of all CollectionManagers.
 * A CollectionManager is a class that provides useful abstractions with regards
 * to reading/writing/caching objects to and from disk and in memory. For example,
 * the AccountCollectionsManager provides methods for interacting with the
 * accounts that have been persisted to the database.
 *
 * @author: Nathan Lauer
 * @email: lauern@bu.edu
 * Creation Date: 12/13/20
 * <p>
 * Please feel free to ask me any questions. I hope you're having a nice day!
 */
public interface CollectionManager<T> {
    /**
     * Saves the passed in obj to a persisted location
     * @param obj the Object to be saved
     */
    public void save(T obj) throws IOException;

    /**
     * Finds the object identified by id
     * @param id the unique ID identifying the desired object to be found
     * @return the Object identified by ID
     */
    public T find(ID id) throws NoSuchElementException;

    /**
     * Given an Owner ID, finds all objects in this Collection that are associated
     * with the ownerID. It is left to the specific CollectionManager to decide
     * what "associated with" means within its context.
     * @param ownerId the ID of the associated owner
     * @return List of all objects in this collection that are associated with ownerId.
     */
    public List<T> findByOwnerID(ID ownerId);

    /**
     *
     * @return a List of every Object in this Collection.
     */
    public List<T> all();

    /**
     * Adds the passed in Element to the Collection.
     * Internally, this will also call the save method, so the element is
     * persisted
     * @param element the Element to add to the Collection.
     */
    public void add(T element) throws IOException;

    /**
     * Clears the CollectionManager's local cache
     */
    public void clear();
}
