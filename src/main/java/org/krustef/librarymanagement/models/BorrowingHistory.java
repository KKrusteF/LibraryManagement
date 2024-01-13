package org.krustef.librarymanagement.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.krustef.librarymanagement.dto.BorrowingHistoryDTO;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "borrowing_history")
public class BorrowingHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "borrow_id")
    private Long borrowId;

    @ManyToOne
    @JoinColumn(name = "book_id", nullable = false)
    private Book book;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "borrow_date")
    private LocalDate borrowDate;

    @Column(name = "return_date")
    private LocalDate returnDate;

    public BorrowingHistoryDTO toDTO() {
        return new BorrowingHistoryDTO(borrowId, book.toDTO(), user.toDTO(), borrowDate, returnDate);
    }
}
