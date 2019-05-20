package model;

import java.io.Serializable;

public class Textbook implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String bookTitle, isbn, author,description;
	public double price;

	
	public String getISBN() {
		return isbn;
	}

	public String getBookTitle() {
		return bookTitle;
	}

	public void setBookTitle(String bookTitle) {
		this.bookTitle = bookTitle;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getIsbn() {
		return isbn;
	}
	
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Textbook(String bookTitle, String author, String isbn, double price,String description) {
		this.bookTitle = bookTitle;
		this.author = author;
		this.isbn = isbn;
		this.price = price;
		this.description=description;
	}
	public Textbook(String bookTitle, String author, String isbn, double price) {
		this.bookTitle = bookTitle;
		this.author = author;
		this.isbn = isbn;
		this.price = price;
	}
	@Override
	public String toString() {
		return bookTitle + " - " + author + " ISBN: " + isbn + " $" + String.format("%,.2f", price);
	}

}
