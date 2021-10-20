package tech.savvy.bookstore.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import tech.savvy.bookstore.dto.ApiResponse;
import tech.savvy.bookstore.dto.BookDTO;
import tech.savvy.bookstore.exception.BookStoreException;
import tech.savvy.bookstore.mapper.BookMapper;
import tech.savvy.bookstore.model.Book;
import tech.savvy.bookstore.service.BookService;
import tech.savvy.bookstore.util.ErrorEnum;

@RestController
@RequestMapping(value = "/api")
@Api( tags = "api")
@Validated
public class BookStoreController {
	
	@Autowired
	private BookService bookService;

	@GetMapping(value = "/find-all-books")
	List<BookDTO> findAllBooks() {
		List<Book> books = bookService.findAllBooks();
		return BookMapper.entityToDTOs(books);
	}

	@GetMapping(value = "/find-books")
	List<BookDTO> findBooks(@RequestParam("bookTitle") String bookTitle,
			@RequestParam("authorName") String authorName) {
		List<Book> books = bookService.findBooks(bookTitle, authorName);
		return BookMapper.entityToDTOs(books);
	}

	@PreAuthorize("hasAnyRole('ADMIN','MANAGER')")
	@PostMapping(value = "/create-book-record")
	ResponseEntity<?> createBookRecord(@RequestBody BookDTO bookdto) {
		Book book = BookMapper.dtoToEntity(bookdto);
		if (bookService.findBookByIsbn(bookdto.getIsbn()) != null) {
			// Book with the same isbn will raise a isbn exist error during creation
			throw new BookStoreException(ErrorEnum.BOOK_ISBN_EXIST.getDescription(),
					ErrorEnum.BOOK_ISBN_EXIST.getCode());
		}
		
		if(book.getAuthors().size() == 0) {
			// It is assume book should have at least 1 author
			throw new BookStoreException(ErrorEnum.BOOK_NO_AUTHOR.getDescription(),
					ErrorEnum.BOOK_NO_AUTHOR.getCode());
		}
		
		bookService.createBookRecord(book);
		return ResponseEntity.ok()
				.body(new ApiResponse(LocalDateTime.now(), "SUCCESS", "Book Record Created Successfully"));
	}

	@PreAuthorize("hasAnyRole('ADMIN','MANAGER')")
	@PostMapping(value = "/update-book-record")
	ResponseEntity<?> updateBookRecord(@RequestBody BookDTO bookdto) {
		Book book = BookMapper.dtoToEntity(bookdto);
		if (bookService.findBookByIsbn(bookdto.getIsbn()) == null) {
			throw new BookStoreException(ErrorEnum.BOOK_ISBN_NOT_EXIST.getDescription(),
					ErrorEnum.BOOK_ISBN_NOT_EXIST.getCode());
		}
		bookService.updateBookRecord(book);
		return ResponseEntity.ok()
				.body(new ApiResponse(LocalDateTime.now(), "SUCCESS", "Book Record Updated Successfully"));
	}

	@PreAuthorize("hasAuthority('ROLE_MANAGER')")
	@GetMapping(value = "/delete-book-record/{isbn}")
	ResponseEntity<?> deleteBookRecord(@PathVariable("isbn") String isbn) {
		if (bookService.findBookByIsbn(isbn) == null) {
			throw new BookStoreException(ErrorEnum.BOOK_ISBN_NOT_EXIST.getDescription(),
					ErrorEnum.BOOK_ISBN_NOT_EXIST.getCode());
		}
		bookService.deleteBookRecord(isbn);
		return ResponseEntity.ok()
				.body(new ApiResponse(LocalDateTime.now(), "SUCCESS", "Book Record Deleted Successfully"));
	}
}
