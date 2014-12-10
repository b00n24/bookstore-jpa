package org.books.persistence.entity;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Customer implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private Long id;

    private String firstName;

    private String lastName;

    private String email;

    @OneToOne(optional = false, cascade = CascadeType.ALL, orphanRemoval = true)
    private Address address;

    @OneToOne(optional = false, cascade = CascadeType.ALL, orphanRemoval = true)
    private CreditCard creditCard;

    public Customer() {
    }

    public Customer(String firstName, String lastName, String email, Address address, CreditCard creditCard) {
	this.firstName = firstName;
	this.lastName = lastName;
	this.email = email;
	this.address = address;
	this.creditCard = creditCard;
    }

    public Long getId() {
	return id;
    }

    public void setId(Long id) {
	this.id = id;
    }

    public String getFirstName() {
	return firstName;
    }

    public void setFirstName(String firstName) {
	this.firstName = firstName;
    }

    public String getLastName() {
	return lastName;
    }

    public void setLastName(String lastName) {
	this.lastName = lastName;
    }

    public String getEmail() {
	return email;
    }

    public void setEmail(String email) {
	this.email = email;
    }

    public Address getAddress() {
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

    public void setCreditCard(CreditCard creditCard) {
	this.creditCard = creditCard;
    }

    @Override
    public String toString() {
	return "Customer{" + "firstName=" + firstName + ", lastName=" + lastName
		+ ", password=" + email
		+ ", address=" + address + ", creditCard=" + creditCard + '}';
    }
}
