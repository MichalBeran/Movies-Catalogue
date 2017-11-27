/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.builders;

import cz.muni.fi.pa165.entities.Actor;
import cz.muni.fi.pa165.entities.Director;
import cz.muni.fi.pa165.entities.Genre;
import cz.muni.fi.pa165.entities.Movie;
import cz.muni.fi.pa165.entities.Rating;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Dominik
 */
public class MovieBuilder {

    private Long id;

    private String title;

    private String description;

    private String image;

    private LocalDate dateOfRelease;

    private Set<Genre> genres;

    private Set<Actor> actors;
    
    private Set<Rating> ratings;

    private Director director;
    
    
    public MovieBuilder() {
        init();
    }
    
    public MovieBuilder id(Long id){
        this.id = id;
        return this;
    }
    
    public MovieBuilder title(String title){
        this.title = title;
        return this;
    }
    
    public MovieBuilder description(String description){
        this.description = description;
        return this;
    }
    
    public MovieBuilder image(String image){
        this.image = image;
        return this;
    }
    
    public MovieBuilder dateOfRelase(LocalDate date){
        this.dateOfRelease = date;
        return this;
    }
    
    public MovieBuilder genres(Genre genre){
        this.genres.add(genre);
        return this;
    }
    
    public MovieBuilder actor(Actor actors){
        this.actors.add(actors);
        return this;
    }
    
    public MovieBuilder rating(Rating rating){
        this.ratings.add(rating);
        return this;
    }
    
    public MovieBuilder director(Director director){
        this.director = director;
        return this;
    }
    
    public Movie build(){
        Movie entity = new Movie();
        entity.setId(id);
        entity.setTitle(title);
        entity.setDescription(description);
        entity.setImage(image);
        entity.setDateOfRelease(dateOfRelease);
        if(!genres.isEmpty())
        entity.setGenres(genres);
        if(!actors.isEmpty())
        entity.setActors(actors);
        if(!ratings.isEmpty());
        entity.setRatings(ratings);
        entity.setDirector(director);
        return entity;
    }
    
    private void init(){
        
        genres = new HashSet();
        actors = new HashSet();
        ratings = new HashSet();
        id = null;
        title = null;
        description = null;
        image = null;
        dateOfRelease = null;
        director = null;
    }
}
