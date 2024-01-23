package org.krustef.librarymanagement.service;

import org.krustef.librarymanagement.models.Book;
import org.krustef.librarymanagement.models.BorrowingHistory;
import org.krustef.librarymanagement.models.User;
import org.krustef.librarymanagement.repository.BorrowingHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BorrowingHistoryService {
    private final BorrowingHistoryRepository borrowingHistoryRepository;

    @Autowired
    public BorrowingHistoryService(BorrowingHistoryRepository borrowingHistoryRepository) {
        this.borrowingHistoryRepository = borrowingHistoryRepository;
    }

    public List<BorrowingHistory> getAllBorrowingHistories() {
        return borrowingHistoryRepository.findAll();
    }

    public BorrowingHistory getBorrowingHistoryById(Long id) {
        return borrowingHistoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Borrowing history not found"));
    }

    public BorrowingHistory saveBorrowingHistory(BorrowingHistory borrowingHistory) {
        return borrowingHistoryRepository.save(borrowingHistory);
    }

    public BorrowingHistory updateBorrowingHistory(Long id, BorrowingHistory updatedBorrowingHistory) {
        return borrowingHistoryRepository.findById(id)
                .map(borrowingHistory -> {
                    borrowingHistory.setUser(updatedBorrowingHistory.getUser());
                    borrowingHistory.setBook(updatedBorrowingHistory.getBook());
                    borrowingHistory.setBorrowedDate(updatedBorrowingHistory.getBorrowedDate());
                    borrowingHistory.setReturnedDate(updatedBorrowingHistory.getReturnedDate());
                    return borrowingHistoryRepository.save(borrowingHistory);
                })
                .orElseThrow(() -> new RuntimeException("Borrowing history not found"));
    }

    public void deleteBorrowingHistory(Long id) {
        if (borrowingHistoryRepository.existsById(id)) {
            borrowingHistoryRepository.deleteById(id);
        } else {
            throw new RuntimeException("Borrowing history not found");
        }
    }

    public List<BorrowingHistory> getByUser(User user) {
        return borrowingHistoryRepository.findByUser(user);
    }

    public List<BorrowingHistory> getByBook(Book book) {
        return borrowingHistoryRepository.findByBook(book);
    }

    public List<BorrowingHistory> getByUserAndBook(User user, Book book) {
        return borrowingHistoryRepository.findByUserAndBook(user, book);
    }
}