package cz.muni.fi.pa165.facade;

import cz.muni.fi.pa165.dto.CreateMovieDto;
import cz.muni.fi.pa165.dto.GenreDto;
import cz.muni.fi.pa165.dto.MovieDto;

import java.util.List;

/**
 *
 * @author Dominik
 */
public interface MovieFacade {
    
    Long createMovie(CreateMovieDto movie);
    String deleteMovie(Long movieId);
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

    MovieDto findById(Long id);
    void delete(MovieDto dto);
    List<MovieDto> findAll();
    MovieDto update(MovieDto dto);
}
