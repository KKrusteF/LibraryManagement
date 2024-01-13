package org.krustef.librarymanagement.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.krustef.librarymanagement.dto.GenreDTO;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "genres")
public class Genre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "genre_id")
    private Long genreId;

    @Column(name = "genre_name")
    private String genreName;

    public GenreDTO toDTO() {
        return new GenreDTO(genreId, genreName);
    }
}
