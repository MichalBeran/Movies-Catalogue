/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.dto;

import java.time.LocalDate;
import java.util.Objects;
import java.util.Set;

/**
 *
 * @author Maros
 */
public class DirectorDto {

    private String firstName;

    private String lastName;

    private LocalDate dateOfBirth;

    private Set<MovieDto> movies;

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

    public Set<MovieDto> getMovies() {
        return movies;
    }

    public void setMovies(Set<MovieDto> movies) {
        this.movies = movies;
    }

    @Override
    public String toString() {
        return "Director{" + "id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", dateOfBirth=" + dateOfBirth.toString() + '}';
    }

    @Override
    public int hashCode() {
        int result = 17;
        int prime = 47;
        result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
        result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
        result = prime * result + ((dateOfBirth == null) ? 0 : dateOfBirth.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!(obj instanceof DirectorDto))
            return false;
        final DirectorDto other = (DirectorDto) obj;

        return Objects.equals(firstName, other.getFirstName())
                && Objects.equals(lastName, other.getLastName())
                && Objects.equals(dateOfBirth, other.getDateOfBirth());
    }
}
