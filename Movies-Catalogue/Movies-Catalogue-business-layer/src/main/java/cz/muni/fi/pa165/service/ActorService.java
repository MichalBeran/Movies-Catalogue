/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.service;

import cz.muni.fi.pa165.entities.Actor;
import java.util.List;

/**
 * Service layer for {@link Actor} entity.
 *
 * @author Maros
 */
public interface ActorService {

    /**
     * Finds {@link Actor} by ID
     *
     * @param id desired identifier
     * @return Actor entity
     */
    Actor findById(Long id);

    /**
     * Finds all {@link Actor} entities
     *
     * @return list of Actors
     */
    List<Actor> findAll();

    /**
     * Creates new {@link Actor} entity
     *
     * @param entity created entity
     * @return id of created entity
     */
    Long create(Actor entity);

    /**
     * Updates {@link Actor} entity
     *
     * @param entity created entity
     * @return id of created entity
     */
    Actor update(Actor entity);

    /**
     * Deletes {@link Actor} entity
     *
     * @param id identifier
     */
    void delete(Long id);
}
