package com.fmi.inventory.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public record CreateBorrowTransactionDto(
    @NotBlank  String memberId,
    @NotBlank String inventoryItemId,
    @Min(0) int days
) { }
