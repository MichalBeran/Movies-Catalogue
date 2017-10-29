package cz.muni.fi.pa165.dao;

import cz.muni.fi.pa165.entities.Genre;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 * @author Michal
 */
@Repository
public class GenreDaoImpl implements GenreDao {
    @PersistenceContext
    protected EntityManager manager;

    public void setEntityManager(EntityManager manager){
        this.manager = manager;
    }

    @Override
    public void create(Genre entity) {
        if (entity == null) {
            throw new IllegalArgumentException("Entity can not be null");
        }
        manager.persist(entity);
    }

    @Override
    public void update(Genre entity) {
        if (entity == null) {
            throw new IllegalArgumentException("Entity can not be null");
        }
        manager.merge(entity);
    }

    @Override
    public List<Genre> findAll() {
        Query query = manager.createQuery("SELECT a FROM Genre a", Genre.class);
        return (List<Genre>) query.getResultList();
    }

    @Override
    public Genre findById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("Id can not be null");
        }
        Genre entity = manager.find(Genre.class, id);
        if (entity == null) {
            throw new IllegalArgumentException("Entity with id " + id + " does not exist");
        }
        return entity;
    }

    @Override
    public void delete(Long id) {
        Genre entity = findById(id);
        manager.remove(entity);
    }
}
