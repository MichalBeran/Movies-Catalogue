package cz.muni.fi.pa165.dao;

import java.util.List;
import cz.muni.fi.pa165.entities.Director;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.PersistenceContext;

import cz.muni.fi.pa165.entities.Movie;
import org.springframework.stereotype.Repository;

/**
 * @author Marek
 */
@Repository
public class DirectorDaoImpl implements DirectorDao{

    @PersistenceContext
    protected EntityManager manager;

    public void setEntityManager(EntityManager manager){
        this.manager = manager;
    }

    @Override
    public void create(Director entity) {
        if (entity == null) {
            throw new IllegalArgumentException("Entity can not be null");
        }
        manager.persist(entity);
    }

    @Override
    public void update(Director entity) {
        if (entity == null) {
            throw new IllegalArgumentException("Entity can not be null");
        }
        manager.merge(entity);
    }

    @Override
    public List<Director> findAll() {
        Query query = manager.createQuery("SELECT d FROM Director d", Director.class);
        return query.getResultList();
    }

    @Override
    public Director findById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("Id can not be null");
        }
        Director entity = manager.find(Director.class, id);
        if (entity == null) {
            throw new IllegalArgumentException("Entity with id " + id + " does not exist");
        }
        return entity;
    }

    @Override
    public void delete(Long id) {
        Director entity = findById(id);
        manager.remove(entity);
    }

    @Override
    public List<Movie> getAllMoviesForDirector(Long id) {
        Director director = findById(id);
        return director.getMovies();
    }
}
