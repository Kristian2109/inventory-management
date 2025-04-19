package com.fmi.inventory.controller;

import com.fmi.inventory.dto.BorrowTransactionDto;
import com.fmi.inventory.dto.CreateBorrowTransactionDto;
import com.fmi.inventory.dto.ReturnItemDto;
import com.fmi.inventory.model.BorrowTransaction;
import com.fmi.inventory.service.BorrowTransactionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transactions")
public class BorrowTransactionController {
    private final BorrowTransactionService service;

    public BorrowTransactionController(BorrowTransactionService service) {
        this.service = service;
    }

    @PostMapping("/borrow")
    public ResponseEntity<BorrowTransactionDto> borrowItem(@RequestBody CreateBorrowTransactionDto request) {
        BorrowTransactionDto borrowTransaction = service.borrowItem(
            request.memberId(), request.inventoryItemId(), request.days()
        );

        return ResponseEntity.ok(borrowTransaction);
    }

    @PostMapping("/{id}/return")
    public ResponseEntity<ReturnItemDto> returnItem(@PathVariable String id) {
        boolean success = service.returnItem(id);
        return ResponseEntity.ok(new ReturnItemDto(success));
    }

    @GetMapping
    public ResponseEntity<List<BorrowTransactionDto>> getAllTransactions() {
        return ResponseEntity.ok(service.getAllTransactions());
    }

    @GetMapping("/overdue")
    public ResponseEntity<List<BorrowTransaction>> getOverdueTransactions() {
        return ResponseEntity.ok(service.getOverdueTransactions());
    }
}
