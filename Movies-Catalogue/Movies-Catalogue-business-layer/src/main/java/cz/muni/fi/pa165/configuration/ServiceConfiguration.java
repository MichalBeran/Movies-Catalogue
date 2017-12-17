package cz.muni.fi.pa165.configuration;

import cz.muni.fi.pa165.dto.detail.MovieDetailDto;
import cz.muni.fi.pa165.dto.view.ActorViewDto;
import cz.muni.fi.pa165.dto.view.DirectorViewDto;
import cz.muni.fi.pa165.dto.view.GenreViewDto;
import cz.muni.fi.pa165.PersistenceApplicationContext;
import cz.muni.fi.pa165.dto.*;
import cz.muni.fi.pa165.entities.Actor;
import cz.muni.fi.pa165.entities.Director;
import cz.muni.fi.pa165.entities.Movie;
import cz.muni.fi.pa165.entities.User;
import cz.muni.fi.pa165.facade.ActorFacadeImpl;
import cz.muni.fi.pa165.service.ActorServiceImpl;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.dozer.loader.api.BeanMappingBuilder;
import org.dozer.loader.api.FieldsMappingOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import static org.dozer.loader.api.FieldsMappingOptions.hintB;

/**
 * @author Michal
 */
@Configuration
@Import(PersistenceApplicationContext.class)
@ComponentScan(basePackageClasses={ActorServiceImpl.class, ActorFacadeImpl.class})
public class ServiceConfiguration {
    
    @Bean
    public Mapper dozer(){
        DozerBeanMapper dozer = new DozerBeanMapper();
        dozer.addMapping(new DozerCustomConfig());
        return dozer;
    }

    public class DozerCustomConfig extends BeanMappingBuilder {
        @Override
        protected void configure() {
            mapping(User.class, UserDto.class);
            mapping(CreateMovieDto.class, Movie.class).fields("dateOfRelease", "dateOfRelease", FieldsMappingOptions.copyByReference());
            //mapping(MovieDto.class, Movie.class).fields("dateOfRelease", "dateOfRelease", FieldsMappingOptions.copyByReference());
            mapping(Movie.class, MovieDto.class)
                    .fields("dateOfRelease", "dateOfRelease", FieldsMappingOptions.copyByReference())
                    .fields("genres", "genres", hintB(GenreDto.class))
                    .fields("actors", "actors", hintB(ActorDto.class));
            mapping(DirectorDto.class, Director.class).fields("dateOfBirth", "dateOfBirth", FieldsMappingOptions.copyByReference());
            mapping(ActorDto.class, Actor.class).fields("dateOfBirth", "dateOfBirth", FieldsMappingOptions.copyByReference());

            mapping(ActorDto.class, ActorViewDto.class).exclude("dateOfBirth");
            mapping(DirectorDto.class, DirectorViewDto.class).exclude("dateOfBirth");
            mapping(GenreDto.class, GenreViewDto.class);
            mapping(MovieDto.class, MovieDetailDto.class)
                    .fields("genres", "genres", hintB(GenreViewDto.class))
                    .fields("actors", "actors", hintB(ActorViewDto.class))
                    .exclude("dateOfRelease");
        }
    }
}
