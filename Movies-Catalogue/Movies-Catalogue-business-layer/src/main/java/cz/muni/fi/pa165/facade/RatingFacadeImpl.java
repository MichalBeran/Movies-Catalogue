package cz.muni.fi.pa165.facade;

import cz.muni.fi.pa165.dto.MovieDto;
import cz.muni.fi.pa165.dto.RatingDto;
import cz.muni.fi.pa165.entities.Movie;
import cz.muni.fi.pa165.entities.Rating;
import cz.muni.fi.pa165.mapping.BeanMappingService;
import cz.muni.fi.pa165.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author Marek Urban
 */
public class RatingFacadeImpl implements RatingFacade {

    @Autowired
    private RatingService ratingService;

    @Autowired
    private BeanMappingService mapper;

    @Override
    public RatingDto findById(Long id) {
        return mapper.mapTo(ratingService.findById(id), RatingDto.class);
    }

    @Override
    public List<RatingDto> findAll() {
        return mapper.mapTo(ratingService.findAll(), RatingDto.class);
    }

    @Override
    public List<RatingDto> findByMovie(MovieDto movie) {
        Movie movieEntity = mapper.mapTo(movie, Movie.class);
        return mapper.mapTo(ratingService.findByMovie(movieEntity), RatingDto.class);
    }

    @Override
    public Long create(RatingDto dto) {
        return ratingService.create(mapper.mapTo(dto, Rating.class));
    }

    @Override
    public RatingDto update(RatingDto dto) {
        return mapper.mapTo(ratingService.update(mapper.mapTo(dto, Rating.class)), RatingDto.class);
    }

    @Override
    public void delete(RatingDto dto) {
        ratingService.delete(dto.getId());
    }
}
