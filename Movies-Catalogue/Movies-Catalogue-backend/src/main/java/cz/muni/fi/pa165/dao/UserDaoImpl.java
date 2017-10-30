/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.dao;

import cz.muni.fi.pa165.entities.User;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Maros
 */
@Repository
public class UserDaoImpl implements UserDao{

    @PersistenceContext
    protected EntityManager manager;

    public void setEntityManager(EntityManager manager) {
        this.manager = manager;
    }
    
    @Override
    public void create(User entity) {
        if (entity == null) {
            throw new IllegalArgumentException("Entity can not be null");
        }
        if(entity.getId() != null || entity.getFirstName() == null || 
                entity.getLastName() == null || entity.getNick() == null ||
                entity.getPassword() == null || entity.getMail() == null ||
                entity.getRoles().isEmpty()){
            throw new IllegalArgumentException("Entity has set id or it has no "
                    + "all required attributes set.");
        }
        manager.persist(entity);
    }

    @Override
    public void update(User entity) {
        if (entity == null) {
            throw new IllegalArgumentException("Entity can not be null");
        }
        manager.merge(entity);
    }

    @Override
    public List<User> findAll() {
        Query query = manager.createQuery("SELECT d FROM User d", User.class);
        return (List<User>) query.getResultList();    }

    public User findById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("Id can not be null");
        }
        User entity = manager.find(User.class, id);
        if (entity == null) {
            throw new IllegalArgumentException("Entity with id " + id + " does not exist");
        }
        return entity;
    }

    @Override
    public void delete(Long id) {
        User entity = findById(id);
        manager.remove(entity);
    }
    
}
