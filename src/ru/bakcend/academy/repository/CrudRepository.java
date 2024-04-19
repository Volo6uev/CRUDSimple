package ru.bakcend.academy.repository;

import java.util.List;

public interface CrudRepository<ID, T> {
    List<T> findAll();
    void save(T entity);
    void updateById(T entity);
    void deleteById(ID id);
}
