package com.fmi.inventory.controller;

import com.fmi.inventory.dto.CreateBorrowTransactionDto;
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
    public ResponseEntity<String> borrowItem(@RequestBody CreateBorrowTransactionDto request) {
        service.borrowItem(request.memberId(), request.inventoryItemId(), request.days());
        return ResponseEntity.ok("Item borrowed successfully.");
    }

    @PostMapping("/{id}/return")
    public ResponseEntity<String> returnItem(@PathVariable String id) {
        boolean success = service.returnItem(id);
        if (success) {
            return ResponseEntity.ok("Item returned successfully.");
        } else {
            return ResponseEntity.badRequest().body("Item has already been returned.");
        }
    }

    @GetMapping
    public ResponseEntity<List<BorrowTransaction>> getAllTransactions() {
        return ResponseEntity.ok(service.getAllTransactions());
    }

    @GetMapping("/overdue")
    public ResponseEntity<List<BorrowTransaction>> getOverdueTransactions() {
        return ResponseEntity.ok(service.getOverdueTransactions());
    }
}
