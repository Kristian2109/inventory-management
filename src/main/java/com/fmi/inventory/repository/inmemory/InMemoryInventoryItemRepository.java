package com.fmi.inventory.repository.inmemory;

import com.fmi.inventory.model.InventoryItem;
import com.fmi.inventory.repository.InventoryItemRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class InMemoryInventoryItemRepository extends InMemoryCrudRepository<InventoryItem>
    implements InventoryItemRepository {

    @Override
    public List<InventoryItem> getItemsWhereQuantityBelow(int upperBound) {
        return storage.values().stream()
            .filter(item -> item.getQuantity() < upperBound)
            .toList();
    }
}