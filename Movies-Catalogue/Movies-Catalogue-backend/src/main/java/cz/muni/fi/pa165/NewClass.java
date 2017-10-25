/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165;

import java.sql.SQLException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 *
 * @author xmlynka
 */
public class NewClass {
    private static EntityManagerFactory emf;

	public static void main(String[] args) throws SQLException {
		// The following line is here just to start up a in-memory database
		AnnotationConfigApplicationContext appContext = new AnnotationConfigApplicationContext(InMemoryDatabaseSpring.class);

		emf = Persistence.createEntityManagerFactory("default");
		try {
			// BEGIN YOUR CODE
			task04();
			// END YOUR CODE
		} finally {
			emf.close();
			appContext.close();
		}
	}

	private static void task04() {
		// TODO under this line, persist two categories, one with name
		// Electronics and second with name Musical
		// You must first obtain the Entity manager
		// Then you have to start transaction using getTransaction().begin()
		// Then use persist() to persist both of the categories and finally commit the transaction

		// The code below is just testing code. Do not modify it
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		List<Category> categories = em.createQuery(
				"select c from Category c order by c.name", Category.class)
				.getResultList();

                if (categories.size() != 2) 
                    throw new RuntimeException("Expected two categories!");

		assertEq(categories.get(0).getName(), "Electronics");
		assertEq(categories.get(1).getName(), "Musical");

		em.getTransaction().commit();
		em.close();

		System.out.println("Succesfully found Electronics and Musical!");
	}
    
}
