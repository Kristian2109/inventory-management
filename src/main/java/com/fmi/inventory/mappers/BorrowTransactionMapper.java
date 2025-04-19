package com.fmi.inventory.mappers;

import com.fmi.inventory.dto.BorrowTransactionDto;
import com.fmi.inventory.model.BorrowTransaction;
import com.fmi.inventory.model.ClubMember;
import com.fmi.inventory.model.InventoryItem;

public class BorrowTransactionMapper {

    public static BorrowTransactionDto toDto(BorrowTransaction transaction) {
        return new BorrowTransactionDto(
            transaction.getId(),
            transaction.getMember().getId(),
            transaction.getItem().getId(),
            transaction.getBorrowedDate(),
            transaction.getDueDate(),
            transaction.isReturned()
        );
    }

    public static BorrowTransaction fromDto(
        BorrowTransactionDto dto,
        ClubMember member,
        InventoryItem item
    ) {
        BorrowTransaction transaction = new BorrowTransaction(
            dto.id(),
            member,
            item,
            (int) java.time.Duration.between(dto.borrowedDate(), dto.dueDate()).toDays()
        );
        transaction.setReturned(dto.returned());
        return transaction;
    }
}