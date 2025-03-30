package com.fmi.inventory.service;

import com.fmi.inventory.model.BorrowTransaction;
import com.fmi.inventory.model.ClubMember;
import com.fmi.inventory.model.InventoryItem;
import com.fmi.inventory.repository.InventoryItemRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public interface TransactionService {
    /**
     * Records a transaction when an item is borrowed.
     * @param member The club member borrowing the item.
     * @param item The item being borrowed.
     * @param days The number of days the item is borrowed for.
     */
    void borrowItem(ClubMember member, InventoryItem item, int days);

    /**
     * Retrieves all transactions in the system.
     * @return List of all transactions.
     */
    List<BorrowTransaction> getAllTransactions();

    /**
     * Marks a borrowed item as returned.
     * @param transactionId The ID of the transaction being completed.
     * @return true if the return was successful, false otherwise.
     */
    boolean returnItem(Integer transactionId);

    /**
     * Retrieves a list of all overdue transactions.
     * @return List of overdue transactions.
     */
    List<BorrowTransaction> getOverdueTransactions();
}