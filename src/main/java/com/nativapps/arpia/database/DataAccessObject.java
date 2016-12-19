package com.nativapps.arpia.database;

import java.io.Serializable;
import java.util.List;

/**
 * The <strong>DataAccessObject</strong> interface provides methods to give you
 * a communication between the database and applicaction.
 *
 * @param <T> Entity class
 * @param <I> Data type of the primary key or id of entity
 *
 * @author Gustavo Pacheco <ryctabo@gmail.com>
 * @version 1.0
 */
public interface DataAccessObject<T extends Serializable, I> {

    /**
     * This method searches all the entities related to T entity that implements
     * the DAO(Data Access Object).
     *
     * @return entities list
     */
    List<T> findAll();

    /**
     * This method searches the entity related with given ID.
     *
     * @param id primary key or id of entity
     * @return entity
     */
    T find(I id);

    /**
     * This method is responsible for storing the entities in the database and
     * match the Persistence Context.
     *
     * @param entity entity to save
     * @return saved entity
     */
    T save(T entity);

    /**
     * this method is responsible for delete the entities related with given ID.
     *
     * @param id primary key or id of entity
     */
    void delete(I id);

}
