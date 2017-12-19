package cz.muni.fi.pa165;

import com.google.common.io.Resources;
import cz.muni.fi.pa165.dto.*;
import cz.muni.fi.pa165.facade.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

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
    private GenreDto comedy;
    private GenreDto drama;

    private ActorDto jackie;
    private ActorDto gal;
    private ActorDto dwayneJohnson;
    private ActorDto karenGillan;
    private ActorDto scottSheperd;
    private ActorDto rosamundPike;
    private ActorDto salmanKhan;
    private ActorDto katrinaKaif;
    private ActorDto willFerrell;
    private ActorDto melGibson;

    private DirectorDto martin;
    private DirectorDto patty;
    private DirectorDto jakeKasdan;
    private DirectorDto scottCooper;
    private DirectorDto aliAbbasZafar;
    private DirectorDto seanAnders;

    private CreateMovieDto wonderWoman;
    private Long wonderWomanId;
    private CreateMovieDto foreigner;
    private Long foreignerId;
    private CreateMovieDto jumanji;
    private Long jumanjiId;
    private CreateMovieDto hostiles;
    private Long hostilesId;
    private CreateMovieDto tigerZinda;
    private Long tigerZindaId;
    private CreateMovieDto daddyHome;
    private Long daddyHomeId;

    private RatingDto adminRating;
    private RatingDto pepaRating;

    public void seed() {
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
        userFacade.makeAdmin(admin);

        pepa = new UserDto();
        pepa.setFirstName("Pepa");
        pepa.setLastName("Novotny");
        pepa.setNick("P3p4");
        pepa.setMail("pepa@thisapp.dev");
        passw = "pepaJePan";
        pepa.setId(userFacade.registerUser(pepa, passw));

        UserDto shorty = new UserDto();
        shorty.setFirstName("Shorty");
        shorty.setLastName("Short");
        shorty.setNick("Shorty");
        shorty.setMail("me@me.me");
        shorty.setId(userFacade.registerUser(shorty, "me"));
        userFacade.makeAdmin(shorty);
    }

        private void seedGenres() {
        adventure = new GenreDto();
        adventure.setName("Adventure");
        adventure.setDescription("Adventure films are usually exciting stories, with new experiences or exotic locales, very similar to or often paired with the action film genre.");
        adventure.setId(genreFacade.create(adventure));

        action = new GenreDto();
        action.setName("Action");
        action.setDescription("Action films usually include high energy, big-budget physical stunts and chases, possibly with rescues, battles, fights, escapes, destructive crises (floods, explosions, natural disasters, fires, etc.), non-stop motion, spectacular rhythm and pacing, and adventurous, often two-dimensional 'good-guy' heroes (or recently, heroines) battling 'bad guys' - all designed for pure audience escapism.");
        action.setId(genreFacade.create(action));
        
        comedy = new GenreDto();
        comedy.setName("Comedy");
        comedy.setDescription("In a modern sense, comedy (from the Greek: κωμῳδία, kōmōidía) refers to any discourse or work generally intended to be humorous or amusing by inducing laughter, especially in theatre, television, film, stand-up comedy, or any other medium of entertainment.");
        comedy.setId(genreFacade.create(comedy));
        
        drama = new GenreDto();
        drama.setName("Drama");
        drama.setDescription("Drama is the specific mode of fiction represented in performance. Considered as a genre of poetry in general, the dramatic mode has been contrasted with the epic and the lyrical modes ever.");
        drama.setId(genreFacade.create(drama));
    }

    private void seedActors() {
        jackie = new ActorDto();
        jackie.setFirstName("Jackie");
        jackie.setLastName("Chan");
        jackie.setDateOfBirth(LocalDate.of(1954, 4, 7));
        jackie.setId(actorFacade.create(jackie));

        gal = new ActorDto();
        gal.setFirstName("Gal");
        gal.setLastName("Gadot");
        gal.setDateOfBirth(LocalDate.of(1985, 4, 30));
        gal.setId(actorFacade.create(gal));
        
        dwayneJohnson = new ActorDto();
        dwayneJohnson.setFirstName("Dwayne");
        dwayneJohnson.setLastName("Johnson");
        dwayneJohnson.setDateOfBirth(LocalDate.of(1972, 5, 2));
        dwayneJohnson.setId(actorFacade.create(dwayneJohnson));
        
        karenGillan = new ActorDto();
        karenGillan.setFirstName("Karen");
        karenGillan.setLastName("Gillan");
        karenGillan.setDateOfBirth(LocalDate.of(1987, 1, 28));
        karenGillan.setId(actorFacade.create(karenGillan));
        
        scottSheperd = new ActorDto();
        scottSheperd.setFirstName("Scott");
        scottSheperd.setLastName("Sheperd");
        scottSheperd.setDateOfBirth(LocalDate.of(1970, 12, 2));
        scottSheperd.setId(actorFacade.create(scottSheperd));
        
        rosamundPike = new ActorDto();
        rosamundPike.setFirstName("Rosamund");
        rosamundPike.setLastName("Pike");
        rosamundPike.setDateOfBirth(LocalDate.of(1979, 51, 27));
        rosamundPike.setId(actorFacade.create(rosamundPike));
        
        salmanKhan = new ActorDto();
        salmanKhan.setFirstName("Salman");
        salmanKhan.setLastName("Khan");
        salmanKhan.setDateOfBirth(LocalDate.of(1965, 12, 27));
        salmanKhan.setId(actorFacade.create(salmanKhan));
        
        katrinaKaif = new ActorDto();
        katrinaKaif.setFirstName("Katrina");
        katrinaKaif.setLastName("Kaif");
        katrinaKaif.setDateOfBirth(LocalDate.of(1984, 7, 16));
        katrinaKaif.setId(actorFacade.create(katrinaKaif));
        
        willFerrell = new ActorDto();
        willFerrell.setFirstName("Will");
        willFerrell.setLastName("Ferrel");
        willFerrell.setDateOfBirth(LocalDate.of(1967, 7, 16));
        willFerrell.setId(actorFacade.create(willFerrell));
        
        melGibson = new ActorDto();
        melGibson.setFirstName("Mel");
        melGibson.setLastName("Gibson");
        melGibson.setDateOfBirth(LocalDate.of(1956, 1, 3));
        melGibson.setId(actorFacade.create(melGibson));
        
    }

    private void seedDirectors() {
        martin = new DirectorDto();
        martin.setFirstName("Martin");
        martin.setLastName("Campbell");
        martin.setDateOfBirth(LocalDate.of(1943, 10, 24));
        martin.setId(directorFacade.create(martin));

        patty = new DirectorDto();
        patty.setFirstName("Patty");
        patty.setLastName("Jenkins");
        patty.setDateOfBirth(LocalDate.of(1971, 6, 24));
        patty.setId(directorFacade.create(patty));
        
        jakeKasdan = new DirectorDto();
        jakeKasdan.setFirstName("Jake");
        jakeKasdan.setLastName("Kasdan");
        jakeKasdan.setDateOfBirth(LocalDate.of(1974, 10, 28));
        jakeKasdan.setId(directorFacade.create(jakeKasdan));
        
        scottCooper = new DirectorDto();
        scottCooper.setFirstName("Scott");
        scottCooper.setLastName("Cooper");
        scottCooper.setDateOfBirth(LocalDate.of(1970, 1, 1));
        scottCooper.setId(directorFacade.create(scottCooper));
        
        aliAbbasZafar = new DirectorDto();
        aliAbbasZafar.setFirstName("Ali");
        aliAbbasZafar.setLastName("Zafar");
        aliAbbasZafar.setDateOfBirth(LocalDate.of(1970, 1, 1));
        aliAbbasZafar.setId(directorFacade.create(aliAbbasZafar));
        
        seanAnders = new DirectorDto();
        seanAnders.setFirstName("Sean");
        seanAnders.setLastName("Anders");
        seanAnders.setDateOfBirth(LocalDate.of(1965, 12, 10));
        seanAnders.setId(directorFacade.create(seanAnders));
    }

    private void seedMovies() {
        foreigner = new CreateMovieDto();
        foreigner.setTitle("The foreigner");
        foreigner.setDescription("A humble businessman with a buried past seeks justice when his daughter is killed in an act of terrorism. A cat-and-mouse conflict ensues with a government official, whose past may hold clues to the killers' identities.");
        foreigner.setDateOfRelease(LocalDate.of(2017, 9, 30));
        List<GenreDto> genres = new ArrayList<>();
        genres.add(action);
        foreigner.setGenres(genres);
        List<ActorDto> actors = new ArrayList<>();
        actors.add(jackie);
        foreigner.setActors(actors);
        foreigner.setDirector(martin);
        foreigner.setImage(imageRead(Resources.getResource("foreigner-img-base64.txt")));
        foreignerId = movieFacade.createMovie(foreigner);

        wonderWoman = new CreateMovieDto();
        wonderWoman.setTitle("Wonder Woman");
        wonderWoman.setDescription("When a pilot crashes and tells of conflict in the outside world, Diana, an Amazonian warrior in training, leaves home to fight a war, discovering her full powers and true destiny.");
        wonderWoman.setDateOfRelease(LocalDate.of(2017, 6, 1));
        genres = new ArrayList<>();
        genres.add(action);
        genres.add(adventure);
        wonderWoman.setGenres(genres);
        actors = new ArrayList<>();
        actors.add(gal);
        wonderWoman.setActors(actors);
        wonderWoman.setDirector(patty);
        wonderWoman.setImage(imageRead(Resources.getResource("wonder-woman-img-base64.txt")));
        wonderWomanId = movieFacade.createMovie(wonderWoman);
        
        jumanji = new CreateMovieDto();
        jumanji.setTitle("Jumaji: Welcome to the jungle");
        jumanji.setDescription("Four teenagers discover an old video game console and are literally drawn into the game's jungle setting becoming the adult avatars they chose.");
        jumanji.setDateOfRelease(LocalDate.of(2017,12,20));
        genres = new ArrayList();
        genres.add(comedy);
        genres.add(action);
        genres.add(adventure);
        jumanji.setGenres(genres);
        actors = new ArrayList();
        actors.add(dwayneJohnson);
        actors.add(karenGillan);
        jumanji.setActors(actors);
        jumanji.setDirector(jakeKasdan);
        jumanji.setImage(imageRead(Resources.getResource("jumanji-img-base64.txt")));
        jumanjiId = movieFacade.createMovie(jumanji);
        
        hostiles = new CreateMovieDto();
        hostiles.setTitle("Hostiles");
        hostiles.setDescription("In 1892, a legendary Army captain reluctantly agrees to escort a Cheyenne chief and his family through dangerous territory."); 
        hostiles.setDateOfRelease(LocalDate.of(2018,1,4));
        genres = new ArrayList();
        genres.add(adventure);
        genres.add(drama);
        hostiles.setGenres(genres);
        actors = new ArrayList();
        actors.add(scottSheperd);
        actors.add(rosamundPike);
        hostiles.setActors(actors);
        hostiles.setDirector(scottCooper);
        hostiles.setImage(imageRead(Resources.getResource("hostiles-img-base64.txt")));
        hostilesId = movieFacade.createMovie(hostiles);
        
        tigerZinda = new CreateMovieDto();
        tigerZinda.setTitle("Tiger Zinda Hai");
        tigerZinda.setDescription("A sequel to the record-breaking blockbuster 'Ek Tha Tiger', 'Tiger Zinda Hai' continues the story of two super spies Tiger and Zoya eight years later.");
        tigerZinda.setDateOfRelease(LocalDate.of(2017,12,22));
        genres = new ArrayList();
        genres.add(adventure);
        genres.add(action);
        tigerZinda.setGenres(genres);
        actors = new ArrayList();
        actors.add(salmanKhan);
        actors.add(katrinaKaif);
        tigerZinda.setActors(actors);
        tigerZinda.setDirector(aliAbbasZafar);
        tigerZinda.setImage(imageRead(Resources.getResource("tiger-zinda-img-base64.txt")));
        tigerZindaId = movieFacade.createMovie(tigerZinda);
        
        daddyHome = new CreateMovieDto();
        daddyHome.setTitle("Daddy's home 2");
        daddyHome.setDescription("Having finally gotten used to each other's existence, Brad and Dusty must now deal with their intrusive fathers during the holidays.");
        daddyHome.setDateOfRelease(LocalDate.of(2017,12,17));
        genres = new ArrayList();
        genres.add(comedy);
        daddyHome.setGenres(genres);
        actors = new ArrayList();
        actors.add(melGibson);
        actors.add(willFerrell);
        daddyHome.setActors(actors);
        daddyHome.setDirector(seanAnders);
        daddyHome.setImage(imageRead(Resources.getResource("daddy-home-img-base64.txt")));
        daddyHomeId = movieFacade.createMovie(daddyHome);
        
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
    
    private String imageRead(URL url){
        try {
            String filePath = url.getPath();
            BufferedReader br = new BufferedReader(new FileReader(filePath));
            try {
                String result = br.readLine();
                System.out.println(result);
                return result;
                
            } catch (IOException ex) {
                Logger.getLogger(DatabaseSeeder.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(DatabaseSeeder.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
