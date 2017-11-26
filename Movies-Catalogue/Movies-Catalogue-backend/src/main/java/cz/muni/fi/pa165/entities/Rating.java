/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Objects;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

/**
 *
 * @author Maros
 */
@Entity
public class Rating {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    private String description;
    private int overallRating;
    private int scenarioRating;
    private int actorsRating;
    
    @OneToOne
    private Movie movie;

    @ManyToOne
    private User user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getOverallRating() {
        return overallRating;
    }

    public void setOverallRating(int overallRating) {
        this.overallRating = overallRating;
    }

    public int getScenarioRating() {
        return scenarioRating;
    }

    public void setScenarioRating(int scenarioRating) {
        this.scenarioRating = scenarioRating;
    }

    public int getActorsRating() {
        return actorsRating;
    }

    public void setActorsRating(int actorsRating) {
        this.actorsRating = actorsRating;
    }
    
    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Rating {" + "id=" + id + ", overallRating=" + overallRating + ", scenarioRating=" + scenarioRating + ",actorsRating=" + actorsRating +'}';
    }

    @Override
    public int hashCode() {
        int result = 17;
        int prime = 47;
        result = prime * result + overallRating;
        result = prime * result + scenarioRating;
        result = prime * result + actorsRating;
        result = prime * result + movie.hashCode();
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!(obj instanceof Rating))
            return false;
        final Rating other = (Rating) obj;

        return Objects.equals(overallRating, other.getOverallRating())
                && Objects.equals(scenarioRating, other.getScenarioRating())
                && Objects.equals(actorsRating, other.getActorsRating())
                && Objects.equals(movie, other.getMovie());
    }
}
