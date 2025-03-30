package com.fmi.inventory.repository;

import com.fmi.inventory.model.InventoryItem;

import java.util.List;

public interface InventoryItemRepository extends CrudRepository<InventoryItem, String> {
    List<InventoryItem> getItemsWhereQuantityBelow(int upperBound);
}
