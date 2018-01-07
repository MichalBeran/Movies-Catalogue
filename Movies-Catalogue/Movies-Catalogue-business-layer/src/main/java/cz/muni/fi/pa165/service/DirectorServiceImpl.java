/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.service;

import cz.muni.fi.pa165.dao.DirectorDao;
import cz.muni.fi.pa165.entities.Director;
import java.util.List;

import cz.muni.fi.pa165.entities.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Maros
 */
@Service
public class DirectorServiceImpl implements DirectorService {

    @Autowired
    private DirectorDao directorDao;

    @Override
    public Director findById(Long id) {
        return directorDao.findById(id);
    }

    @Override
    public List<Director> findAll() {
        return directorDao.findAll();
    }

    @Override
    public Long create(Director entity) {
        directorDao.create(entity);
        return entity.getId();
    }

    @Override
    public Director update(Director entity) {
        directorDao.update(entity);
        return entity;
    }

    @Override
    public void delete(Long id) {
        directorDao.delete(id);
    }

    @Override
    public List<Movie> getAllMoviesForDirector(Long id) {
        return directorDao.getAllMoviesForDirector(id);
    }
}
