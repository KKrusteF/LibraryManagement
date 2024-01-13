package org.krustef.librarymanagement.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
public class BookDTO {
    private Long bookId;
    private String title;
    private String author;
    private String isbn;
    private LocalDate publicationDate;
    private int quantityAvailable;
    private GenreDTO genre;
}
