package cz.muni.fi.pa165.service;

import cz.muni.fi.pa165.configuration.ServiceConfiguration;
import cz.muni.fi.pa165.dao.UserDao;
import cz.muni.fi.pa165.entities.Rating;
import cz.muni.fi.pa165.entities.User;
import cz.muni.fi.pa165.enums.Role;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.internal.verification.VerificationModeFactory.times;

/**
 * @author Michal
 */
@ContextConfiguration(classes = ServiceConfiguration.class)
public class UserServiceImplTest extends AbstractJUnit4SpringContextTests {

    @Mock
    private UserDao userDao;

    @Mock
    private TimeService timeService;

    @Autowired
    @InjectMocks
    private UserService userService;

    @Rule
    public ExpectedException ex = ExpectedException.none();

    private User user1;
    private User user2;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        user1 = new User();
        user1.setFirstName("Josef");
        user1.setLastName("Novak");
        user1.setNick("pepa");
        user1.addRole(Role.ADMINISTRATOR);
        user1.setMail("pepa@novak.cz");

        user2 = new User();
        user2.setFirstName("Anna");
        user2.setLastName("Mala");
        user2.setNick("mini");
        user2.addRole(Role.USER);
        user2.setMail("mini@seznam.cz");

    }

    @After
    public void tearDown() {
    }

    @Test
    public void testRegisterNewUser() {
        User calledUser = new User();
        calledUser.setFirstName("Josef");
        calledUser.setLastName("Novak");
        calledUser.setNick("pepa");
        calledUser.addRole(Role.ADMINISTRATOR);
        calledUser.setMail("pepa@novak.cz");

        Long id = 1L;
        User expectedUser = new User();
        expectedUser.setFirstName("Josef");
        expectedUser.setLastName("Novak");
        expectedUser.setNick("pepa");
        expectedUser.addRole(Role.ADMINISTRATOR);
        expectedUser.setMail("pepa@novak.cz");

        doAnswer(invocation -> {
            Object[] args = invocation.getArguments();
            ((User) args[0]).setId(id);
            return null;
        }).when(userDao).create(calledUser);

        expectedUser.setId(id);
        assertThat(userService.registerUser(calledUser, "password")).isEqualTo(expectedUser.getId());
        verify(userDao, times(1)).create(expectedUser);

    }

    @Test
    public void testFindAll() {
        userService.registerUser(user1, "newPass");
        userService.registerUser(user2, "blabla");
        List<User> users = new ArrayList<>();
        users.add(user1);
        users.add(user2);
        when(userDao.findAll()).thenReturn(users);

        assertThat(userService.findAllUsers()).contains(user1);
        assertThat(userService.findAllUsers()).contains(user2);
    }

    @Test
    public void testUpdateUser() {
        userService.registerUser(user1, "newPass");

        Long id = 1L;
        user1.setId(id);
        when(userDao.findById(id)).thenReturn(user1);
        assertThat(userService.update(user1, "newPass")).isEqualTo(user1);
        verify(userDao, times(1)).update(user1);
    }

    @Test
    public void testCreateNullUser() {
        ex.expect(NullPointerException.class);
        userService.registerUser(null, "password");
    }

    @Test
    public void testCreateNullPasswordUser() {
        ex.expect(NullPointerException.class);
        userService.registerUser(user1, null);
    }

    @Test
    public void testCreateEmptyPasswordUser() {
        ex.expect(IllegalArgumentException.class);
        userService.registerUser(user1, "");
    }

    @Test
    public void testDeleteUser() {
        user1.setId(2L);
        userService.delete(user1);
        verify(userDao, times(1)).delete(user1.getId());
    }

    @Test
    public void testFindUserById() {
        Long id = 1L;
        user1.setId(id);

        when(userDao.findById(user1.getId())).thenReturn(user1);

        assertThat(userService.findUserById(id)).isEqualTo(user1);
        verify(userDao, times(1)).findById(user1.getId());
    }

    @Test
    public void testFindUserByMail() {
        userService.registerUser(user1, "pass");

        when(userDao.findByMail(user1.getMail())).thenReturn(user1);

        assertThat(userService.findUserByMail("pepa@novak.cz")).isEqualTo(user1);
        verify(userDao, times(1)).findByMail(user1.getMail());
    }

    @Test
    public void testFindUserByNick() {
        when(userDao.findByNick(user1.getNick())).thenReturn(user1);

        assertThat(userService.findUserByNick("pepa")).isEqualTo(user1);
        verify(userDao, times(1)).findByNick(user1.getNick());
    }

    @Test
    public void testSignInSuccess() {
        userService.registerUser(user1, "pass");

        when(userDao.findByMail(user1.getMail())).thenReturn(user1);

        assertThat(userService.signIn(user1, "pass")).isNotNull();
    }

    @Test
    public void testSignInFail() {
        userService.registerUser(user1, "pass");

        when(userDao.findByMail(user1.getMail())).thenReturn(user1);

        assertThat(userService.signIn(user1, "password")).isNull();
    }

    @Test
    public void testIsAdminSucces() {
        Long id = 1L;
        user1.setId(id);
        when(userDao.findById(user1.getId())).thenReturn(user1);
        assertThat(userService.isAdmin(user1)).isTrue();
    }

    @Test
    public void testIsAdminFail() {
        Long id = 1L;
        user1.setId(id);
        when(userDao.findById(user2.getId())).thenReturn(user2);
        assertThat(userService.isAdmin(user2)).isFalse();
    }

}