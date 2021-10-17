package tech.savvy.bookstore.mapper;


import java.util.ArrayList;
import java.util.List;

import tech.savvy.bookstore.dto.AuthorDTO;
import tech.savvy.bookstore.dto.BookDTO;
import tech.savvy.bookstore.model.Author;
import tech.savvy.bookstore.model.Book;

public class BookMapper {

	public static List<BookDTO> entityToDTOs(List<Book> books){
		List<BookDTO> bookdtoList = new ArrayList<BookDTO>();
		for(Book book : books) {
			bookdtoList.add(entityToDTO(book));
		}
		return bookdtoList;
	}
	
	public static BookDTO entityToDTO(Book book) {
        BookDTO bookdto = new BookDTO();
        bookdto.setIsbn(book.getIsbn());
        bookdto.setTitle(book.getTitle());
        bookdto.setYear(book.getYear());
        bookdto.setPrice(book.getPrice());
        bookdto.setGenre(book.getGenre());
        // Map authors
        for(Author author : book.getAuthors()) {
        	AuthorDTO authorDTO = new AuthorDTO();
        	authorDTO.setName(author.getName());
        	authorDTO.setBirthday(author.getBirthday());
        	bookdto.addAuthors(authorDTO);
        }
        return bookdto;
    }
	
    public static Book dtoToEntity(BookDTO bookdto) {
        Book book = new Book();
        book.setIsbn(bookdto.getIsbn());
        book.setTitle(bookdto.getTitle());
        book.setYear(bookdto.getYear());
        book.setPrice(bookdto.getYear());
        book.setGenre(bookdto.getGenre());
        // Map authors
        for(AuthorDTO authordto : bookdto.getAuthors()) {
        	Author author = new Author();
        	author.setName(authordto.getName());
        	author.setBirthday(authordto.getBirthday());
        	book.addAuthors(author);
        	// Don't forget to set this
        	author.setBook(book);
        }
        return book;
    }
}
