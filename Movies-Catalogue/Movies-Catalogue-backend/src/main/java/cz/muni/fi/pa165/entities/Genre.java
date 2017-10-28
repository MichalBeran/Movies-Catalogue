/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.entities;

import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Maros
 */
@Entity
public class Genre {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private String name;
    private String description;
    @ManyToMany
    private Set<Movie> movies;

    public void setId(Long id) {
        this.id = id;
    }
    public Long getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    public String getDescription() {
        return description;
    }

    public Set<Movie> getMovies() {
        return movies;
    }
    public void addMovie(Movie movie) {
        this.movies.add(movie);
    }
    
    @Override
    public String toString() {
        return "Genre {" + "id=" + id + ", name=" + name + ", description=" + description + '}';
    }

}
