/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.facades;

import cz.muni.fi.pa165.builders.CreateMovieDtoBuilder;
import cz.muni.fi.pa165.configuration.ServiceConfiguration;
import cz.muni.fi.pa165.dto.*;
import cz.muni.fi.pa165.facade.ActorFacade;
import cz.muni.fi.pa165.facade.DirectorFacade;
import cz.muni.fi.pa165.facade.GenreFacade;
import cz.muni.fi.pa165.facade.MovieFacade;
import cz.muni.fi.pa165.mapping.BeanMappingService;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

/**
 *
 * @author Dominik
 */
@ContextConfiguration(classes = ServiceConfiguration.class)
public class MovieFacadeImplTest extends AbstractTransactionalJUnit4SpringContextTests{
    
    @Autowired
    private MovieFacade movieFacade;
    
    @Autowired
    private DirectorFacade directorFacade;
    
    @Autowired
    private ActorFacade actorFacade;

    @Autowired
    private GenreFacade genreFacade;
    
    @Autowired
    private BeanMappingService beanMappingService;
    
    private Long idA;
    private Long idD;
    private Long idG;
    private Long idG2;
    private Long idM;
    
    
    
    public MovieFacadeImplTest() {
    }
    
    @BeforeClass
    public static void beforeClass(){
        
    }
    
