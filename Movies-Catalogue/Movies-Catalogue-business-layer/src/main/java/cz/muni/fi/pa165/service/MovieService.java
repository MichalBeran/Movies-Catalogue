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
    
    
}
