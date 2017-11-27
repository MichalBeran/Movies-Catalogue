/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.facade;

import cz.muni.fi.pa165.dto.ActorDto;
import cz.muni.fi.pa165.entities.Actor;

import cz.muni.fi.pa165.mapping.BeanMappingService;
import cz.muni.fi.pa165.service.ActorService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Maros
 */
@Service
@Transactional
public class ActorFacadeImpl implements ActorFacade {

    @Autowired
    private ActorService actorService;

    @Autowired
    private BeanMappingService mapper;

    @Override
    public Long create(ActorDto actor) {
        return actorService.create(mapper.mapTo(actor, Actor.class));
    }

    @Override
    public ActorDto update(ActorDto actor) {
        return mapper.mapTo(actorService.update(mapper.mapTo(actor, Actor.class)), ActorDto.class);
    }

    @Override
    public void delete(ActorDto actor) {
        actorService.delete(actor.getId());
    }

    @Override
    public ActorDto findById(Long id) {
        return mapper.mapTo(actorService.findById(id), ActorDto.class);
    }

    @Override
    public List<ActorDto> findAll() {
        return mapper.mapTo(actorService.findAll(), ActorDto.class);
    }

}
