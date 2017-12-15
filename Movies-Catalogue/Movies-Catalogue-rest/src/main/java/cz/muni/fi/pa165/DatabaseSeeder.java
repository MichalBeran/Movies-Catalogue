package cz.muni.fi.pa165;

import com.google.common.io.Resources;
import cz.muni.fi.pa165.dto.*;
import cz.muni.fi.pa165.facade.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
@Transactional
public class DatabaseSeeder {

    @Autowired
    private GenreFacade genreFacade;

    @Autowired
    private UserFacade userFacade;

    @Autowired
    private ActorFacade actorFacade;

    @Autowired
    private DirectorFacade directorFacade;

    @Autowired
    private RatingFacade ratingFacade;

    @Autowired
    private MovieFacade movieFacade;

    private UserDto admin;
    private UserDto pepa;

    private GenreDto adventure;
    private GenreDto action;

    private ActorDto jackie;
    private ActorDto gal;

    private DirectorDto martin;
    private DirectorDto patty;

    private CreateMovieDto wonderWoman;
    private Long wonderWomanId;
    private CreateMovieDto foreigner;
    private Long foreignerId;

    private RatingDto adminRating;
    private RatingDto pepaRating;

    public void seed(){
        seedUsers();
        seedGenres();
        seedActors();
        seedDirectors();
        seedMovies();
        seedRatings();
    }

    private void seedUsers() {
        admin = new UserDto();
        admin.setFirstName("Super");
        admin.setLastName("User");
        admin.setNick("R00t");
        admin.setMail("admin@thisapp.dev");
        String passw = "DROP TABLE users;";
        admin.setId(userFacade.registerUser(admin, passw));

        pepa = new UserDto();
        pepa.setFirstName("Pepa");
        pepa.setLastName("Novotny");
        pepa.setNick("P3p4");
        pepa.setMail("pepa@thisapp.dev");
        passw = "pepaJePan";
        pepa.setId(userFacade.registerUser(pepa, passw));
    }

    private void seedGenres(){
        adventure = new GenreDto();
        adventure.setName("Adventure");
        adventure.setDescription("Adventure films are usually exciting stories, with new experiences or exotic locales, very similar to or often paired with the action film genre.");
        adventure.setDescription("Adventure films are usually exciting stories");
        adventure.setId(genreFacade.create(adventure));

        action = new GenreDto();
        action.setName("Action");
        action.setDescription("Action films usually include high energy, big-budget physical stunts and chases, possibly with rescues, battles, fights, escapes, destructive crises (floods, explosions, natural disasters, fires, etc.), non-stop motion, spectacular rhythm and pacing, and adventurous, often two-dimensional 'good-guy' heroes (or recently, heroines) battling 'bad guys' - all designed for pure audience escapism.");
        action.setDescription("Action films usually include high energy, big-budget physical stunts and chases.");
        action.setId(genreFacade.create(action));
    }

    private void seedActors() {
        jackie = new ActorDto();
        jackie.setFirstName("Jackie");
        jackie.setLastName("Chan");
        jackie.setDateOfBirth(LocalDate.of(1954,4,7));
        jackie.setId(actorFacade.create(jackie));

        gal = new ActorDto();
        gal.setFirstName("Gal");
        gal.setLastName("Gadot");
        gal.setDateOfBirth(LocalDate.of(1985,4,30));
        gal.setId(actorFacade.create(gal));
    }

    private void seedDirectors(){
        martin = new DirectorDto();
        martin.setFirstName("Martin");
        martin.setLastName("Campbell");
        martin.setDateOfBirth(LocalDate.of(1943,10,24));
        martin.setId(directorFacade.create(martin));

        patty = new DirectorDto();
        patty.setFirstName("Patty");
        patty.setLastName("Jenkins");
        patty.setDateOfBirth(LocalDate.of(1971,6,24));
        patty.setId(directorFacade.create(patty));
    }

    private void seedMovies() {

        foreigner = new CreateMovieDto();
        foreigner.setTitle("The foreigner");
        foreigner.setDescription("A humble businessman with a buried past seeks justice when his daughter is killed in an act of terrorism. A cat-and-mouse conflict ensues with a government official, whose past may hold clues to the killers' identities.");
        foreigner.setDescription("A humble businessman with a buried past seeks justice when his daughter is killed in an act of terrorism.");
        foreigner.setDescription("dscr");
        foreigner.setDateOfRelease(LocalDate.of(2017,9,30));
        List<GenreDto> genres = new ArrayList<>();
        genres.add(action);
        foreigner.setGenres(genres);
        List<ActorDto> actors = new ArrayList<>();
        actors.add(jackie);
        foreigner.setActors(actors);
        foreigner.setDirector(martin);
        foreigner.setImage(Resources.getResource("foreigner-img-base64.txt").toString());
        foreigner.setImage("ggg");
        foreignerId = movieFacade.createMovie(foreigner);

        wonderWoman = new CreateMovieDto();
        wonderWoman.setTitle("Wonder Woman");
        wonderWoman.setDescription("When a pilot crashes and tells of conflict in the outside world, Diana, an Amazonian warrior in training, leaves home to fight a war, discovering her full powers and true destiny.");
        wonderWoman.setDescription("When a pilot crashes and tells of conflict in the outside world.");
        wonderWoman.setDescription("dscr");
        wonderWoman.setDateOfRelease(LocalDate.of(2017,6,1));
        genres = new ArrayList<>();
        genres.add(action);
        genres.add(adventure);
        wonderWoman.setGenres(genres);
        actors = new ArrayList<>();
        actors.add(gal);
        wonderWoman.setActors(actors);
        wonderWoman.setDirector(patty);
        wonderWoman.setImage(Resources.getResource("wonder-woman-img-base64.txt").toString());
        wonderWoman.setImage("gg");
        wonderWomanId = movieFacade.createMovie(wonderWoman);
    }

    private void seedRatings() {
        adminRating = new RatingDto();
        adminRating.setOverallRating(100);
        adminRating.setActorsRating(100);
        adminRating.setScenarioRating(100);
        adminRating.setMovie(movieFacade.findById(foreignerId));
        adminRating.setUser(admin);
        adminRating.setDescription("Perfect!");
        adminRating.setId(ratingFacade.create(adminRating));

        pepaRating = new RatingDto();
        pepaRating.setOverallRating(0);
        pepaRating.setActorsRating(0);
        pepaRating.setScenarioRating(0);
        pepaRating.setMovie(movieFacade.findById(wonderWomanId));
        pepaRating.setUser(admin);
        pepaRating.setDescription("Didn't like it at all");
        pepaRating.setId(ratingFacade.create(pepaRating));
    }

}
