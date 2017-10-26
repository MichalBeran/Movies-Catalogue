package cz.muni.fi.pa165.entities.dao;

import java.util.List;

/**
 * @author Marek
 *
 * @param <T> entity class implementing this interface
 */
public interface Dao<T> {

    /**
     * Persits a newly created entity
     *
     * @param entity entity to create
     * @return T
     */
    T create(T entity);

    /**
     * Gets all entities from the repository
     *
     * @return List<T> a list of entities
     */
    List<T> getAll();

    /**
     * Finds entity with corresponding id
     *
     * @param id unique identifier of entity
     * @return T the found entity
     */
    T getById(int id);

    /**
     * Removes specified entity from the repository
     *
     * @param entity entity to delete
     */
    void delete(T entity);
}