    @Before
    public void setUp() {
        
        DirectorDto dir = new DirectorDto();
        dir.setFirstName("First");
        dir.setLastName("Last");
        idD = directorFacade.create(dir);
        
        ActorDto act = new ActorDto();
        act.setFirstName("Firsta");
        act.setLastName("Lasta");
        idA = actorFacade.create(act);

        GenreDto gen = new GenreDto();
        gen.setDescription("blabla");
        gen.setName("hola");
        idG = genreFacade.create(gen);

        GenreDto gen2 = new GenreDto();
        gen2.setDescription("blabla");
        gen2.setName("action");
        idG2 = genreFacade.create(gen2);

        CreateMovieDto mov = new CreateMovieDto();
        mov.setDateOfRelease(LocalDate.now());
        mov.setDescription("bla");
        mov.setTitle("haha");
        List<ActorDto> actList = new ArrayList<>();
        actList.add(actorFacade.findById(idA));
        List<GenreDto> genList = new ArrayList<>();
        genList.add(genreFacade.findById(idG));
        mov.setActors(actList);
        mov.setGenres(genList);
        mov.setDirector(directorFacade.findById(idD));
        idM = movieFacade.createMovie(mov);
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testCreateMovie() {
        CreateMovieDto movieDto = new CreateMovieDtoBuilder().title("Film1")
                .actor(actorFacade.findById(idA)).director(directorFacade.findById(idD))
                .dateOfRelease(LocalDate.of(2017,11,26)).build();
        
        Long id = movieFacade.createMovie(movieDto);
        assertThat(id).isNotNull();
    }

    @Test
    public void testUpdate(){
        MovieDto mov = movieFacade.findById(idM);
        List<GenreDto> list = mov.getGenres();
        list.add(genreFacade.findById(idG2));
        mov.setGenres(list);
        movieFacade.update(mov);

        MovieDto mm = movieFacade.findById(idM);
        assertThat(mm.getGenres()).contains(genreFacade.findById(idG2));

    }
    
    @Test
    public void findByIdTest(){
        MovieDto mov = movieFacade.findById(idM);
        assertThat(mov).isNotNull();
    }
    
    @Test
    public void deleteMovieTest(){
        CreateMovieDto movieDto = new CreateMovieDtoBuilder().title("Film1")
                .actor(actorFacade.findById(idA)).director(directorFacade.findById(idD))
                .dateOfRelease(LocalDate.of(2017,11,26)).build();
        Long idToDelete = movieFacade.createMovie(movieDto);
        List<MovieDto> movies = movieFacade.findAll();
        int numberOfFilmsBeforeDelete = movies.size();
        
        String name = movieFacade.deleteMovie(idToDelete);
        assertThat(name).isNotNull();
        assertThat(name).isEqualTo(movieDto.getTitle());
        
        movies = movieFacade.findAll();
        
        assertThat(movies.size()).isEqualTo(numberOfFilmsBeforeDelete - 1);
    }
    
    @Test
    public void findAllTest(){
        CreateMovieDto movieDto = new CreateMovieDtoBuilder().title("Film1")
                .actor(actorFacade.findById(idA)).director(directorFacade.findById(idD))
                .dateOfRelease(LocalDate.of(2017,11,26)).build();
        movieFacade.createMovie(movieDto);
        
        assertThat(movieFacade.findAll().size()).isEqualTo(2);
    }
    
    @Test
    public void findAllByTitleTest(){
        CreateMovieDto movieDto = new CreateMovieDtoBuilder().title("Film part 1")
                .actor(actorFacade.findById(idA)).director(directorFacade.findById(idD))
                .dateOfRelease(LocalDate.of(2017,11,26)).build();
        CreateMovieDto movieDto2 = new CreateMovieDtoBuilder().title("Film part 2")
                .actor(actorFacade.findById(idA)).director(directorFacade.findById(idD))
                .dateOfRelease(LocalDate.of(2017,11,26)).build();
        Long id1 = movieFacade.createMovie(movieDto);
        Long id2 = movieFacade.createMovie(movieDto2);
        
        MovieDto movie1 = movieFacade.findById(id1);
        MovieDto movie2 = movieFacade.findById(id2);
        
        List<MovieDto> foundMovies = movieFacade.findAllByTitle(movieDto.getTitle());
        assertThat(foundMovies).containsOnly(movie1);
        
        foundMovies = movieFacade.findAllByTitle("Film part");
        assertThat(foundMovies).containsOnly(movie1, movie2);
        
    }
    
    @Test
    public void findAllByGenreTest(){
        CreateMovieDto movieDto1 = new CreateMovieDtoBuilder().title("Film1")
                .actor(actorFacade.findById(idA)).director(directorFacade.findById(idD))
                .dateOfRelease(LocalDate.of(2017,11,26)).genre(genreFacade.findById(idG)).build();
        CreateMovieDto movieDto2 = new CreateMovieDtoBuilder().title("Film2")
                .actor(actorFacade.findById(idA)).director(directorFacade.findById(idD))
                .dateOfRelease(LocalDate.of(2017,11,26)).genre(genreFacade.findById(idG)).
                genre(genreFacade.findById(idG2)).build();
        CreateMovieDto movieDto3 = new CreateMovieDtoBuilder().title("Film3")
                .actor(actorFacade.findById(idA)).director(directorFacade.findById(idD))
                .dateOfRelease(LocalDate.of(2017,11,26)).genre(genreFacade.findById(idG2)).build();
        Long id1 = movieFacade.createMovie(movieDto1);
        Long id2 = movieFacade.createMovie(movieDto2);
        Long id3 = movieFacade.createMovie(movieDto3);
        MovieDto movie1 = movieFacade.findById(id1);
        MovieDto movie2 = movieFacade.findById(id2);
        MovieDto movie3 = movieFacade.findById(id3);
        MovieDto defaultMovie = movieFacade.findById(idM);
        
        List<MovieDto> movies = movieFacade.findAllByGenre(idG);
        assertThat(movies).containsOnly(movie1, movie2, defaultMovie);
        movies = movieFacade.findAllByGenre(idG2);
        assertThat(movies).containsOnly(movie2, movie3);
    }
    
    @Test
    public void findAllByActorTest(){
        ActorDto act = new ActorDto();
        act.setFirstName("Firsta");
        act.setLastName("Lasta");
        Long actorId = actorFacade.create(act);
        
        CreateMovieDto movieDto1 = new CreateMovieDtoBuilder().title("Film1")
                .actor(actorFacade.findById(actorId)).director(directorFacade.findById(idD))
                .dateOfRelease(LocalDate.of(2017,11,26)).build();
        CreateMovieDto movieDto2 = new CreateMovieDtoBuilder().title("Film2")
                .actor(actorFacade.findById(idA)).actor(actorFacade.findById(actorId)).director(directorFacade.findById(idD))
                .dateOfRelease(LocalDate.of(2017,11,26)).build();
        Long id1 = movieFacade.createMovie(movieDto1);
        Long id2 = movieFacade.createMovie(movieDto2);
        MovieDto movie1 = movieFacade.findById(id1);
        MovieDto movie2 = movieFacade.findById(id2);
        MovieDto defaultMovie = movieFacade.findById(idM);
        
        List<MovieDto> movies = movieFacade.findAllByActor(idA);
        assertThat(movies).containsOnly(defaultMovie, movie2);
        movies = movieFacade.findAllByActor(actorId);
        assertThat(movies).containsOnly(movie1, movie2);
    }
   
    @Test
    public void findAllByDirectorTest(){
        DirectorDto dir = new DirectorDto();
        dir.setFirstName("First");
        dir.setLastName("Last");
        Long directorId = directorFacade.create(dir);
        
        CreateMovieDto movieDto1 = new CreateMovieDtoBuilder().title("Film1")
                .actor(actorFacade.findById(idA)).director(directorFacade.findById(idD))
                .dateOfRelease(LocalDate.of(2017,11,26)).build();
        CreateMovieDto movieDto2 = new CreateMovieDtoBuilder().title("Film2")
                .actor(actorFacade.findById(idA)).director(directorFacade.findById(directorId))
                .dateOfRelease(LocalDate.of(2017,11,26)).build();
        Long id1 = movieFacade.createMovie(movieDto1);
        Long id2 = movieFacade.createMovie(movieDto2);
        MovieDto movie1 = movieFacade.findById(id1);
        MovieDto movie2 = movieFacade.findById(id2);
        MovieDto defaultMovie = movieFacade.findById(idM);
        
        List<MovieDto> movies = movieFacade.findAllByDirector(directorId);
        assertThat(movies).containsOnly(movie2);
        movies = movieFacade.findAllByDirector(idD);
        assertThat(movies).containsOnly(defaultMovie, movie1);
    }
    
}
