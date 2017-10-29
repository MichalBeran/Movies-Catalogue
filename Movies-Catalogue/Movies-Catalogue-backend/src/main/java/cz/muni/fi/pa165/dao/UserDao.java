package cz.muni.fi.pa165.dao;

import cz.muni.fi.pa165.entities.User;

import java.util.List;

/**
 * @author Marek
 *
 */
public interface UserDao {

    /**
     * Persits a newly created entity
     *
     * @param entity entity to create
     */
    void create(User entity);

    /**
     * Persits a newly created entity
     *
     * @param entity entity to create
     */
    void update(User entity);

    /**
     * Gets all entities from the repository
     *
     * @return List<User> a list of entities
     */
    List<User> findAll();

    /**
     * Finds entity with corresponding id
     *
     * @param id unique identifier of entity
     * @return User the found entity
     */
    User findById(Long id);

    /**
     * Removes specified entity from the repository
     *
     * @param id id of entity
     */
    void delete(Long id);
}