package com.fmi.inventory.repository.inmemory;

import com.fmi.inventory.model.BorrowTransaction;
import com.fmi.inventory.repository.BorrowTransactionRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class InMemoryBorrowTransactionRepository extends InMemoryCrudRepository<BorrowTransaction>
    implements BorrowTransactionRepository {

    public List<BorrowTransaction> getTransactionWhereIsReturnedAndDueDateIsBefore(
        boolean isReturned, LocalDateTime dueDate
    ) {
        return storage.values().stream()
            .filter(tr -> tr.isReturned() == isReturned && tr.getDueDate().isBefore(dueDate))
            .collect(Collectors.toList());
    }
}
