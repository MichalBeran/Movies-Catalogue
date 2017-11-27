package cz.muni.fi.pa165.dao;

import java.util.List;
import cz.muni.fi.pa165.entities.Actor;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

/**
 * @author Marek
 */
@Repository
public class ActorDaoImpl implements ActorDao{

    @PersistenceContext
    protected EntityManager manager;

    public void setEntityManager(EntityManager manager){
        this.manager = manager;
    }

    @Override
    public void create(Actor entity) {
        if (entity == null) {
            throw new IllegalArgumentException("Entity can not be null");
        }
        manager.persist(entity);
    }

    @Override
    public void update(Actor entity) {
        if (entity == null) {
            throw new IllegalArgumentException("Entity can not be null");
        }
        manager.merge(entity);
    }

    @Override
    public List<Actor> findAll() {
        Query query = manager.createQuery("SELECT a FROM Actor a", Actor.class);
        return (List<Actor>) query.getResultList();
    }

    @Override
    public Actor findById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("Id can not be null");
        }
        Actor entity = manager.find(Actor.class, id);
        if (entity == null) {
            throw new IllegalArgumentException("Entity with id " + id + " does not exist");
        }
        return entity;
    }

    @Override
    public void delete(Long id) {
        Actor entity = findById(id);
        manager.remove(entity);
    }
}
