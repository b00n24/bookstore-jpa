/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.books.persistence;

import java.util.Calendar;
import java.util.List;
import org.books.persistence.dto.OrderInfo;
import org.books.persistence.entity.Order;
import org.books.persistence.service.OrderRepository;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Silvan
 */
public class OrderRepositoryTest extends QueriesWithDataTest {

    OrderRepository service = new OrderRepository(emf.createEntityManager());

    @Test
    public void queryOrderByCustomerAndYearUtil() {
	Calendar cal = Calendar.getInstance();
	cal.setTime(orderDate);
	int year = cal.get(Calendar.YEAR);
	final List<OrderInfo> result = service.getOrders(customer, year);

	assertFalse(result.isEmpty());
    }

    @Test
    public void queryOrderByCustomerAndYearUtil_wrongYear() {
	final List<OrderInfo> result = service.getOrders(customer, 1999);

	assertTrue(result.isEmpty());
    }

    @Test
    public void queryOrderByNumber() {
	Order result = service.getOrderByNumber(orderNumber);

	assertEquals(orderNumber, result.getNumber());
    }

    @Test
    public void queryOrderByNumberCaseInsensitiv() {
	Order result = service.getOrderByNumber(orderNumber.toUpperCase());
	
	assertEquals(orderNumber, result.getNumber());
    }
}
