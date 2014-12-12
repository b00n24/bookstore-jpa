package org.books.persistence.dto.queries;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import org.books.persistence.dto.OrderInfo;
import org.books.persistence.entity.Customer;

/**
 *
 * @author AWy
 */
public class QueryUtil {

    public static List<OrderInfo> getOrders(final Customer customer, final int year) {
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("bookstore");
	EntityManager em = emf.createEntityManager();
	Calendar cal = Calendar.getInstance();

	// First of year
	cal.set(Calendar.HOUR_OF_DAY, 0);
	cal.set(Calendar.MINUTE, 0);
	cal.set(Calendar.SECOND, 0);
	cal.set(Calendar.MILLISECOND, 0);
	cal.set(Calendar.DAY_OF_YEAR, 1);
	cal.set(Calendar.YEAR, year);
	Date firstOfYear = cal.getTime();

	// Last of year
	cal.set(Calendar.HOUR_OF_DAY, 23);
	cal.set(Calendar.MINUTE, 59);
	cal.set(Calendar.SECOND, 59);
	cal.set(Calendar.MILLISECOND, 999);
	cal.set(Calendar.MONTH, 11); // 11 = december
	cal.set(Calendar.DAY_OF_MONTH, 31); // new years eve
	Date lastOfYear = cal.getTime();
//
//	CriteriaBuilder cb = em.getCriteriaBuilder();
//	CriteriaQuery<Order> query = cb.createQuery(Order.class);
//	Root<Order> root = query.from(Order.class);
//	query.select(root);
//	Subquery<Customer> subquery = query.subquery(Customer.class);
//	subquery.where(root.get(Order_.customer))
//	query.where(cb.and(
//		cb.equal(root.get(Order_.customer), customer), 
//		    cb.and(cb.greaterThanOrEqualTo(root.<Date>get(Order_.date), firstOfYear), 
//		    cb.lessThanOrEqualTo(root.<Date>get(Order_.date), lastOfYear))
//		));
//	List<Order> result = em.createQuery(query).getResultList();
	TypedQuery<OrderInfo> query = em.createQuery("SELECT NEW org.books.persistence.dto.OrderInfo(o.id, o.number, o.date, o.amount, o.status)"
		+ " FROM BookOrder o JOIN o.customer c where o.customer.id = :customerId and"
		+ " o.date BETWEEN :firstOfYear and :lastOfYear", OrderInfo.class);
	query.setParameter("customerId", customer.getId());
	query.setParameter("firstOfYear", firstOfYear);
	query.setParameter("lastOfYear", lastOfYear);

	return query.getResultList();
    }

}
