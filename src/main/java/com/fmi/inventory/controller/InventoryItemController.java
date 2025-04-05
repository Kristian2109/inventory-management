package com.fmi.inventory.controller;

import com.fmi.inventory.dto.CreateItemDto;
import com.fmi.inventory.dto.UpdateItemDto;
import com.fmi.inventory.model.InventoryItem;
import com.fmi.inventory.service.InventoryService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/items")
public class InventoryItemController {

    private final InventoryService inventoryService;

    public InventoryItemController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @GetMapping
    public ResponseEntity<List<InventoryItem>> getAllItems() {
        return ResponseEntity.ok(inventoryService.getAllItems());
    }

    @PostMapping
    public ResponseEntity<InventoryItem> addItem(@RequestBody @Valid CreateItemDto item) {
        InventoryItem createdItem = inventoryService.addItem(
            item.name(),
            item.description(),
            item.quantity(),
            item.serialNumber(),
            item.unit(),
            item.category(),
            item.borrowable()
        );
        return new ResponseEntity<>(createdItem, HttpStatus.CREATED);
    }

    @GetMapping("/low-stock")
    public ResponseEntity<List<InventoryItem>> getLowStockItems(@RequestParam int threshold) {
        return ResponseEntity.ok(inventoryService.getLowStockItems(threshold));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateItem(@PathVariable String id, @RequestBody @Valid UpdateItemDto item) {
        boolean updated = inventoryService.updateItem(
            id, item.name(), item.description(), item.quantity(), item.category(), item.borrowable()
        );

        if (updated) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
