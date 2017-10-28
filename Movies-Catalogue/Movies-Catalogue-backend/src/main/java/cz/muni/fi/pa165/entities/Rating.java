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
import javax.persistence.ManyToOne;

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
    
    @ManyToOne
    private Movie movie;

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
    
    @Override
    public String toString() {
        return "Rating {" + "id=" + id + ", overallRating=" + overallRating + ", scenarioRating=" + scenarioRating + ",actorsRating" + actorsRating +'}';
    }
    
}
