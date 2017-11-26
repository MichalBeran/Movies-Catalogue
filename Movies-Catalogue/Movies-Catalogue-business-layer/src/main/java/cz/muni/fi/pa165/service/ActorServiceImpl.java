/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.service;

import cz.muni.fi.pa165.dao.ActorDao;
import cz.muni.fi.pa165.entities.Actor;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Maros
 */
public class ActorServiceImpl implements ActorService {

    @Autowired
    private ActorDao actorDao;

    @Override
    public Actor findById(Long id) {
        return actorDao.findById(id);
    }

    @Override
    public List<Actor> findAll() {
        return actorDao.findAll();
    }

    @Override
    public Long create(Actor entity) {
        actorDao.create(entity);
        return entity.getId();
    }

    @Override
    public Actor update(Actor entity) {
        actorDao.update(entity);
        return entity;
    }

    @Override
    public void delete(Long id) {
        actorDao.delete(id);
    }

}
