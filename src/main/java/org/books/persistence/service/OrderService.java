/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.books.persistence.service;

import java.util.List;
import org.books.persistence.dto.OrderInfo;
import org.books.persistence.entity.Customer;
import org.books.persistence.entity.Order;

/**
 *
 * @author Silvan
 */
public interface OrderService {
    
    Order getOrderByNumber(String number);
    
    List<OrderInfo> getOrders(Customer customer, int year);
}
