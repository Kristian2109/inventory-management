package com.fmi.inventory.dto;

public record UpdateItemDto(
    String name,
    String description,
    int quantity,
    String category,
    boolean borrowable
) {}