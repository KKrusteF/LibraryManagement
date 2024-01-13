package org.krustef.librarymanagement.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
public class AuthorDTO {
    private Long authorId;
    private String authorName;
    private LocalDate birthDate;
    private String nationality;
}
