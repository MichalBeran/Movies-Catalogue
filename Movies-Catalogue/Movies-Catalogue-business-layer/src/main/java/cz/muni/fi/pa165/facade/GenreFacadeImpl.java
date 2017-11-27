package cz.muni.fi.pa165.facade;

import cz.muni.fi.pa165.dto.GenreDto;
import cz.muni.fi.pa165.dto.MovieDto;
import cz.muni.fi.pa165.entities.Genre;
import cz.muni.fi.pa165.entities.Movie;
import cz.muni.fi.pa165.mapping.BeanMappingService;
import cz.muni.fi.pa165.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Marek Urban
 */
@Service
public class GenreFacadeImpl implements GenreFacade {

    @Autowired
    private GenreService genreService;

    @Autowired
    private BeanMappingService mapper;

    @Override
    public GenreDto findById(Long id) {
        return mapper.mapTo(genreService.findById(id), GenreDto.class);
    }

    @Override
    public GenreDto findByName(String name) {
        return mapper.mapTo(genreService.findByName(name), GenreDto.class);
    }

    @Override
    public List<GenreDto> findAll() {
        return mapper.mapTo(genreService.findAll(), GenreDto.class);
    }

    @Override
    public List<GenreDto> findByMovie(MovieDto movie) {
        Movie movieEntity = mapper.mapTo(movie, Movie.class);
        return mapper.mapTo(genreService.findByMovie(movieEntity), GenreDto.class);
    }

    @Override
    public Long create(GenreDto dto) {
        return genreService.create(mapper.mapTo(dto, Genre.class));
    }

    @Override
    public GenreDto update(GenreDto dto) {
        return mapper.mapTo(genreService.update(mapper.mapTo(dto, Genre.class)), GenreDto.class);
    }

    @Override
    public void delete(GenreDto dto) {
        genreService.delete(dto.getId());
    }
}
