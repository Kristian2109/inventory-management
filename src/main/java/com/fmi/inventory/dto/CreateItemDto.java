package com.fmi.inventory.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;


public record CreateItemDto(
    @NotBlank String name,
    @NotBlank String description,
    @Min(0) int quantity,
    @NotBlank String serialNumber,
    @NotBlank String unit,
    @NotBlank String category,
    boolean borrowable
) {}