/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.dao;

import cz.muni.fi.pa165.entities.Movie;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author Dominik
 */
public class MovieDaoImpl implements MovieDao{
    
    EntityManager em;

    @Override
    public void create(Movie entity) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void update(Movie entity) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<Movie> findAll() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Movie findById(long id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void delete(long id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
