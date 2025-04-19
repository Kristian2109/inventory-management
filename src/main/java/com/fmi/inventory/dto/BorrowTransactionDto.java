package com.fmi.inventory.dto;

import java.time.LocalDateTime;

public record BorrowTransactionDto(
    String id,
    String memberId,
    String itemId,
    LocalDateTime borrowedDate,
    LocalDateTime dueDate,
    boolean returned
) { }
