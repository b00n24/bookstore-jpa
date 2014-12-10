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
	emf = Persistence.createEntityManagerFactory("hr");
	em = emf.createEntityManager();

//	em.getTransaction().begin();
//	em.createNativeQuery("delete from SOFTWAREPROJECT").executeUpdate();
//	em.createNativeQuery("delete from QUALITYPROJECT").executeUpdate();
//	em.createNativeQuery("delete from PROJECTCOLLABORATION").executeUpdate();
//	em.createNativeQuery("delete from DESIGNPROJECT").executeUpdate();
//	em.createNativeQuery("delete from HARDWAREPROJECT_HARDWARECOMPONENT").executeUpdate();
//	em.createNativeQuery("delete from HARDWARECOMPONENT").executeUpdate();
//	em.createNativeQuery("delete from HARDWAREPROJECT").executeUpdate();
//	em.createNativeQuery("delete from PROJECT").executeUpdate();
//	em.createNativeQuery("delete from PRODUCT").executeUpdate();
//	em.createNativeQuery("delete from PLATFORM").executeUpdate();
//	em.createNativeQuery("delete from EMPLOYEE").executeUpdate();
//	em.createNativeQuery("delete from PHONE").executeUpdate();
//	em.createNativeQuery("delete from DEPARTMENT").executeUpdate();
//	em.createNativeQuery("delete from ADDRESS").executeUpdate();
////	em.createNativeQuery("delete from SEQUENCE").executeUpdate();
	em.getTransaction().commit();
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
