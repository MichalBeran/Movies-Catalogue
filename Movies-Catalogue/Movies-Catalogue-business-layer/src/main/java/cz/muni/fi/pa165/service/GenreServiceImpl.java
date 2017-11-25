package cz.muni.fi.pa165.service;

import cz.muni.fi.pa165.dao.GenreDao;
import cz.muni.fi.pa165.entities.Genre;
import cz.muni.fi.pa165.entities.Movie;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author Marek Urban
 */
public class GenreServiceImpl implements GenreService {

    @Autowired
    private GenreDao genreDao;


    @Override
    public Genre findById(Long id) {
        return genreDao.findById(id);
    }

    @Override
    public Genre findByName(String name) {
        return genreDao.findByName(name);
    }

    @Override
    public List<Genre> findAll() {
        return genreDao.findAll();
    }

    @Override
    public List<Genre> findByMovie(Movie movie) {
        return (List<Genre>) genreDao.findByMovieId(movie.getId());
    }

    @Override
    public Long create(Genre entity) {
        genreDao.create(entity);
        return entity.getId();
    }

    @Override
    public Genre update(Genre entity) {
        genreDao.update(entity);
        return entity;
    }

    @Override
    public void delete(Long id) {
        genreDao.delete(id);
    }
}
