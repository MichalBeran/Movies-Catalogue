/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.dao;

import cz.muni.fi.pa165.InMemoryDatabaseSpring;
import cz.muni.fi.pa165.entities.User;
import cz.muni.fi.pa165.enums.Role;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.Rule;
import org.junit.rules.ExpectedException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import static org.assertj.core.api.Assertions.assertThat;

/**
 *
 * @author Dominik
 */
public class UserDaoImplTest {
    
    private static EntityManagerFactory emf;
    private EntityManager em;
    private UserDaoImpl userDaoImpl;
    private User userWithoutProperties;
    private User userWithId;
    private User userNormal1;
    private User userNormal2;
    private User userAdmin;
    
    @Rule
    public ExpectedException ex = ExpectedException.none();
    
    @BeforeClass
    public static void setUpClass() {
        new AnnotationConfigApplicationContext(InMemoryDatabaseSpring.class);
        emf = Persistence.createEntityManagerFactory("default");
    }
    
    @AfterClass
    public static void tearDownClass() {
        emf.close();
    }
    
    @Before
    public void setUp() {
        userDaoImpl = new UserDaoImpl();
        em = emf.createEntityManager();
        userDaoImpl.setEntityManager(em);
        init();
    }
    
    @After
    public void tearDown() {
        List<User> users = em.createQuery("select u from User u").getResultList();
        for (User u : users){
            if(!em.getTransaction().isActive()) {
                em.getTransaction().begin();
            }
            em.remove(u);
            em.getTransaction().commit();
        }
        userDaoImpl = null;
    }
    
    private void init(){
        userWithoutProperties = new User();
        
        userWithId = new User();
        userWithId.setId(new Long("10"));
        
        userNormal1 = new User();
        userNormal1.setFirstName("Janko");
        userNormal1.setLastName("Hrasko");
        userNormal1.setMail("janko.hrasko@fi.muni.cz");
        userNormal1.setNick("janicko");
        userNormal1.setPassword("Mar1enka5");
        userNormal1.addRole(Role.USER);
        
        userNormal2 = new User();
        userNormal2.setFirstName("Marienka");
        userNormal2.setLastName("Kovacova");
        userNormal2.setMail("marienka.kovacova@fi.muni.cz");
        userNormal2.setNick("marienka");
        userNormal2.setPassword("Jan1ck0");
        userNormal2.addRole(Role.USER);
        
        userAdmin = new User();
        userAdmin.setFirstName("Dominik");
        userAdmin.setLastName("Mlynka");
        userAdmin.setMail("dominik.mlynka@fi.muni.cz");
        userAdmin.setNick("domeniqo");
        userAdmin.setPassword("Mov1esCatalogue");
        userAdmin.addRole(Role.ADMINISTRATOR);
    }

    /**
     * Test of create method, entity has set Id before persisting.
     */
    @Test
    public void testCreateUserWithId() {
        ex.expect(IllegalArgumentException.class);
        userDaoImpl.create(userWithId);
    }
    
    /**
     * Test of create method, entity has not set any required atribute before persisting.
     */
    @Test
    public void testCreateUserWithoutProperties() {
        ex.expect(IllegalArgumentException.class);
        userDaoImpl.create(userWithoutProperties);
    }
    
    /**
     * Test of create method, entity is null.
     */
    @Test
    public void testCreateNullUser() {
        ex.expect(IllegalArgumentException.class);
        userDaoImpl.create(null);
    }
    
    /**
     * Test of create method, entity is fully initialized.
     */
    @Test
    public void testCreateUser() {
        em.getTransaction().begin();
        userDaoImpl.create(userNormal1);
        em.getTransaction().commit();
        assertThat(userNormal1.getId()).isNotNull();
        assertThat(userDaoImpl.findAll()).contains(userNormal1);
    }

    /**
     * Test of update method, of class UserDaoImpl.
     */
    @Test
    public void testUpdate() {
        em.getTransaction().begin();
        userDaoImpl.create(userNormal1);
        userNormal1.setFirstName("Updated");
        userDaoImpl.update(userNormal1);
        em.getTransaction().commit();
        em.getTransaction().begin();
        User updatedUser = userDaoImpl.findById(userNormal1.getId());
        assertThat(updatedUser.getFirstName()).isEqualTo("Updated");
        
    }

    /**
     * Test of findAll method, of class UserDaoImpl.
     */
    @Test
    public void testFindAll() {
        em.getTransaction().begin();
        userDaoImpl.create(userNormal1);
        userDaoImpl.create(userNormal2);
        em.getTransaction().commit();
        em.getTransaction().begin();
        List<User> stored = userDaoImpl.findAll();
        assertThat(stored).containsOnly(userNormal1, userNormal2);
        
    }

    /**
     * Test of findById method, of class UserDaoImpl.
     */
    @Test
    public void testFindById() {
        em.getTransaction().begin();
        userDaoImpl.create(userNormal1);
        em.getTransaction().commit();
        assertThat(userDaoImpl.findById(userNormal1.getId())).isEqualToComparingFieldByField(userNormal1);
    }

    /**
     * Test of delete method, of class UserDaoImpl.
     */
    @Test
    public void testDelete() {
        em.getTransaction().begin();
        userDaoImpl.create(userNormal1);
        em.getTransaction().commit();
        em.getTransaction().begin();
        userDaoImpl.delete(userNormal1.getId());
        em.getTransaction().commit();
        em.getTransaction().begin();
        ex.expect(IllegalArgumentException.class);
        userDaoImpl.findById(userNormal1.getId());
    }
    
}
