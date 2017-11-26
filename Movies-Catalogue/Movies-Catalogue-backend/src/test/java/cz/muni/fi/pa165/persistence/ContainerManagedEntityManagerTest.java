/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.persistence;

import cz.muni.fi.pa165.PersistenceApplicationContext;
import cz.muni.fi.pa165.dao.MovieDao;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

/**
 *
 * @author Dominik
 */
@ContextConfiguration(classes = PersistenceApplicationContext.class)
public class ContainerManagedEntityManagerTest extends AbstractTransactionalJUnit4SpringContextTests{
    
    @Autowired
    private MovieDao movieDao;
    
    @PersistenceContext
    private EntityManager em;
    
    @Test
    public void test(){
        System.out.println("ano");
    }
}
