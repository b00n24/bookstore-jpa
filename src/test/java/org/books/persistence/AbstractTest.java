package org.books.persistence;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.junit.AfterClass;
import org.junit.BeforeClass;

/**
 *
 * @author AWy
 */
public abstract class AbstractTest {

    protected static EntityManagerFactory emf;
    protected static EntityManager em;

    public AbstractTest() {
    }

    @BeforeClass
    public static void setUpClass() {
	emf = Persistence.createEntityManagerFactory("bookstore");
	em = emf.createEntityManager();

	try {
	    em.getTransaction().begin();
	    em.createNativeQuery("delete from LINEITEM").executeUpdate();
	    em.createNativeQuery("delete from BOOKORDER").executeUpdate();
	    em.createNativeQuery("delete from BOOK").executeUpdate();
	    em.createNativeQuery("delete from CUSTOMER").executeUpdate();
	    em.createNativeQuery("delete from CREDITCARD").executeUpdate();
	    em.createNativeQuery("delete from ADDRESS").executeUpdate();
	    em.createNativeQuery("delete from LOGIN").executeUpdate();
	    //em.createNativeQuery("delete from SEQUENCE").executeUpdate();
	    em.getTransaction().commit();
	} catch (Exception e) {
	    // Ignore
	}
    }

    @AfterClass
    public static void tearDownClass() {
	if (em != null && em.isOpen()) {
	    em.close();
	}
	if (emf != null && emf.isOpen()) {
	    emf.close();
	}
    }
}
