package org.books.persistence;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import org.books.persistence.dto.queries.QueryUtil;
import org.books.persistence.entity.Address;
import org.books.persistence.entity.Book;
import org.books.persistence.entity.CreditCard;
import org.books.persistence.entity.Customer;
import org.books.persistence.entity.LineItem;
import org.books.persistence.entity.Order;
import org.books.persistence.enums.Binding;
import org.books.persistence.enums.Status;
import org.books.persistence.enums.Type;
import org.junit.After;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
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
    private final String orderNumber = "23abb432";
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
	creditCard.setType(Type.MasterCard);
	creditCard.setNumber("12345");
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
	book1.setBinding(Binding.Hardcover);
	book1.setNumberOfPages(55);
	book1.setPrice(new BigDecimal(price1, MathContext.DECIMAL32));
	em.persist(book1);

	// Book 2
	book2 = new Book();
	book2.setIsbn(isbn2);
	book2.setTitle(title2);
	book2.setAuthors("authors2");
	book2.setPublisher("publisher2");
	book2.setPublicationYear(2006);
	book2.setBinding(Binding.Paperback);
	book2.setNumberOfPages(99);
	book2.setPrice(new BigDecimal(price2, MathContext.DECIMAL32));
	em.persist(book2);

	// LineItem
	lineItem1 = new LineItem();
	lineItem1.setQuantity(1);
	lineItem1.setBook(book1);
	em.persist(lineItem1);

	// Order
	order = new Order();
	order.setNumber(orderNumber);
	order.setDate(orderDate);
	order.setAmount(new BigDecimal(55));
	order.setStatus(Status.ACCEPTED);
	order.getItems().add(lineItem1);
	em.persist(order);

	em.getTransaction().commit();
    }

    @After
    public void tearDown() {
	em.getTransaction().begin();

	Order o1 = em.merge(order);
	em.remove(o1);
	Customer c1 = em.merge(customer);
	em.remove(c1);
	LineItem i1 = em.merge(lineItem1);
	em.remove(i1);
	Book b1 = em.merge(book1);
	em.remove(b1);
	Book b2 = em.merge(book2);
	em.remove(b2);

	em.getTransaction().commit();
    }

    @Test
    public void queryBookByIsbn() {
	TypedQuery<Book> query = em.createNamedQuery(Book.QUERY_BY_ISBN, Book.class);
	query.setParameter(Book.PARAM_ISBN, isbn1);

	Book result = query.getSingleResult();

	assertEquals(isbn1, result.getIsbn());
    }

    @Test
    public void queryCustomerByEmail() {
	TypedQuery<Customer> query = em.createNamedQuery(Customer.QUERY_BY_EMAIL, Customer.class);
	query.setParameter(Customer.PARAM_EMAIL, email);

	Customer result = query.getSingleResult();

	assertEquals(email, result.getEmail());
    }

    @Test(expected = NoResultException.class)
    public void queryCustomerByEmailCaseSensitiv_shouldNotFind() {
	TypedQuery<Customer> query = em.createNamedQuery(Customer.QUERY_BY_EMAIL, Customer.class);
	query.setParameter(Customer.PARAM_EMAIL, email.toUpperCase());

	query.getSingleResult();
    }

    @Test
    public void queryCustomerByFirstName() {
	TypedQuery<Customer> query = em.createNamedQuery(Customer.QUERY_BY_NAME, Customer.class);
	query.setParameter(Customer.PARAM_NAME, firstName);

	Customer result = query.getSingleResult();

	assertEquals(customer.getId(), result.getId());
    }

    @Test(expected = NoResultException.class)
    public void queryCustomerByFirstNameCaseSensitiv_shouldNotFind() {
	TypedQuery<Customer> query = em.createNamedQuery(Customer.QUERY_BY_NAME, Customer.class);
	query.setParameter(Customer.PARAM_NAME, firstName.toUpperCase());

	query.getSingleResult();
    }

    @Test
    public void queryCustomerByLastName() {
	TypedQuery<Customer> query = em.createNamedQuery(Customer.QUERY_BY_NAME, Customer.class);
	query.setParameter(Customer.PARAM_NAME, lastName);

	Customer result = query.getSingleResult();

	assertEquals(customer.getId(), result.getId());
    }

    @Test(expected = NoResultException.class)
    public void queryCustomerByLastNameCaseSensitiv_shouldNotFind() {
	TypedQuery<Customer> query = em.createNamedQuery(Customer.QUERY_BY_NAME, Customer.class);
	query.setParameter(Customer.PARAM_NAME, lastName.toUpperCase());

	query.getSingleResult();
    }

    @Test
    public void queryOrderByNumber() {
	TypedQuery<Order> query = em.createNamedQuery(Order.QUERY_BY_NUMBER, Order.class);
	query.setParameter(Order.PARAM_NUMBER, orderNumber);

	Order result = query.getSingleResult();

	assertEquals(orderNumber, result.getNumber());
    }

    @Test(expected = NoResultException.class)
    public void queryOrderByNumberCaseSensitiv_shouldNotFind() {
	TypedQuery<Order> query = em.createNamedQuery(Order.QUERY_BY_NUMBER, Order.class);
	query.setParameter(Order.PARAM_NUMBER, orderNumber.toUpperCase());

	query.getSingleResult();
    }

    @Test
    public void queryOrderByCustomerAndYearUtil() {
	Calendar cal = Calendar.getInstance();
	cal.setTime(orderDate);
	int year = cal.get(Calendar.YEAR);
	final List<Order> result = QueryUtil.getOrders(customer, year);

	assertFalse(result.isEmpty());
    }

    @Test
    public void queryOrderByCustomerAndYearUtil_wrongYear() {
	final List<Order> result = QueryUtil.getOrders(customer, 1999);

	assertTrue(result.isEmpty());
    }

}
