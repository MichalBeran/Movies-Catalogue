/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.dto.detail;

import cz.muni.fi.pa165.dto.view.MovieViewDto;
import cz.muni.fi.pa165.dto.view.UserViewDto;

/**
 *
 * @author Dominik
 */
public class RatingDetailDto {
    private Long id;
    private String description;
    private int overallRating;
    private int scenarioRating;
    private int actorsRating;
    private MovieViewDto movie;
    private UserViewDto user;

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

    public MovieViewDto getMovie() {
        return movie;
    }

    public void setMovie(MovieViewDto movie) {
        this.movie = movie;
    }

    public UserViewDto getUser() {
        return user;
    }

    public void setUser(UserViewDto user) {
        this.user = user;
    }
    
    
}
