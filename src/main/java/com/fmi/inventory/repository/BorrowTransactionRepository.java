package com.fmi.inventory.repository;

import com.fmi.inventory.model.BorrowTransaction;

import java.time.LocalDateTime;
import java.util.List;

public interface BorrowTransactionRepository extends CrudRepository<BorrowTransaction, String> {
    List<BorrowTransaction> getTransactionWhereIsReturnedAndDueDateIsBefore(boolean isReturned, LocalDateTime dueDate);
}
