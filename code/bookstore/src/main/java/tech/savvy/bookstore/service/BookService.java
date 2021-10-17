package tech.savvy.bookstore.service;

import java.util.List;

import tech.savvy.bookstore.exception.BookStoreException;
import tech.savvy.bookstore.model.Book;

public interface BookService {

	List<Book> findAllBooks();
	
	Book findBookByIsbn(String isbn);
	
	List<Book> findBooks(String title , String author) throws BookStoreException;
	
	Book createBookRecord (Book book) throws BookStoreException;
	
	Book updateBookRecord (Book book) throws BookStoreException;
	
	void deleteBookRecord (String isbn) throws BookStoreException;
}
