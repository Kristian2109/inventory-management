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
    public T save(T entity) {
        if (entity == null) {
            throw new IllegalArgumentException("Entity or ID cannot be null");
        }
        String id = createNewId();
        entity.setId(id);
        storage.put(id, entity);
        return entity;
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

    @Override
    public T findByIdOrThrow(String id) {
        T entity = storage.get(id);
        if (entity == null) {
            throw new IllegalArgumentException("No entity with such id: " + id);
        }
        return entity;
    }

    private String createNewId() {
        return UUID.randomUUID().toString();
    }
}
