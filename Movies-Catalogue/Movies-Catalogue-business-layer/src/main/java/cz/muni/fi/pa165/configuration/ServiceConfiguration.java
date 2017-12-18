package cz.muni.fi.pa165.configuration;

import cz.muni.fi.pa165.dto.detail.MovieDetailDto;
import cz.muni.fi.pa165.dto.view.ActorViewDto;
import cz.muni.fi.pa165.dto.view.DirectorViewDto;
import cz.muni.fi.pa165.dto.view.GenreViewDto;
import cz.muni.fi.pa165.PersistenceApplicationContext;
import cz.muni.fi.pa165.dto.*;
import cz.muni.fi.pa165.dto.detail.ActorDetailDto;
import cz.muni.fi.pa165.dto.detail.DirectorDetailDto;
import cz.muni.fi.pa165.dto.detail.GenreDetailDto;
import cz.muni.fi.pa165.dto.view.MovieViewDto;
import cz.muni.fi.pa165.entities.Actor;
import cz.muni.fi.pa165.entities.Director;
import cz.muni.fi.pa165.entities.Movie;
import cz.muni.fi.pa165.entities.User;
import cz.muni.fi.pa165.facade.ActorFacadeImpl;
import cz.muni.fi.pa165.service.ActorServiceImpl;
import org.dozer.CustomConverter;
import org.dozer.DozerBeanMapper;
import org.dozer.DozerConverter;
import org.dozer.Mapper;
import org.dozer.loader.api.BeanMappingBuilder;
import org.dozer.loader.api.FieldsMappingOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import java.util.List;

import static org.dozer.loader.api.FieldsMappingOptions.customConverter;
import static org.dozer.loader.api.FieldsMappingOptions.hintB;

/**
 * @author Michal
 */
@Configuration
@Import(PersistenceApplicationContext.class)
@ComponentScan(basePackageClasses = {ActorServiceImpl.class, ActorFacadeImpl.class})
public class ServiceConfiguration {

    @Bean
    public Mapper dozer() {
        DozerBeanMapper dozer = new DozerBeanMapper();
        dozer.addMapping(new DozerCustomConfig());
        return dozer;
    }

    public class DozerCustomConfig extends BeanMappingBuilder {
        @Override
        protected void configure() {

            mapping(User.class, UserDto.class);
            mapping(CreateMovieDto.class, Movie.class).fields("dateOfRelease", "dateOfRelease", FieldsMappingOptions.copyByReference()).fields("image", "image", FieldsMappingOptions.customConverter(StringToByteConverter.class));
            //mapping(MovieDto.class, Movie.class).fields("dateOfRelease", "dateOfRelease", FieldsMappingOptions.copyByReference());
            mapping(MovieDetailDto.class, Movie.class).fields("image", "image", FieldsMappingOptions.customConverter(StringToByteConverter.class));
            mapping(Movie.class, MovieDto.class)
                    .fields("dateOfRelease", "dateOfRelease", FieldsMappingOptions.copyByReference())
                    .fields("genres", "genres", hintB(GenreDto.class))
                    .fields("actors", "actors", hintB(ActorDto.class))
                    .fields("image", "image", FieldsMappingOptions.customConverter(StringToByteConverter.class));
            mapping(DirectorDto.class, Director.class).fields("dateOfBirth", "dateOfBirth", FieldsMappingOptions.copyByReference());
            mapping(ActorDto.class, Actor.class).fields("dateOfBirth", "dateOfBirth", FieldsMappingOptions.copyByReference());

            mapping(ActorDto.class, ActorViewDto.class).exclude("dateOfBirth");
            mapping(DirectorDto.class, DirectorViewDto.class).exclude("dateOfBirth");
            mapping(GenreDto.class, GenreViewDto.class);
            mapping(MovieDto.class, MovieDetailDto.class)
                    .fields("genres", "genres", hintB(GenreViewDto.class))
                    .fields("actors", "actors", hintB(ActorViewDto.class))
                    .fields("dateOfRelease", "dateOfRelease", FieldsMappingOptions.copyByReference());
            mapping(ActorDto.class, ActorDetailDto.class)
                    .fields("dateOfBirth", "dateOfBirth", FieldsMappingOptions.copyByReference())
                    .fields("movies", "movies", hintB(MovieViewDto.class));
            mapping(DirectorDto.class, DirectorDetailDto.class)
                    .fields("dateOfBirth", "dateOfBirth", FieldsMappingOptions.copyByReference())
                    .fields("movies", "movies", hintB(MovieViewDto.class));
            mapping(GenreDto.class, GenreDetailDto.class)
                    .fields("movies", "movies", hintB(MovieViewDto.class));
        }
    }

    public static class StringToByteConverter extends DozerConverter<String, byte[]> implements CustomConverter {
        public StringToByteConverter() {
            super(String.class, byte[].class);
        }

        public byte[] convertTo(String source, byte[] destination) {
            if (source == null) {
                return new byte[0];
            } else {
                return source.getBytes();
            }
        }

        public String convertFrom(byte[] source, String destination) {
            if (source == null) {
                return new String();
            } else {
                return new String(source);
            }
        }
    }
}
