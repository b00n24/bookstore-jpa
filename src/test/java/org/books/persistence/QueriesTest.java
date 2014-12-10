package org.books.persistence;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import javax.persistence.TypedQuery;
import org.books.persistence.entity.Address;
import org.books.persistence.entity.Book;
import org.books.persistence.entity.CreditCard;
import org.books.persistence.entity.Customer;
import org.books.persistence.entity.LineItem;
import org.books.persistence.entity.Order;
import org.junit.After;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author AWy
 */
public class QueriesTest extends AbstractTest {

    private final String firstName = "Homer";
    private final String lastName = "Simpson";
    private final String email = "homer@simpson.com";
    private final String cityName = "St. Ursen";
    private final String isbn1 = "isbn1";
    private final String title1 = "title1";
    private final double price1 = 55.65;
    private final String isbn2 = "isbn2";
    private final String title2 = "title2";
    private final double price2 = 44.23;
    private final String orderNumber = "23432";
    private final Date orderDate = Calendar.getInstance().getTime();

    private Customer customer;
    private Address address;
    private CreditCard creditCard;
    private Book book1;
    private Book book2;
    private Order order;
    private LineItem lineItem1;

    @Before
    public void setup() {
	// GIVEN
	em.getTransaction().begin();

	// Address
	address = new Address();
	address.setStreet("Street");
	address.setCity(cityName);
	address.setPostalCode("postalCode");
	address.setCountry("country");

	// CreditCard
	creditCard = new CreditCard();
	creditCard.setType(CreditCard.Type.MasterCard);
	creditCard.setNumber("123456789");
	creditCard.setExpirationMonth(6);
	creditCard.setExpirationYear(16);

	// Customer
	customer = new Customer();
	customer.setFirstName(firstName);
	customer.setLastName(lastName);
	customer.setEmail(email);
	customer.setAddress(address);
	customer.setCreditCard(creditCard);
	em.persist(customer);

	// Book 1
	book1 = new Book();
	book1.setIsbn(isbn1);
	book1.setTitle(title1);
	book1.setAuthors("authors");
	book1.setPublisher("publisher");
	book1.setPublicationYear(1999);
	book1.setBinding(Book.Binding.Hardcover);
	book1.setNumberOfPages(55);
	book1.setPrice(new BigDecimal(price1));
	em.persist(book1);

	// Book 2
	book2 = new Book();
	book2.setIsbn(isbn2);
	book2.setTitle(title2);
	book2.setAuthors("authors2");
	book2.setPublisher("publisher2");
	book2.setPublicationYear(2006);
	book2.setBinding(Book.Binding.Paperback);
	book2.setNumberOfPages(99);
	book2.setPrice(new BigDecimal(price2));
	em.persist(book2);

	// LineItem
	lineItem1 = new LineItem();
	lineItem1.setQuantity(1);
	lineItem1.setBook(book1);

	// Order
	order = new Order();
	order.setNumber(orderNumber);
	order.setDate(orderDate);
	order.setAmount(new BigDecimal(55));
	order.setStatus(Order.Status.accepted);
	order.getItems().add(lineItem1);
	//em.persist(order);

	em.getTransaction().commit();
    }

    @After
    public void tearDown() {
	em.getTransaction().begin();

	Order o1 = em.merge(order);
	em.remove(o1);
	Customer c1 = em.merge(customer);
	em.remove(c1);

	em.getTransaction().commit();
    }
    
    @Test
    public void queryByIsbn() {
	TypedQuery<Book>  query = em.createNamedQuery(Book.QUERY_ISBN, Book.class);
	query.setParameter(Book.PARAM_ISBN, isbn1);
	
	Book result = query.getSingleResult();
	
	assertEquals(isbn1, result.getIsbn());
    }

}
