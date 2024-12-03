package edu.miu.cs544.moe.service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

import java.util.Collection;
import java.util.List;

public abstract class AbstractService<T> implements Service<T>, AutoCloseable{
    protected final EntityManagerFactory entityManagerFactory;
    protected final EntityManager entityManager;
    protected final Class<T> entityClass;
    protected EntityTransaction transaction;

    public AbstractService(String persistenceUnitName, Class<T> entityClass) {
        this.entityClass = entityClass;
        this.entityManagerFactory = Persistence.createEntityManagerFactory(persistenceUnitName);
        this.entityManager = entityManagerFactory.createEntityManager();
//        this.entityManager.setProperty("javax.persistence.sharedCache.mode", "DISABLE_SELECTIVE");
    }

    protected void begin() {
        transaction = entityManager.getTransaction();
        transaction.begin();
    }

    protected void end() {
        transaction.commit();
    }

    @Override
    public void close() throws Exception {
        entityManager.close();
        entityManagerFactory.close();
    }

    @Override
    public T save(T t) {
        this.begin();
        this.entityManager.persist(t);
        this.end();
        return t;
    }

    @Override
    public Collection<T> findAll() {
        return this.entityManager.createQuery("SELECT t FROM " + entityClass.getSimpleName() + " t", entityClass).getResultList();
    }

    @Override
    public T findOne(Long id) {
        return this.entityManager.find(entityClass, id);
    }

    @Override
    public T update(T t) {
        this.begin();
        T updated = this.entityManager.merge(t);
        this.end();
        return updated;
    }

    @Override
    public void delete(Long id) {
        this.begin();
        T t = this.entityManager.find(entityClass, id);
        this.entityManager.remove(t);
        this.end();
    }
}
