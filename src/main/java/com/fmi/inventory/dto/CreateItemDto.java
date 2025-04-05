package com.fmi.inventory.dto;

public record CreateItemDto(
    String name,
    String description,
    int quantity,
    String serialNumber,
    String unit,
    String category,
    boolean borrowable
) {}