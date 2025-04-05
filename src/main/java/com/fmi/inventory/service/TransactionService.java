package com.fmi.inventory.service;

import com.fmi.inventory.exceptions.OperationNotPermittedException;
import com.fmi.inventory.model.BorrowTransaction;
import com.fmi.inventory.model.ClubMember;
import com.fmi.inventory.model.InventoryItem;
import com.fmi.inventory.repository.BorrowTransactionRepository;
import com.fmi.inventory.repository.ClubMemberRepository;
import com.fmi.inventory.repository.InventoryItemRepository;

import java.time.LocalDateTime;
import java.util.List;

public class TransactionService {
    private final InventoryItemRepository itemRepository;
    private final BorrowTransactionRepository transactionRepository;
    private final ClubMemberRepository memberRepository;

    public TransactionService(InventoryItemRepository itemRepository,
                              BorrowTransactionRepository borrowTransactionRepository,
                              ClubMemberRepository memberRepository) {
        this.itemRepository = itemRepository;
        this.transactionRepository = borrowTransactionRepository;
        this.memberRepository = memberRepository;
    }

    /**
     * Records a transaction when an item is borrowed.
     * @param memberId The club member id borrowing the item.
     * @param inventoryItemId The item being borrowed.
     * @param days The number of days the item is borrowed for.
     */
    void borrowItem(String memberId, String inventoryItemId, int days) {
        ClubMember clubMember = memberRepository.findByIdOrThrow(memberId);
        InventoryItem inventoryItem = itemRepository.findByIdOrThrow(inventoryItemId);
        if (!inventoryItem.isBorrowable()) {
            throw new OperationNotPermittedException("Item with id " + inventoryItemId + " is not borrowable!");
        }
        if (inventoryItem.getQuantity() == 0) {
            throw new OperationNotPermittedException("Item with id " + inventoryItemId + " is not stock!");
        }
        BorrowTransaction newBorrowTransaction = new BorrowTransaction(clubMember, inventoryItem, days);
        transactionRepository.save(newBorrowTransaction);
    }

    /**
     * Retrieves all transactions in the system.
     * @return List of all transactions.
     */
    List<BorrowTransaction> getAllTransactions() {
        return transactionRepository.findAll();
    }

    /**
     * Marks a borrowed item as returned.
     * @param transactionId The ID of the transaction being completed.
     * @return true if the return was successful, false otherwise.
     */
    boolean returnItem(String transactionId) {
        BorrowTransaction transaction = transactionRepository.findByIdOrThrow(transactionId);
        if (transaction.isReturned()) {
            return false;
        }
        transaction.setReturned(true);
        transactionRepository.save(transaction);
        return true;
    }

    /**
     * Retrieves a list of all overdue transactions.
     * @return List of overdue transactions.
     */
    List<BorrowTransaction> getOverdueTransactions() {
        return transactionRepository.getTransactionWhereIsReturnedAndDueDateIsBefore(false, LocalDateTime.now());
    }
}