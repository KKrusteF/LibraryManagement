package org.krustef.librarymanagement.service.impl;

import org.krustef.librarymanagement.exception.NotFoundException;
import org.krustef.librarymanagement.models.Book;
import org.krustef.librarymanagement.repository.BookRepository;
import org.krustef.librarymanagement.service.BookService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    @Override
    public List<Book> findAllBooks() {
        return bookRepository.findAll();
    }

    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    @Override
    public List<Book> searchBooks(String keyword) {
        if (keyword != null) {
            if (!bookRepository.findByDescriptionIgnoreCase(keyword).isEmpty()) {
                return bookRepository.findByDescriptionIgnoreCase(keyword);
            }
            if (!bookRepository.findByGenreIgnoreCase(keyword).isEmpty()) {
                return bookRepository.findByGenreIgnoreCase(keyword);
            }
            if (!bookRepository.findByNameIgnoreCase(keyword).isEmpty()) {
                return bookRepository.findByNameIgnoreCase(keyword);
            }
        }
        return bookRepository.findAll();
    }

    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    @Override
    public Book findBookById(Long id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Book not found with ID %d", id)));
    }

    @Override
    public void createBook(Book book) {
        bookRepository.save(book);
    }

    @Override
    public void updateBook(Book book) {
        bookRepository.save(book);
    }

    @Override
    public void deleteBook(Long id) {
        final Book book = bookRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Book not found with ID %d", id)));

        bookRepository.deleteById(book.getId());
    }
}
