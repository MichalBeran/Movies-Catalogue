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
        
        userDaoImpl.setEntityManager(emf.createEntityManager());
        init();
    }
    
    @After
    public void tearDown() {
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
        
        userNormal2 = new User();
        userNormal2.setFirstName("Marienka");
        userNormal2.setLastName("Kovacova");
        userNormal2.setMail("marienka.kovacova@fi.muni.cz");
        userNormal2.setNick("marienka");
        userNormal2.setPassword("Jan1ck0");
        
        userAdmin = new User();
        userAdmin.setFirstName("Dominik");
        userAdmin.setLastName("Mlynka");
        userAdmin.setMail("dominik.mlynka@fi.muni.cz");
        userAdmin.setNick("domeniqo");
        userAdmin.setPassword("Mov1esCatalogue");
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
     * Test of create method, entity has not set any atribute before persisting.
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
     * Test of create method, entity is null.
     */
    @Test
    public void testCreateUser() {
        em.getTransaction().begin();
        userDaoImpl.create(userNormal1);
        em.getTransaction().commit();
        assertThat(userNormal1.getId()).isNotNull();
    }

    /**
     * Test of update method, of class UserDaoImpl.
     */
    @Test
    public void testUpdate() {
        System.out.println("update");
        User entity = null;
        UserDaoImpl instance = new UserDaoImpl();
        instance.update(entity);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findAll method, of class UserDaoImpl.
     */
    @Test
    public void testFindAll() {
        System.out.println("findAll");
        UserDaoImpl instance = new UserDaoImpl();
        List<User> expResult = null;
        List<User> result = instance.findAll();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findById method, of class UserDaoImpl.
     */
    @Test
    public void testFindById() {
        System.out.println("findById");
        Long id = null;
        UserDaoImpl instance = new UserDaoImpl();
        User expResult = null;
        User result = instance.findById(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of delete method, of class UserDaoImpl.
     */
    @Test
    public void testDelete() {
        System.out.println("delete");
        Long id = null;
        UserDaoImpl instance = new UserDaoImpl();
        instance.delete(id);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
