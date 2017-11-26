package cz.muni.fi.pa165.dao;

import cz.muni.fi.pa165.entities.Movie;
import cz.muni.fi.pa165.entities.Rating;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 * @author Michal
 */
@Repository
public class RatingDaoImpl implements RatingDao {
    
    protected EntityManager manager;

    public void setEntityManager(EntityManager manager){
        this.manager = manager;
    }

    @Override
    public void create(Rating entity) {
        if (entity == null) {
            throw new IllegalArgumentException("Entity can not be null");
        }
        manager.persist(entity);
    }

    @Override
    public void update(Rating entity) {
        if (entity == null) {
            throw new IllegalArgumentException("Entity can not be null");
        }
        manager.merge(entity);
    }

    @Override
    public List<Rating> findAll() {
        Query query = manager.createQuery("SELECT a FROM Rating a", Rating.class);
        return (List<Rating>) query.getResultList();
    }

    @Override
    public Rating findById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("Id can not be null");
        }
        Rating entity = manager.find(Rating.class, id);
        if (entity == null) {
            throw new IllegalArgumentException("Entity with id " + id + " does not exist");
        }
        return entity;
    }

    @Override
    public void delete(Long id) {
        Rating entity = findById(id);
        manager.remove(entity);
    }

    @Override
    public List<Rating> findByMovieId(Long id) {
        Movie movie = manager.find(Movie.class, id);
        return (List<Rating>) movie.getRatings();
    }
}
