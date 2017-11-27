package cz.muni.fi.pa165.facades;

import cz.muni.fi.pa165.configuration.ServiceConfiguration;
import cz.muni.fi.pa165.dto.GenreDto;
import cz.muni.fi.pa165.facade.GenreFacade;
import cz.muni.fi.pa165.service.GenreService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

/**
 * @author Marek Urban
 */
@ContextConfiguration(classes = ServiceConfiguration.class)
public class GenreFacadeImplTest extends AbstractJUnit4SpringContextTests{

    @Autowired
    private GenreFacade genreFacade;

    @Before
    public void setUp(){}

    @After
    public void tearDown(){}

    @Test
    public void testFindAll(){

    }

    @Test
    public void testCreate(){
        GenreDto genreDto = new GenreDto();
        genreDto.setName("Sci-Fi");
        genreDto.setDescription("Sci-Fi description");
        Long id = genreFacade.create(genreDto);

        assertThat(id).isNotNull();
    }
}
