package com.fmi.inventory.repository;

import com.fmi.inventory.model.Identifiable;

import java.util.List;
import java.util.Optional;

public interface CrudRepository<T extends Identifiable<ID>, ID> {
    void save(T entity);
    Optional<T> findById(ID id);
    List<T> findAll();
    void update(T entity);
    void deleteById(ID id);
}
