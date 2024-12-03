package edu.miu.cs544.moe.service;

import java.util.Collection;

public interface Service<T> {
    T save(T t);
    Collection<T> findAll();
    T findOne(Long id);
    T update(T t);
    void delete(Long id);
}
