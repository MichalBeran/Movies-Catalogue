/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.facade;

import cz.muni.fi.pa165.dto.ActorDto;

import java.util.List;

/**
 *
 * @author Maros
 */
public interface ActorFacade {

    /**
     * Create a new actor
     *
     * @param actor to be created
     * @return id of created actor
     */
    Long create(ActorDto actor);

    /**
     * Update actor
     *
     * @param actor to be updated
     * @return updated dto
     */
    ActorDto update(ActorDto actor);

    /**
     * Delete actor
     *
     * @param actor to be deleted
     */
    void delete(ActorDto actor);

    /**
     * Find actor by id
     *
     * @param id actor id
     * @return actor with given id
     */
    ActorDto findById(Long id);

    /**
     * Find all actors
     *
     * @return all actors
     */
    List<ActorDto> findAll();

}
