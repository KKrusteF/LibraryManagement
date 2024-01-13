package org.krustef.librarymanagement.service;

import org.krustef.librarymanagement.dto.BookDTO;
import org.krustef.librarymanagement.dto.BorrowingHistoryDTO;
import org.krustef.librarymanagement.dto.GenreDTO;
import org.krustef.librarymanagement.dto.UserDTO;
import org.krustef.librarymanagement.models.Book;
import org.krustef.librarymanagement.models.BorrowingHistory;
import org.krustef.librarymanagement.models.Genre;
import org.krustef.librarymanagement.models.User;
import org.krustef.librarymanagement.repository.BorrowingHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BorrowingHistoryService {

    private final BorrowingHistoryRepository borrowingHistoryRepository;

    @Autowired
    public BorrowingHistoryService(BorrowingHistoryRepository borrowingHistoryRepository) {
        this.borrowingHistoryRepository = borrowingHistoryRepository;
    }

    public List<BorrowingHistoryDTO> getAllBorrowingHistoriesDTO() {
        return borrowingHistoryRepository.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public BorrowingHistoryDTO getBorrowingHistoryDTOById(Long borrowId) {
        BorrowingHistory borrowingHistory = borrowingHistoryRepository.findById(borrowId)
                .orElse(null);
        return (borrowingHistory != null) ? mapToDTO(borrowingHistory) : null;
    }

    public BorrowingHistoryDTO saveBorrowingHistoryDTO(BorrowingHistoryDTO borrowingHistoryDTO) {
        BorrowingHistory borrowingHistory = mapToEntity(borrowingHistoryDTO);
        borrowingHistory = borrowingHistoryRepository.save(borrowingHistory);
        return mapToDTO(borrowingHistory);
    }

    public void deleteBorrowingHistory(Long borrowId) {
        borrowingHistoryRepository.deleteById(borrowId);
    }

    private BorrowingHistoryDTO mapToDTO(BorrowingHistory borrowingHistory) {
        return new BorrowingHistoryDTO(
                borrowingHistory.getBorrowId(),
                mapToDTO(borrowingHistory.getBook()),
                mapToDTO(borrowingHistory.getUser()),
                borrowingHistory.getBorrowDate(),
                borrowingHistory.getReturnDate()
        );
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

    private UserDTO mapToDTO(User user) {
        return new UserDTO(
                user.getUserId(),
                user.getUsername(),
                user.getEmail(),
                user.getRoles()
        );
    }

    private GenreDTO mapToDTO(Genre genre) {
        return new GenreDTO(
                genre.getGenreId(),
                genre.getGenreName()
        );
    }

    private BorrowingHistory mapToEntity(BorrowingHistoryDTO borrowingHistoryDTO) {
        BorrowingHistory borrowingHistory = new BorrowingHistory();
        borrowingHistory.setBorrowId(borrowingHistoryDTO.getBorrowId());
        borrowingHistory.setBook(mapToEntity(borrowingHistoryDTO.getBook()));
        borrowingHistory.setUser(mapToEntity(borrowingHistoryDTO.getUser()));
        borrowingHistory.setBorrowDate(borrowingHistoryDTO.getBorrowDate());
        borrowingHistory.setReturnDate(borrowingHistoryDTO.getReturnDate());
        return borrowingHistory;
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

    private User mapToEntity(UserDTO userDTO) {
        User user = new User();
        user.setUserId(userDTO.getUserId());
        user.setUsername(userDTO.getUsername());
        user.setEmail(userDTO.getEmail());
        user.setRoles(userDTO.getRoles());
        return user;
    }

    private Genre mapToEntity(GenreDTO genreDTO) {
        Genre genre = new Genre();
        genre.setGenreId(genreDTO.getGenreId());
        genre.setGenreName(genreDTO.getGenreName());
        return genre;
    }
}
