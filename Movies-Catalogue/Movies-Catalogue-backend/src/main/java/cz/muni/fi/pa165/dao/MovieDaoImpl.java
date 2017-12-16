/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.dao;

import cz.muni.fi.pa165.entities.Actor;
import cz.muni.fi.pa165.entities.Genre;
import cz.muni.fi.pa165.entities.Movie;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Dominik
 */
@Repository
public class MovieDaoImpl implements MovieDao{
    
    @PersistenceContext
    private EntityManager em;

    public void setEntityManager(EntityManager em){
        this.em = em;
    }

    @Override
    public void create(Movie entity) {
        if (entity == null) {
            throw new IllegalArgumentException("Entity can not be null");
        }
        if(entity.getId() != null || entity.getTitle() == null || 
                entity.getDateOfRelease() == null || entity.getActors().isEmpty() ||
                entity.getDirector() == null){
            throw new IllegalArgumentException("Entity has not all of required "
                    + "attributes set or it has set Id already");
        }
        em.persist(entity);
    }

    @Override
    public void update(Movie entity) {
        if (entity == null) {
            throw new IllegalArgumentException("Entity can not be null");
        }
        em.merge(entity);
    }

    @Override
    public List<Movie> findAll() {
        Query query = em.createQuery("SELECT m FROM Movie m ORDER by m.dateOfRelease", Movie.class);
        return query.getResultList();
    }

    @Override
    public Movie findById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("Id can not be null");
        }
        Movie entity = em.find(Movie.class, id);
        if (entity == null) {
            throw new IllegalArgumentException("Entity with id " + id + " does not exist");
        }
        return entity;
    }

    @Override
    public void delete(Long id) {
        Movie movie = findById(id);
        // fix for ManyToMany relationship from the owning entities
        List<Genre> genres = movie.getGenres();
        for (Genre genre : genres) {
            genre.getMovies().remove(movie);
            em.merge(genre);
        }
        List<Actor> actors = movie.getActors();
        for (Actor actor : actors) {
            actor.getMovies().remove(movie);
            em.merge(actor);
        }
        em.remove(movie);
    }
    
}
