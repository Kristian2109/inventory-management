package com.fmi.inventory.repository.inmemory;

import com.fmi.inventory.model.Identifiable;
import com.fmi.inventory.repository.CrudRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

public class InMemoryCrudRepository<T extends Identifiable<String>> implements CrudRepository<T, String> {
    protected final Map<String, T> storage = new HashMap<>();

    @Override
    public void save(T entity) {
        if (entity == null) {
            throw new IllegalArgumentException("Entity or ID cannot be null");
        }
        storage.put(createNewId(), entity);
    }

    @Override
    public Optional<T> findById(String id) {
        return Optional.ofNullable(storage.get(id));
    }

    @Override
    public List<T> findAll() {
        return new ArrayList<>(storage.values());
    }

    @Override
    public void update(T entity) {
        if (entity == null || entity.getId() == null || !storage.containsKey(entity.getId())) {
            throw new IllegalArgumentException("Entity must exist to be updated");
        }
        storage.put(entity.getId(), entity);
    }

    @Override
    public void deleteById(String id) {
        storage.remove(id);
    }

    private String createNewId() {
        return UUID.randomUUID().toString();
    }
}
