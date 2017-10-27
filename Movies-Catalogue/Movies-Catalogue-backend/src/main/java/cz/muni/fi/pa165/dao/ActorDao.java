package cz.muni.fi.pa165.dao;

import cz.muni.fi.pa165.entities.Actor;

import java.util.List;

/**
 * Created by Michal on 27.10.2017.
 */
public interface ActorDao {
    /**
     * Persists a newly created entity
     *
     * @param entity entity to create
     */
    void create(Actor entity);

    /**
     * Update saved entity
     *
     * @param entity entity to create
     */
    void update(Actor entity);

    /**
     * Gets all entities from the repository
     *
     * @return List<Actor> a list of entities
     */
    List<Actor> findAll();

    /**
     * Finds entity with corresponding id
     *
     * @param id unique identifier of entity
     * @return Actor the found entity
     */
    Actor findById(long id);

    /**
     * Removes specified entity from the repository
     *
     * @param id id of entity
     */
    void delete(long id);
}
