/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.service;

import cz.muni.fi.pa165.dao.MovieDao;
import cz.muni.fi.pa165.entities.Genre;
import cz.muni.fi.pa165.entities.Movie;
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
        System.out.println("blabla");
        return null;
    }

    @Override
    public List<Movie> getRecommendedMovies(Movie m) {
        System.out.println("blabla");
        return null;
    }

    @Override
    public List<Movie> getTopMovies(Genre g) {
        System.out.println("blabla");   
        return null;
    }
    
}
