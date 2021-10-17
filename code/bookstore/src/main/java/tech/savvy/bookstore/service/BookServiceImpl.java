package tech.savvy.bookstore.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tech.savvy.bookstore.exception.BookStoreException;
import tech.savvy.bookstore.model.Book;
import tech.savvy.bookstore.repository.BookRepository;
import tech.savvy.bookstore.util.ErrorEnum;

@Service
public class BookServiceImpl implements BookService {

	@Autowired
	private BookRepository bookRepository;

	@Override
	public List<Book> findAllBooks() {
		List<Book> books = bookRepository.findAll();
		return books;
	}

	@Override
	public Book findBookByIsbn(String isbn) {
		return bookRepository.findBookByIsbn(isbn);
	}

	@Override
	public List<Book> findBooks(String bookTitle, String authorName) throws BookStoreException {
		// The search is OR condition, books match title and author name will be
		// returned.
		List<Book> books = bookRepository.findBookByTitleOrAuthor(bookTitle, authorName);
		if (books == null || books.size() == 0) {
			throw new BookStoreException(ErrorEnum.BOOK_NOT_FOUND.getDescription(), ErrorEnum.BOOK_NOT_FOUND.getCode());
		}
		return books;
	}

	@Override
	public Book createBookRecord(Book book) throws BookStoreException {
		Book savedBook = bookRepository.save(book);
		if (savedBook == null) {
			// Since save method will not throw exception, we have to check if it is null
			throw new BookStoreException(ErrorEnum.BOOK_SAVE_ERROR.getDescription(),
					ErrorEnum.BOOK_SAVE_ERROR.getCode());
		}
		return savedBook;
	}

	@Override
	public Book updateBookRecord(Book book) throws BookStoreException {
		Book savedBook = bookRepository.save(book);
		if (savedBook == null) {
			// Since save method will not throw exception, we have to check if it is null
			throw new BookStoreException(ErrorEnum.BOOK_UPDATE_ERROR.getDescription(),
					ErrorEnum.BOOK_UPDATE_ERROR.getCode());
		}
		return savedBook;
	}

	@Override
	public void deleteBookRecord(String isbn) throws BookStoreException {
		try {
			bookRepository.deleteBookByIsbn(isbn);
		} catch (Exception ex) {
			throw new BookStoreException(ErrorEnum.BOOK_DELETE_ERROR.getDescription(),
					ErrorEnum.BOOK_DELETE_ERROR.getCode());
		}
	}
}
