package org.krustef.librarymanagement.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class GenreDTO {
    private Long genreId;
    private String genreName;
}
