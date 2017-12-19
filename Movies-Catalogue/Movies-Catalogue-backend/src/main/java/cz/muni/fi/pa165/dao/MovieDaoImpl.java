/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.dao;

import cz.muni.fi.pa165.entities.Actor;
import cz.muni.fi.pa165.entities.Director;
import cz.muni.fi.pa165.entities.Genre;
import cz.muni.fi.pa165.entities.Movie;

import java.util.ArrayList;
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
        List<Genre> genres = entity.getGenres();
        List<Actor> actors = entity.getActors();
        for(Genre genre: genres){
            Genre emGenre = em.createQuery("SELECT g from Genre g where id=:id", Genre.class).setParameter("id", genre.getId()).getSingleResult();
            List<Movie> movies = emGenre.getMovies() == null ? new ArrayList<>() : emGenre.getMovies();
            if(!movies.contains(entity)){
                movies.add(entity);
                emGenre.setMovies(movies);
            }
            em.merge(emGenre);
        }
        for(Actor actor: actors){
            Actor emActor = em.createQuery("SELECT a from Actor a where id=:id", Actor.class).setParameter("id", actor.getId()).getSingleResult();
            List<Movie> movies = emActor.getMovies() == null ? new ArrayList<>() : emActor.getMovies();
            if(!movies.contains(entity)) {
                movies.add(entity);
                emActor.setMovies(movies);
            }
            em.merge(emActor);
        }

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
        List<Movie> movies = query.getResultList();
        return movies;
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
            List<Movie> movies = genre.getMovies();
            movies.remove(movie);
            genre.setMovies(movies);
            em.merge(genre);
        }
        List<Actor> actors = movie.getActors();
        for (Actor actor : actors) {
            List<Movie> movies = actor.getMovies();
            movies.remove(movie);
            actor.setMovies(movies);
            em.merge(actor);
        }
        Director director = movie.getDirector();
        List<Movie> movies = director.getMovies();
        movies.remove(movie);
        director.setMovies(movies);
        em.merge(director);
        em.remove(movie);
    }
    
}
