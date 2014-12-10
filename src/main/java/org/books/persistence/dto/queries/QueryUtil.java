package org.books.persistence.dto.queries;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.books.persistence.dto.OrderInfo;
import org.books.persistence.entity.Book;
import org.books.persistence.entity.Book_;
import org.books.persistence.entity.Customer;
import org.books.persistence.entity.Order;
import org.books.persistence.entity.Order_;

/**
 *
 * @author AWy
 */
public class QueryUtil {

    public static List<Order> getOrders(final Customer customer, final int year) {
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("bookstore");
	EntityManager em = emf.createEntityManager();
	Calendar cal = Calendar.getInstance();
	cal.set(Calendar.DAY_OF_YEAR, 1);
	cal.set(Calendar.YEAR, year);
	Date firstOfYear = cal.getTime();
	cal.set(Calendar.MONTH, 11); // 11 = december
	cal.set(Calendar.DAY_OF_MONTH, 31); // new years eve
	Date lastOfYear = cal.getTime();
	
	CriteriaBuilder cb = em.getCriteriaBuilder();
	CriteriaQuery<Order> query = cb.createQuery(Order.class);
	Root<Order> root = query.from(Order.class);
	query.select(root);
	query.where(cb.and(cb.greaterThanOrEqualTo(root.<Date>get(Order_.date), firstOfYear), cb.lessThanOrEqualTo(root.<Date>get(Order_.date), lastOfYear)));
	List<Order> result = em.createQuery(query).getResultList();
	
	// TODO Should return OrderInfo
	return result;
    }

}
