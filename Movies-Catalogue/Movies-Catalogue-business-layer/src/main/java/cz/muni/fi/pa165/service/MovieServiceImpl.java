/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.service;

import cz.muni.fi.pa165.dao.MovieDao;
import cz.muni.fi.pa165.entities.Genre;
import cz.muni.fi.pa165.entities.Movie;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Dominik
 */
@Service
public class MovieServiceImpl implements MovieService{

    @Autowired
    private MovieDao movieDao;
    
    @Autowired
    private TimeService timeService;
    
    @Override
    public void create(Movie m) {
        movieDao.create(m);
    }

    @Override
    public void delete(Long id) {
        movieDao.delete(id);
    }

    @Override
    public void edit(Movie edited) {
        movieDao.update(edited);
    }

    @Override
    public Movie findById(Long id) {
        return movieDao.findById(id);
    }

    @Override
    public List<Movie> getAllMovies() {
        return movieDao.findAll();
    }

    @Override
    public List<Movie> getNewestMovies(int number) {
        List<Movie> movies = movieDao.findAll();
        int numberOfFilms = movies.size();
        if(numberOfFilms < number){
            throw new IllegalStateException("There are not enough films.");
        }
        return movies.subList(numberOfFilms - number, numberOfFilms - 1);
    }
    
}
