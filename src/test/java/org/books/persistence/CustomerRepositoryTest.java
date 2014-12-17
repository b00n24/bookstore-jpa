/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.books.persistence;

import java.util.List;
import org.books.persistence.dto.CustomerInfo;
import org.books.persistence.entity.Customer;
import org.books.persistence.entity.Login;
import org.books.persistence.service.CustomerRepository;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Silvan
 */
public class CustomerRepositoryTest extends QueriesWithDataTest {

    CustomerRepository service = new CustomerRepository(emf.createEntityManager());

    @Test
    public void queryCustomerByEmail() {
	Customer result = service.findByMail(email);

	assertEquals(email, result.getEmail());
    }

    public void queryCustomerByEmailCaseInsensitiv() {
	Customer result = service.findByMail(email.toUpperCase());

	assertEquals(email, result.getEmail());
    }

    @Test
    public void queryCustomersByFirstNameNoResult() {
	List<CustomerInfo> customersByName = service.searchCustomersByName("randomname");
	assertEquals(0, customersByName.size());
    }

    @Test
    public void queryCustomersByFirstNamePart() {
	List<CustomerInfo> customersByName = service.searchCustomersByName("ome");
	assertEquals(2, customersByName.size());
	assertEquals(customer.getId(), customersByName.get(0).getId());
	assertEquals(customer2.getId(), customersByName.get(1).getId());
    }

    @Test
    public void queryCustomersByFirstName() {
	List<CustomerInfo> customersByName = service.searchCustomersByName(firstName);
	assertEquals(2, customersByName.size());
	assertEquals(customer.getId(), customersByName.get(0).getId());
	assertEquals(customer2.getId(), customersByName.get(1).getId());
    }

    @Test
    public void queryCustomersByFirstNameCaseInsensitiv() {
	List<CustomerInfo> customersByName = service.searchCustomersByName(firstName.toUpperCase());
	assertEquals(2, customersByName.size());
	assertEquals(customer.getId(), customersByName.get(0).getId());
	assertEquals(customer2.getId(), customersByName.get(1).getId());
    }

    @Test
    public void queryCustomersByFirstName2() {
	List<CustomerInfo> customersByName = service.searchCustomersByName("2");
	assertEquals(1, customersByName.size());
	assertEquals(customer2.getId(), customersByName.get(0).getId());
    }

    @Test
    public void queryCustomersByLastName() {
	List<CustomerInfo> customersByName = service.searchCustomersByName(lastName);
	assertEquals(2, customersByName.size());
	assertEquals(customer.getId(), customersByName.get(0).getId());
	assertEquals(customer2.getId(), customersByName.get(1).getId());
    }

    @Test
    public void queryCustomersByLastName2() {
	List<CustomerInfo> customersByName = service.searchCustomersByName(lastName + "2");
	assertEquals(1, customersByName.size());
	assertEquals(customer2.getId(), customersByName.get(0).getId());
    }
    
    @Test
    public void queryLoginByName() {
	Login result = service.findLoginByUserName(email);

	assertEquals(email, result.getUserName());
    }    
}
