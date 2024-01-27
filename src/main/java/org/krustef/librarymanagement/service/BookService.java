package org.krustef.librarymanagement.service;

import org.krustef.librarymanagement.models.Book;

import java.util.List;

public interface BookService {

	List<Book> findAllBooks();
	
	List<Book> searchBooks(String keyword);

	Book findBookById(Long id);

	void createBook(Book book);

	void updateBook(Book book);

	void deleteBook(Long id);
}
