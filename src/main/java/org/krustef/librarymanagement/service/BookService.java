package org.krustef.librarymanagement.service;

import org.krustef.librarymanagement.models.Author;
import org.krustef.librarymanagement.models.Book;
import org.krustef.librarymanagement.models.Genre;
import org.krustef.librarymanagement.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    private final BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Book getBookById(Long id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found"));
    }

    public Book saveBook(Book book) {
        return bookRepository.save(book);
    }

    public Book updateBook(Long id, Book updatedBook) {
        return bookRepository.findById(id)
                .map(book -> {
                    book.setTitle(updatedBook.getTitle());
                    book.setDescription(updatedBook.getDescription());
                    book.setPublicationYear(updatedBook.getPublicationYear());
                    book.setGenres(updatedBook.getGenres());
                    book.setAuthors(updatedBook.getAuthors());
                    return bookRepository.save(book);
                })
                .orElseThrow(() -> new RuntimeException("Book not found"));
    }

    public void deleteBook(Long id) {
        if (bookRepository.existsById(id)) {
            bookRepository.deleteById(id);
        } else {
            throw new RuntimeException("Book not found");
        }
    }

    public List<Book> findBooksByAuthor(Author author) {
        return bookRepository.findByAuthors(author);
    }

    public List<Book> findBooksByGenre(Genre genre) {
        return bookRepository.findByGenre(genre);
    }
}
