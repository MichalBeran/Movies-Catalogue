/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.dto;

import cz.muni.fi.pa165.entities.Director;
import java.time.LocalDate;
import java.util.Set;

/**
 *
 * @author Maros
 */
public class DirectorDto {

    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Set<Director> getMovies() {
        return movies;
    }

    public void setMovies(Set<Director> movies) {
        this.movies = movies;
    }

    private String firstName;

    private String lastName;

    private LocalDate dateOfBirth;

    private Set<Director> movies;
}
