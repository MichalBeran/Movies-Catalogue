/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.entities;

import cz.muni.fi.pa165.InMemoryDatabaseSpring;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.BeforeClass;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 *
 * @author Dominik
 * 
 * !!!DONT USE THIS CLASS, THIS IS JUST FOR DEMONSTRATION HOW TO GET APPLICATION
 * CONTEXT FOR TESTS!!!!
 */
public class ActorTest {
    
    private static EntityManagerFactory emf;
    private EntityManager em;
    
    @BeforeClass
    public static void setUpEMFactory(){
        
        new AnnotationConfigApplicationContext(InMemoryDatabaseSpring.class);
        emf = Persistence.createEntityManagerFactory("default");
    }
    
    @AfterClass
    public static void killFactory(){
        emf.close();
    }

    /**
     * Test of getId method, of class Actor.
     */
    @Test
    public void testStore() {
        Actor a = new Actor();
        a.setFirstName("dominik");
        a.setLastName("mlynka");
        
        em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(a);
        em.getTransaction().commit();
        em.close();
        
        em = emf.createEntityManager();
        em.getTransaction().begin();
        Actor b = em.find(Actor.class, a.getId());
        System.out.println(b.toString());
        em.close();
    }
    
}
