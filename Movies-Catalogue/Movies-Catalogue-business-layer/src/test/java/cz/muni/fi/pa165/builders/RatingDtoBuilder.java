package cz.muni.fi.pa165.builders;

import cz.muni.fi.pa165.dto.MovieDto;
import cz.muni.fi.pa165.dto.RatingDto;
import cz.muni.fi.pa165.dto.UserDto;


/**
 * @author Marek Urban
 */
public class RatingDtoBuilder {

    private RatingDto rating;

    public RatingDtoBuilder(){
        this.rating = new RatingDto();
    }

    public RatingDtoBuilder id(Long id){
        this.rating.setId(id);
        return this;
    }

    public RatingDtoBuilder movie(MovieDto movie){
        this.rating.setMovie(movie);
        return this;
    }

    public RatingDtoBuilder user(UserDto user){
        this.rating.setUser(user);
        return this;
    }

    public RatingDtoBuilder actorRtg(int score){
        this.rating.setActorsRating(score);
        return this;
    }
    public RatingDtoBuilder scenarioRtg(int score){
        this.rating.setScenarioRating(score);
        return this;
    }
    public RatingDtoBuilder overallRtg(int score){
        this.rating.setOverallRating(score);
        return this;
    }

    public RatingDto build(){
        RatingDto rating = new RatingDto();
        rating.setId(this.rating.getId());
        rating.setMovie(this.rating.getMovie());
        rating.setUser(this.rating.getUser());
        rating.setActorsRating(this.rating.getActorsRating());
        rating.setScenarioRating(this.rating.getScenarioRating());
        rating.setOverallRating(this.rating.getOverallRating());
        this.rating = new RatingDto();
        return rating;
    }
}
