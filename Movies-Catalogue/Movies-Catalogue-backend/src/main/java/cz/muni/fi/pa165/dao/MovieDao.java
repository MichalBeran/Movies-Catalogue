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
     */
    void create(Movie entity);

    /**
     * Persits a newly created entity
     *
     * @param entity entity to create
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
     */
    Movie findById(Long id);

    /**
     * Removes specified entity from the repository
     *
     * @param id id of entity
     */
    void delete(Long id);
}
