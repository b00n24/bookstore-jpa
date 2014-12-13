/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.books.persistence.service;

import java.util.List;
import org.books.persistence.dto.CustomerInfo;
import org.books.persistence.entity.Customer;
import org.books.persistence.entity.Login;

/**
 *
 * @author Silvan
 */
public interface CustomerService {
    
    List<CustomerInfo> searchCustomersByName(String name);
    
    Login getLoginByUserName(String userName);

    Customer getCustomerByMail(String email);
}
