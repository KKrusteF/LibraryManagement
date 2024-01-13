package org.krustef.librarymanagement.repository;

import org.krustef.librarymanagement.models.BorrowingHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BorrowingHistoryRepository extends JpaRepository<BorrowingHistory, Long> {

}
