package cz.muni.fi.pa165.entities.dao;

import cz.muni.fi.pa165.entities.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * @Author Marek
 */
@Repository
public class UserDao implements Dao<User> {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public User create(User entity) {
        manager.persist(entity);
        return entity;
    }

    @Override
    public List<User> getAll() {
        return manager.createQuery("SELECT u from User u", User.class).getResultList();
    }

    @Override
    public User getById(int id) {
        return manager.find(User.class, id);
    }

    @Override
    public void delete(User entity) {
        manager.remove(entity);
    }
}
