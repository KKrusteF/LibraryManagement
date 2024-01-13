package org.krustef.librarymanagement.service;

import org.krustef.librarymanagement.dto.BookDTO;
import org.krustef.librarymanagement.dto.GenreDTO;
import org.krustef.librarymanagement.models.Book;
import org.krustef.librarymanagement.models.Genre;
import org.krustef.librarymanagement.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookService {

    private final BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<BookDTO> getAllBooksDTO() {
        return bookRepository.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public BookDTO getBookDTOById(Long bookId) {
        Book book = bookRepository.findById(bookId).orElse(null);
        return (book != null) ? mapToDTO(book) : null;
    }

    public BookDTO saveBookDTO(BookDTO bookDTO) {
        Book book = mapToEntity(bookDTO);
        return mapToDTO(bookRepository.save(book));
    }

    public void deleteBookById(Long bookId) {
        bookRepository.deleteById(bookId);
    }

    private BookDTO mapToDTO(Book book) {
        return new BookDTO(
                book.getBookId(),
                book.getTitle(),
                book.getAuthor(),
                book.getIsbn(),
                book.getPublicationDate(),
                book.getQuantityAvailable(),
                mapToDTO(book.getGenre())
        );
    }

    private GenreDTO mapToDTO(Genre genre) {
        return new GenreDTO(genre.getGenreId(), genre.getGenreName());
    }

    private Book mapToEntity(BookDTO bookDTO) {
        Book book = new Book();
        book.setBookId(bookDTO.getBookId());
        book.setTitle(bookDTO.getTitle());
        book.setAuthor(bookDTO.getAuthor());
        book.setIsbn(bookDTO.getIsbn());
        book.setPublicationDate(bookDTO.getPublicationDate());
        book.setQuantityAvailable(bookDTO.getQuantityAvailable());
        book.setGenre(mapToEntity(bookDTO.getGenre()));
        return book;
    }

    private Genre mapToEntity(GenreDTO genreDTO) {
        Genre genre = new Genre();
        genre.setGenreId(genreDTO.getGenreId());
        genre.setGenreName(genreDTO.getGenreName());
        return genre;
    }
}
