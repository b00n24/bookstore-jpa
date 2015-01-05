package org.books.persistence.dto;

/**
 *
 * @author AWy
 */
public class OrderItem {
    private String isbn;
    private Integer quantity;

    public String getIsbn() {
	return isbn;
    }

    public void setIsbn(String isbn) {
	this.isbn = isbn;
    }

    public Integer getQuantity() {
	return quantity;
    }

    public void setQuantity(Integer quantity) {
	this.quantity = quantity;
    }
    
    
}
