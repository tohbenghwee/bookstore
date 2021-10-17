package tech.savvy.bookstore.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table(name = "book")
public class Book {

	 @Id
     //@GeneratedValue(strategy = GenerationType.IDENTITY)
	 @Size(min = 13, max = 13 ,message = "size must be between 13 for isbn")
     private String isbn;
	 
	 @Size(min = 1, max = 250, message = "title cannot be empty")
     private String title;
     
     private int year;
     
     private double price;
     
     @Size(min = 1, max = 50 , message = "genre cannot be empty")
     private String genre;
     
     @OneToMany(mappedBy="book" , cascade = CascadeType.ALL)
     private Set<Author> authors = new HashSet<Author>();

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

	public Set<Author> getAuthors() {
		return authors;
	}

	public void setAuthors(Set<Author> authors) {
		this.authors = authors;
	}

	public void addAuthors(Author author) {
		this.authors.add(author);
	}
     
}
