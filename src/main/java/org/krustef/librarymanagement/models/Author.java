package org.krustef.librarymanagement.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.krustef.librarymanagement.dto.AuthorDTO;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "authors")
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "author_id")
    private Long authorId;

    @Column(name = "author_name")
    private String authorName;

    @Column(name = "birth_date")
    private LocalDate birthDate;

    @Column(name = "nationality")
    private String nationality;

    public AuthorDTO toDTO() {
        return new AuthorDTO(authorId, authorName, birthDate, nationality);
    }
}
