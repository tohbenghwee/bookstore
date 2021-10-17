package tech.savvy.bookstore.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import tech.savvy.bookstore.model.Book;

public interface BookRepository extends JpaRepository<Book, String> {

	@Query("SELECT book FROM Book book where book.isbn = (:pIsbn) ")
	Book findBookByIsbn( @Param("pIsbn") String isbn);
	
	@Query("SELECT book FROM Book book join book.authors au where book.title = (:pTitle) or au.name = (:pName) ")
	List<Book> findBookByTitleOrAuthor( @Param("pTitle") String title, @Param("pName") String name);

	@Query("SELECT book FROM Book book join book.authors au where book.title = (:pTitle) and au.name = (:pName) ")
	List<Book> findBookByTitleAndAuthor( @Param("pTitle") String title, @Param("pName") String name);
	
	@Modifying
	@Transactional
	@Query("DELETE FROM Book book where book.isbn = ?1")
    void deleteBookByIsbn(String isbn);
	
}

