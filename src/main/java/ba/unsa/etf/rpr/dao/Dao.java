package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.exceptions.TrainException;

import java.util.List;

/**
 * Root interface for all DAO classes
 * @author Tajra Selimovic
 * @param <T> the type of entity that this DAO operates on
 */
public interface Dao<T> {

    /**
     * get entity from database base on ID
     * @param id primary key of entity
     * @return Entity from database
     * @throws TrainException in case of problems
     */
    T getById(int id) throws TrainException;

    /**
     * Saves entity into database
     * @param item bean for saving to database
     * @return saved item with id field populated
     * @throws TrainException in case of problems
     */
    T add(T item) throws TrainException;

    /**
     * Fully updates entity in database based on id (primary) match.
     * @param item - bean to be updated. id must be populated
     * @return updated version of bean
     * @throws TrainException in case of problems
     */
    T update(T item) throws TrainException;

    /**
     * Hard delete of item from database with given id
     * @param id - primary key of entity
     * @throws TrainException in case of problems
     */
    void delete(int id) throws TrainException;

    /**
     * Lists all entities from database. WARNING: Very slow operation because it reads all records.
     * @return List of entities from database
     * @throws TrainException in case of problems
     */
    List<T> getAll() throws TrainException;
}
