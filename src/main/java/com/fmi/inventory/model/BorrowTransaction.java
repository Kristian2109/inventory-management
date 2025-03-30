package com.fmi.inventory.model;

import java.time.LocalDateTime;

public class BorrowTransaction implements Identifiable<String> {
    private String id;
    private final ClubMember member;
    private final InventoryItem item;
    private final LocalDateTime borrowedDate;
    private final LocalDateTime dueDate;
    private boolean returned;

    public BorrowTransaction(String id, ClubMember member, InventoryItem item, int days) {
        this.id = id;
        this.member = member;
        this.item = item;
        this.borrowedDate = LocalDateTime.now();
        this.dueDate = borrowedDate.plusDays(days);
        this.returned = false;
    }

    public BorrowTransaction( ClubMember member, InventoryItem item, int days) {
        this.member = member;
        this.item = item;
        this.borrowedDate = LocalDateTime.now();
        this.dueDate = borrowedDate.plusDays(days);
        this.returned = false;
    }

    public String getId() {
        return id;
    }

    public ClubMember getMember() {
        return member;
    }

    public InventoryItem getItem() {
        return item;
    }

    public LocalDateTime getBorrowedDate() {
        return borrowedDate;
    }

    public LocalDateTime getDueDate() {
        return dueDate;
    }

    public boolean isReturned() {
        return returned;
    }

    public void setReturned(boolean returned) {
        this.returned = returned;
    }
}