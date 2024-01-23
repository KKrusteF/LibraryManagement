package org.krustef.librarymanagement.controller;

import org.krustef.librarymanagement.service.BorrowingHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class BorrowingHistoryController {
    private final BorrowingHistoryService borrowingHistoryService;

    @Autowired
    public BorrowingHistoryController(BorrowingHistoryService borrowingHistoryService) {
        this.borrowingHistoryService = borrowingHistoryService;
    }
}
