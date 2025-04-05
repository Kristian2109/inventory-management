package com.fmi.inventory.model;

public interface Identifiable<ID> {
    ID getId();
    void setId(ID id);
}