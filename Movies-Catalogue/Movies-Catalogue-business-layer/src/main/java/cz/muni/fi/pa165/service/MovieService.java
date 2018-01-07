/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.service;

import cz.muni.fi.pa165.entities.Genre;
import cz.muni.fi.pa165.entities.Movie;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 *
 * @author Dominik
 */
public interface MovieService {
    
    void create(Movie m);
    void delete(Long id);
    void edit(Movie edited);
    Movie findById(Long id);
    List<Movie> getAllMovies();
    
    /**
     * 
     * @param number specifies number of films to be returned
     * @return Last {@param number} added films
     */
    List<Movie> getNewestMovies(int number);
    
    /**
     * 
     * @param g Genre of the film
     * @return Top movies in given category
     */
    List<Movie> getTopMovies(Genre g);
    
    
    /**
     * Method returns similiar films
     * @param m Specifies film, according to which film should be looked for
     * @return Similiar movies
     */
    List<Movie> getRecommendedMovies(Movie m);

    /**
     * Method returns movie overall rating
     * @param m movie that we want to know his overall rating
     * @return overall rating or -1 if there is no ratings.
     */
    int getMovieOverallRating(Movie m);

    /**
     * Find movies with name
     *
     * @param serachString
     * @return List of found movies
     */
    List<Movie> findAllByTitle(String serachString);


}
