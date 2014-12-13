package org.books.persistence.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import org.books.persistence.enums.Status;

// order is a reserved word in SQL
@Entity(name = "BookOrder")
@NamedQueries({
    @NamedQuery(name = Order.QUERY_BY_NUMBER, query = "SELECT o FROM BookOrder o WHERE o.number = :" + Order.PARAM_NUMBER)
})
public class Order implements Serializable {

    private static final long serialVersionUID = 1L;
    public static final String QUERY_BY_NUMBER = "Order.number";
    public static final String PARAM_NUMBER = "number";

    @Id
    @GeneratedValue
    private Long id;

    private String number;

    // date is a reserved word in SQL
    @Column(name = "orderDate")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date date;

    private BigDecimal amount;

    @Enumerated(EnumType.STRING)
    private Status status;

    @ManyToOne(optional = false, cascade = CascadeType.REFRESH)
    private Customer customer;

    @OneToOne(cascade = CascadeType.ALL, optional = false, orphanRemoval = true)
    private Address address;

    @OneToOne(cascade = CascadeType.ALL, optional = false, orphanRemoval = true)
    private CreditCard creditCard;

    @OneToMany(fetch = FetchType.EAGER, orphanRemoval = true, cascade = CascadeType.REFRESH)
    @JoinColumn(name = "bookorder_id")
    private List<LineItem> items;

    public Order() {
    }

    public Order(String number, Date date, BigDecimal amount, Status status,
	    Customer customer, Address address, CreditCard creditCard, List<LineItem> items) {
	this.number = number;
	this.date = date;
	this.amount = amount;
	this.status = status;
	this.customer = customer;
	this.address = address;
	this.creditCard = creditCard;
	this.items = items;
    }

    public Long getId() {
	return id;
    }

    public void setId(Long id) {
	this.id = id;
    }

    public String getNumber() {
	return number;
    }

    public void setNumber(String number) {
	this.number = number;
    }

    public Date getDate() {
	return date;
    }

    public void setDate(Date date) {
	this.date = date;
    }

    public BigDecimal getAmount() {
	return amount;
    }

    public void setAmount(BigDecimal amount) {
	this.amount = amount;
    }

    public Status getStatus() {
	return status;
    }

    public void setStatus(Status status) {
	this.status = status;
    }

    public Customer getCustomer() {
	if (customer == null) {
	    customer = new Customer();
	}
	return customer;
    }

    public void setCustomer(Customer customer) {
	this.customer = customer;
    }

    public Address getAddress() {
	if (address == null) {
	    address = new Address();
	}
	return address;
    }

    public void setAddress(Address address) {
	this.address = address;
    }

    public CreditCard getCreditCard() {
	if (creditCard == null) {
	    creditCard = new CreditCard();
	}
	return creditCard;
    }

    public void setCreditCard(CreditCard card) {
	this.creditCard = card;
    }

    public List<LineItem> getItems() {
	if (items == null) {
	    items = new ArrayList<>();
	}
	return items;
    }

    public void setItems(List<LineItem> items) {
	this.items = items;
    }

    @Override
    public String toString() {
	return "Order{" + "id=" + id + ", number=" + number + ", date=" + date + ", amount=" + amount + ", status=" + status + ", customer=" + customer + ", address=" + address + ", creditCard=" + creditCard + ", items=" + items + '}';
    }
}
