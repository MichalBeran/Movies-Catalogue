package cz.muni.fi.pa165.facade;

import cz.muni.fi.pa165.dto.CreateMovieDTO;
import cz.muni.fi.pa165.dto.GenreDto;
import cz.muni.fi.pa165.dto.MovieDto;

import java.util.List;

/**
 *
 * @author Dominik
 */
public interface MovieFacade {
    
    void createMovie(CreateMovieDTO movie);
    void deleteMovie(Long movieId);
    /**
     * 
     * @param g Genre of the film
     * @return Top movies in given category
     */
    List<MovieDto> getTopMovies(GenreDto g);
    
    
    /**
     * Method returns similiar films
     * @param m Specifies film, according to which film should be looked for
     * @return Similiar movies
     */
    List<MovieDto> getRecommendedMovies(MovieDto m);
    
    List<MovieDto> getNewestMovies(int i);
    
}
