/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.books.persistence.service;

import java.util.List;
import org.books.persistence.entity.Book;

/**
 *
 * @author Silvan
 */
public interface BookService {
    
    Book getBookByISBN(String isbn);
    
    List<Book> searchBooks(String keywords);
}
