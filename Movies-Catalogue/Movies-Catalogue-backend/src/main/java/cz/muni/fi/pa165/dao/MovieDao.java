/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.dao;

import cz.muni.fi.pa165.entities.Movie;
import java.util.List;

/**
 *
 * @author Maros
 */
public interface MovieDao {
    
    /**
     * Persits a newly created entity
     *
     * @param entity entity to create
     * @throws IllegalArgumentException, if some of required attributes is not set
     * or @param entity is null
     */
    void create(Movie entity);

    /**
     * Update entity attributes due to Id of parameter entity
     *
     * @param entity entity to be updated
     * @throws IllegalArgumentException, if entity with the same Id does not exists
     */
    void update(Movie entity);

    /**
     * Gets all entities from the repository
     *
     * @return List<Movie> a list of entities
     */
    List<Movie> findAll();

    /**
     * Finds entity with corresponding id
     *
     * @param id unique identifier of entity
     * @return Movie the found entity
     * @throws IllegalArgumentException, if entity was not found in database
     */
    Movie findById(Long id);

    /**
     * Removes specified entity from the repository
     *
     * @param id id of entity
     * @throws IllegalArgumentException, if entity was not found in database
     */
    void delete(Long id);
}
