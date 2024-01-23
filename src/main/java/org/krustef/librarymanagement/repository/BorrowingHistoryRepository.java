package org.krustef.librarymanagement.repository;

import org.krustef.librarymanagement.models.Book;
import org.krustef.librarymanagement.models.BorrowingHistory;
import org.krustef.librarymanagement.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BorrowingHistoryRepository extends JpaRepository<BorrowingHistory, Long> {
    List<BorrowingHistory> findByUser(User user);
    List<BorrowingHistory> findByBook(Book book);
    List<BorrowingHistory> findByUserAndBook(User user, Book book);
}
