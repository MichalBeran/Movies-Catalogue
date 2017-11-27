/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.service;

import cz.muni.fi.pa165.dao.MovieDao;
import cz.muni.fi.pa165.entities.Genre;
import cz.muni.fi.pa165.entities.Movie;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
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
    private GenreService genreService;
    
    @Autowired
    private RatingService ratingService;
    
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
        return movies.subList(numberOfFilms - number, numberOfFilms);
    }
    
    @Override
    public List<Movie> getRecommendedMovies(Movie m) {
        Set<Genre> genresOfFilm = m.getGenres();
        Iterator itr = genresOfFilm.iterator();
        if(itr.hasNext()){
            Genre genreOfFilm = genreService.findById(((Genre) itr.next()).getId());
            Set<Movie> recommendedMovies = genreOfFilm.getMovies();
            return new ArrayList<>(recommendedMovies);
        }else{
            return new ArrayList<>();
        }
    }

    @Override
    public List<Movie> getTopMovies(Genre g) {
        Iterator itr = g.getMovies().iterator();
        List<Movie> topMovies = new ArrayList<>();
        while(itr.hasNext()){
            Movie movie = (Movie) itr.next();
            //Rating overallRating = ratingService.getOverallRating();
            //if(overallRating.getOverallRating() > 4){
            //    topMovies.add(movie);
            //}
        }
        return topMovies;
    }
    
}
