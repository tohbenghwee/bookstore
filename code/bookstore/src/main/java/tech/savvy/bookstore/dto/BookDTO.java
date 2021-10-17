package tech.savvy.bookstore.dto;

import java.util.HashSet;
import java.util.Set;

public class BookDTO {

     private String isbn;
	 
     private String title;
     
     private int year;
     
     private double price;
     
     private String genre;
     
     private Set<AuthorDTO> authors = new HashSet<AuthorDTO>();

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public Set<AuthorDTO> getAuthors() {
		return authors;
	}

	public void addAuthors(AuthorDTO author) {
		this.authors.add(author);
	}
}
