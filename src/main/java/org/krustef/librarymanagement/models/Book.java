package org.krustef.librarymanagement.models;

import jakarta.persistence.*;
import lombok.*;
import org.krustef.librarymanagement.dto.BookDTO;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_id")
    private Long bookId;

    @Column(name = "title")
    private String title;

    @Column(name = "author")
    private String author;

    @Column(name = "isbn")
    private String isbn;

    @Column(name = "publication_date")
    private LocalDate publicationDate;

    @Column(name = "quantity_available")
    private int quantityAvailable;

    @ManyToOne
    @JoinColumn(name = "genre_id", nullable = false)
    private Genre genre;

    public BookDTO toDTO() {
        return new BookDTO(bookId, title, author, isbn, publicationDate, quantityAvailable, genre.toDTO());
    }
}

