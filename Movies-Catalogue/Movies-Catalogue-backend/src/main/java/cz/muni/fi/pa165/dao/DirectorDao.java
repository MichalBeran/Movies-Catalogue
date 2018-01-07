package cz.muni.fi.pa165.dao;

import cz.muni.fi.pa165.entities.Director;
import cz.muni.fi.pa165.entities.Movie;

import java.util.List;

/**
 * Created by Michal on 27.10.2017.
 */
public interface DirectorDao {
    /**
     * Persists a newly created entity
     *
     * @param entity entity to create
     */
    void create(Director entity);

    /**
     * Update saved entity
     *
     * @param entity entity to create
     */
    void update(Director entity);

    /**
     * Gets all entities from the repository
     *
     * @return List<Director> a list of entities
     */
    List<Director> findAll();

    /**
     * Finds entity with corresponding id
     *
     * @param id unique identifier of entity
     * @return Director the found entity
     */
    Director findById(Long id);

    /**
     * Removes specified entity from the repository
     *
     * @param id id of entity
     */
    void delete(Long id);

    /**
     * Get movies for director.
     *
     * @param id Director id
     * @return list of movies
     */
    List<Movie> getAllMoviesForDirector(Long id);
}
