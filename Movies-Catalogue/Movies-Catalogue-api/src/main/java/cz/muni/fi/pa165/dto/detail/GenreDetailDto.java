/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.dto.detail;

import cz.muni.fi.pa165.dto.MovieDto;
import cz.muni.fi.pa165.dto.view.MovieViewDto;
import java.util.List;

/**
 *
 * @author Dominik
 */
public class GenreDetailDto {
    
    private Long id;
    private String name;
    private String description;
    private List<MovieViewDto> movies;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<MovieViewDto> getMovies() {
        return movies;
    }

    public void setMovies(List<MovieViewDto> movies) {
        this.movies = movies;
    }
}
