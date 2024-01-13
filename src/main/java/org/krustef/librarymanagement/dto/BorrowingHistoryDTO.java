package org.krustef.librarymanagement.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
public class BorrowingHistoryDTO {
    private Long borrowId;
    private BookDTO book;
    private UserDTO user;
    private LocalDate borrowDate;
    private LocalDate returnDate;
}
