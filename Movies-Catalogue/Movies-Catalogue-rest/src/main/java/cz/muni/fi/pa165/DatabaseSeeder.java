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
    private UserDto peter;
    private UserDto hent;
    private UserDto lojzik;

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
    private ActorDto markHamill;
    private ActorDto harrisonFord;

    private DirectorDto martin;
    private DirectorDto patty;
    private DirectorDto jakeKasdan;
    private DirectorDto scottCooper;
    private DirectorDto aliAbbasZafar;
    private DirectorDto seanAnders;
    private DirectorDto georgeLucas;
    private DirectorDto stevenSpiel;
    private DirectorDto sethGordon;

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
    private CreateMovieDto starWars4;
    private Long starWars4Id;
    private CreateMovieDto starWars5;
    private Long starWars5Id;
    private CreateMovieDto indiana;
    private Long indianaId;
    private CreateMovieDto baywatch;
    private Long baywatchId;
    private CreateMovieDto StarWars6;
    private Long starWars6Id;


    private RatingDto adminRating;
    private RatingDto pepaRating;
    private RatingDto peterRating;
    private RatingDto hentRating;
    private RatingDto hentRatingBaywatch;
    private RatingDto peterRatingDaddy;
    private RatingDto peterRatingStarWars5;
    private RatingDto hentRatingJumanji;
    private RatingDto peterRatingJumanji;
    private RatingDto lojzikRatingTiger;
    private RatingDto lojzikRatingHostiles;
    private RatingDto pepaRatingStarWars6;

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

        peter = new UserDto();
        peter.setFirstName("Peter");
        peter.setLastName("Mayhew");
        peter.setNick("Chewie");
        peter.setMail("peter@thisapp.dev");
        passw = "pepaNieJePan";
        peter.setId(userFacade.registerUser(peter, passw));

        hent = new UserDto();
        hent.setFirstName("John");
        hent.setLastName("Hent");
        hent.setNick("hent");
        hent.setMail("hent@thisapp.dev");
        passw = "hentJePan";
        hent.setId(userFacade.registerUser(hent, passw));

        lojzik = new UserDto();
        lojzik.setFirstName("Luis");
        lojzik.setLastName("NeverMind");
        lojzik.setNick("lojzik");
        lojzik.setMail("lojza@thisapp.dev");
        passw = "pepaLepa";
        lojzik.setId(userFacade.registerUser(lojzik, passw));
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
        rosamundPike.setDateOfBirth(LocalDate.of(1979, 1, 27));
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

        markHamill = new ActorDto();
        markHamill.setFirstName("Mark");
        markHamill.setLastName("Hamill");
        markHamill.setDateOfBirth(LocalDate.of(1951, 9, 25));
        markHamill.setId(actorFacade.create(markHamill));

        harrisonFord = new ActorDto();
        harrisonFord.setFirstName("Harrison");
        harrisonFord.setLastName("Ford");
        harrisonFord.setDateOfBirth(LocalDate.of(1942, 7, 13));
        harrisonFord.setId(actorFacade.create(harrisonFord));
        
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

        georgeLucas = new DirectorDto();
        georgeLucas.setFirstName("George");
        georgeLucas.setLastName("Lucas");
        georgeLucas.setDateOfBirth(LocalDate.of(1944, 5, 14));
        georgeLucas.setId(directorFacade.create(georgeLucas));

        stevenSpiel = new DirectorDto();
        stevenSpiel.setFirstName("Steven");
        stevenSpiel.setLastName("Spielberg");
        stevenSpiel.setDateOfBirth(LocalDate.of(1946, 12, 18));
        stevenSpiel.setId(directorFacade.create(stevenSpiel));

        sethGordon = new DirectorDto();
        sethGordon.setFirstName("Seth");
        sethGordon.setLastName("Gordon");
        sethGordon.setDateOfBirth(LocalDate.of(1974, 6, 15));
        sethGordon.setId(directorFacade.create(sethGordon));
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

        starWars4 = new CreateMovieDto();
        starWars4.setTitle("Star Wars: Episode IV - A New Hope");
        starWars4.setDescription("Luke Skywalker joins forces with a Jedi Knight, a cocky pilot, a Wookiee and two droids to save the galaxy from the Empire's world-destroying battle-station while also attempting to rescue Princess Leia from the evil Darth Vader.");
        starWars4.setDateOfRelease(LocalDate.of(1977,5,25));
        genres = new ArrayList();
        genres.add(action);
        genres.add(adventure);
        starWars4.setGenres(genres);
        actors = new ArrayList();
        actors.add(markHamill);
        actors.add(harrisonFord);
        starWars4.setActors(actors);
        starWars4.setDirector(georgeLucas);
        starWars4.setImage(imageRead(Resources.getResource("star-wars-4-img-base64.txt")));
        starWars4Id = movieFacade.createMovie(starWars4);

        starWars5 = new CreateMovieDto();
        starWars5.setTitle("Star Wars: Episode V - The Empire Strikes Back");
        starWars5.setDescription("After the rebels are overpowered by the Empire on the ice planet Hoth, Luke Skywalker begins Jedi training with Yoda. His friends accept shelter from a questionable ally as Darth Vader hunts them in a plan to capture Luke.");
        starWars5.setDateOfRelease(LocalDate.of(1980,5,21));
        genres = new ArrayList();
        genres.add(action);
        genres.add(adventure);
        starWars5.setGenres(genres);
        actors = new ArrayList();
        actors.add(markHamill);
        actors.add(harrisonFord);
        starWars5.setActors(actors);
        starWars5.setDirector(georgeLucas);
        starWars5.setImage(imageRead(Resources.getResource("star-wars-5-img-base64.txt")));
        starWars5Id = movieFacade.createMovie(starWars5);

        indiana = new CreateMovieDto();
        indiana.setTitle("Indiana Jones and the Kingdom of the Crystal Skull");
        indiana.setDescription("Famed archaeologist and adventurer Dr. Henry \"Indiana\" Jones, Jr. is called back into action, when he becomes entangled in a Soviet plot to uncover the secret behind mysterious artifacts known as the Crystal Skulls.");
        indiana.setDateOfRelease(LocalDate.of(2008,5,18));
        genres = new ArrayList();
        genres.add(action);
        genres.add(adventure);
        indiana.setGenres(genres);
        actors = new ArrayList();
        actors.add(harrisonFord);
        indiana.setActors(actors);
        indiana.setDirector(stevenSpiel);
        indiana.setImage(imageRead(Resources.getResource("indiana-jones-img-base64.txt")));
        indianaId = movieFacade.createMovie(indiana);

        baywatch = new CreateMovieDto();
        baywatch.setTitle("Baywatch");
        baywatch.setDescription("Devoted lifeguard Mitch Buchannon butts heads with a brash new recruit, as they uncover a criminal plot that threatens the future of the bay.");
        baywatch.setDateOfRelease(LocalDate.of(2017,5,25));
        genres = new ArrayList();
        genres.add(comedy);
        baywatch.setGenres(genres);
        actors = new ArrayList();
        actors.add(dwayneJohnson);
        baywatch.setActors(actors);
        baywatch.setDirector(sethGordon);
        baywatch.setImage(imageRead(Resources.getResource("baywatch-img-base64.txt")));
        baywatchId = movieFacade.createMovie(baywatch);

        StarWars6 = new CreateMovieDto();
        StarWars6.setTitle("Star Wars: Episode VI - Return of the Jedi");
        StarWars6.setDescription("fter a daring mission to rescue Han Solo from Jabba the Hutt, the rebels dispatch to Endor to destroy a more powerful Death Star. Meanwhile, Luke struggles to help Vader back from the dark side without falling into the Emperor's trap.");
        StarWars6.setDateOfRelease(LocalDate.of(1983,5,25));
        genres = new ArrayList();
        genres.add(action);
        genres.add(adventure);
        StarWars6.setGenres(genres);
        actors = new ArrayList();
        actors.add(markHamill);
        actors.add(harrisonFord);
        StarWars6.setActors(actors);
        StarWars6.setDirector(georgeLucas);
        StarWars6.setImage(imageRead(Resources.getResource("star-wars-6-img-base64.txt")));
        starWars6Id = movieFacade.createMovie(StarWars6);
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
        pepaRating.setOverallRating(40);
        pepaRating.setActorsRating(30);
        pepaRating.setScenarioRating(50);
        pepaRating.setMovie(movieFacade.findById(wonderWomanId));
        pepaRating.setUser(pepa);
        pepaRating.setDescription("This movie was average, at best. Really poor acting on Gal Gadots part. The story was very predictable, CGI was poor, and the story was phoned in. I really couldn't care less about any of the characters, and there actions were incredible illogical. I had hope with a twist at the end that it would get better, but once again, it just fell flat.");
        pepaRating.setId(ratingFacade.create(pepaRating));

        peterRating = new RatingDto();
        peterRating.setOverallRating(90);
        peterRating.setActorsRating(100);
        peterRating.setScenarioRating(90);
        peterRating.setMovie(movieFacade.findById(starWars4Id));
        peterRating.setUser(peter);
        peterRating.setDescription("Star wars made epic fantasy real. For a generation of people it has defined what the cinema experience is meant to be. Today it is probable that pc games will offer a deeper and more satisfying entertainment solution, but for pure visual and aural pleasure, mixed with basic emotional manipulation, there has never and will never be a better example of cinema than when star wars appeared over 25 years ago.");
        peterRating.setId(ratingFacade.create(peterRating));

        hentRating = new RatingDto();
        hentRating.setOverallRating(30);
        hentRating.setActorsRating(50);
        hentRating.setScenarioRating(20);
        hentRating.setMovie(movieFacade.findById(indianaId));
        hentRating.setUser(hent);
        hentRating.setDescription("Why, Lucas??? Why? Why...? ...Why??? Please! Tell me!!! Why???! Why?!!! WHY???!!!!");
        hentRating.setId(ratingFacade.create(hentRating));

        hentRatingBaywatch = new RatingDto();
        hentRatingBaywatch.setOverallRating(20);
        hentRatingBaywatch.setActorsRating(40);
        hentRatingBaywatch.setScenarioRating(10);
        hentRatingBaywatch.setMovie(movieFacade.findById(baywatchId));
        hentRatingBaywatch.setUser(hent);
        hentRatingBaywatch.setDescription("The only good thing about the movie is the cameo of Pamela Anderson and David Hasselhoff, but just look for the clips on youtube and you will be totally fine.");
        hentRatingBaywatch.setId(ratingFacade.create(hentRatingBaywatch));

        peterRatingDaddy = new RatingDto();
        peterRatingDaddy.setOverallRating(20);
        peterRatingDaddy.setActorsRating(35);
        peterRatingDaddy.setScenarioRating(10);
        peterRatingDaddy.setMovie(movieFacade.findById(daddyHomeId));
        peterRatingDaddy.setUser(peter);
        peterRatingDaddy.setDescription("Is this movie going to make any money? I'm asking because if it does, I should shut up and dedicate myself to gardening or something like that. Clearly, money is the only reason behind this enterprise and I'm giving it a 2 and not a 1 out of respect for the crew and all their hard work.Phew.");
        peterRatingDaddy.setId(ratingFacade.create(peterRatingDaddy));

        peterRatingStarWars5 = new RatingDto();
        peterRatingStarWars5.setOverallRating(92);
        peterRatingStarWars5.setActorsRating(95);
        peterRatingStarWars5.setScenarioRating(90);
        peterRatingStarWars5.setMovie(movieFacade.findById(starWars5Id));
        peterRatingStarWars5.setUser(peter);
        peterRatingStarWars5.setDescription("The first Star Wars movie, is a Sci-fi and cinematic masterpiece. But where Star Wars capitalized, Empire Strikes Back took it to the next level.");
        peterRatingStarWars5.setId(ratingFacade.create(peterRatingStarWars5));

        hentRatingJumanji = new RatingDto();
        hentRatingJumanji.setOverallRating(60);
        hentRatingJumanji.setActorsRating(75);
        hentRatingJumanji.setScenarioRating(50);
        hentRatingJumanji.setMovie(movieFacade.findById(jumanjiId));
        hentRatingJumanji.setUser(hent);
        hentRatingJumanji.setDescription("Jumanji 1995 has many memorial moments for example: The reveal of the Jumanji board, monkeys robbing a police car, the stampede, sinking floors, the flooded room with some crocodiles, the incredible use of the sound track, basically a lot of stuff. The original is well crafted and now it's 2017 and we have Jumanji: Welcome to the Jungle. It's a fun, action comedy film with really cool references to the first film.");
        hentRatingJumanji.setId(ratingFacade.create(hentRatingJumanji));

        peterRatingJumanji = new RatingDto();
        peterRatingJumanji.setOverallRating(40);
        peterRatingJumanji.setActorsRating(45);
        peterRatingJumanji.setScenarioRating(30);
        peterRatingJumanji.setMovie(movieFacade.findById(jumanjiId));
        peterRatingJumanji.setUser(peter);
        peterRatingJumanji.setDescription("A boring mediocre adventure movie filled by unnecessary joke and drama, along with bad cgi moments.");
        peterRatingJumanji.setId(ratingFacade.create(peterRatingJumanji));

        lojzikRatingTiger = new RatingDto();
        lojzikRatingTiger.setOverallRating(68);
        lojzikRatingTiger.setActorsRating(70);
        lojzikRatingTiger.setScenarioRating(80);
        lojzikRatingTiger.setMovie(movieFacade.findById(tigerZindaId));
        lojzikRatingTiger.setUser(lojzik);
        lojzikRatingTiger.setDescription("Full entertainment best action movie of Bollywood.");
        lojzikRatingTiger.setId(ratingFacade.create(lojzikRatingTiger));

        lojzikRatingHostiles = new RatingDto();
        lojzikRatingHostiles.setOverallRating(72);
        lojzikRatingHostiles.setActorsRating(70);
        lojzikRatingHostiles.setScenarioRating(90);
        lojzikRatingHostiles.setMovie(movieFacade.findById(hostilesId));
        lojzikRatingHostiles.setUser(lojzik);
        lojzikRatingHostiles.setDescription("Christian Bale shines in emotional journey that has a powerful and relevant message.");
        lojzikRatingHostiles.setId(ratingFacade.create(lojzikRatingHostiles));

        pepaRatingStarWars6 = new RatingDto();
        pepaRatingStarWars6.setOverallRating(90);
        pepaRatingStarWars6.setActorsRating(90);
        pepaRatingStarWars6.setScenarioRating(90);
        pepaRatingStarWars6.setMovie(movieFacade.findById(starWars6Id));
        pepaRatingStarWars6.setUser(pepa);
        pepaRatingStarWars6.setDescription("A fine ending - especially after having seen Episode III.");
        pepaRatingStarWars6.setId(ratingFacade.create(pepaRatingStarWars6));
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
