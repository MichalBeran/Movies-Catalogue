package cz.muni.fi.pa165.configuration;

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
            mapping(MovieDto.class, Movie.class).fields("dateOfRelease", "dateOfRelease", FieldsMappingOptions.copyByReference());
            mapping(DirectorDto.class, Director.class).fields("dateOfBirth", "dateOfBirth", FieldsMappingOptions.copyByReference());
            mapping(ActorDto.class, Actor.class).fields("dateOfBirth", "dateOfBirth", FieldsMappingOptions.copyByReference());
        }
    }
}
