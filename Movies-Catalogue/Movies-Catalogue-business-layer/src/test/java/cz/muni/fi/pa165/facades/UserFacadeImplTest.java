package cz.muni.fi.pa165.facades;

import cz.muni.fi.pa165.configuration.ServiceConfiguration;
import cz.muni.fi.pa165.dto.UserDto;
import cz.muni.fi.pa165.facade.UserFacade;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

import static org.assertj.core.api.Assertions.assertThat;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @author Michal
 */
@ContextConfiguration(classes = ServiceConfiguration.class)
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@Transactional
public class UserFacadeImplTest extends AbstractJUnit4SpringContextTests {

    @Autowired
    private UserFacade userFacade;

    private UserDto userDto;

    @BeforeClass
    public static void init(){

    }

    @Before
    public void setUp() {
        userDto = new UserDto();
        userDto.setNick("pepa");
        userDto.setFirstName("Josef");
        userDto.setLastName("Novak");
        userDto.setMail("josef@novak.cz");
        userDto.setPassword("pepik");
    }

    @After
    public void tearDown() {
        List<UserDto> userDtos = userFacade.findAllUsers();
        for (UserDto dto : userDtos) {
            userFacade.delete(dto);
        }
    }

    @Test
    public void testRegisterUser(){
        Long expectedId = userFacade.registerUser(userDto, "pepik");
        assertThat(expectedId).isNotNull();
    }

    @Test
    public void testFindAll(){
        userFacade.registerUser(userDto, "pepik");
        assertThat(userFacade.findAllUsers().size()).isEqualTo(1);
    }

    @Test
    public void testIsAdmin(){
        Long id = userFacade.registerUser(userDto, "pepik");
        userDto.setId(id);
        userFacade.makeAdmin(userDto);

        List<UserDto> users = userFacade.findAllUsers();
        Boolean is = userFacade.isAdmin(userDto);
        assertThat(userFacade.isAdmin(userDto)).isTrue();
    }
}
