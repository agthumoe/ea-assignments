package edu.miu.cs544.moe.service;

import edu.miu.cs544.moe.configs.EntityManagerWrapper;

import java.util.Collection;

public abstract class AbstractService<T> implements Service<T>{
    protected EntityManagerWrapper entityManagerWrapper;
    protected Class<T> entityClass;

    public AbstractService(EntityManagerWrapper entityManagerWrapper, Class<T> entityClass) {
        this.entityManagerWrapper = entityManagerWrapper;
        this.entityClass = entityClass;
    }

    @Override
    public T save(T t) {
        this.entityManagerWrapper.begin();
        this.entityManagerWrapper.getEntityManager().persist(t);
        this.entityManagerWrapper.end();
        return t;
    }

    @Override
    public Collection<T> findAll() {
        return this.entityManagerWrapper.getEntityManager().createQuery("SELECT t FROM " + entityClass.getSimpleName() + " t", entityClass).getResultList();
    }

    @Override
    public T findOne(Long id) {
        return this.entityManagerWrapper.getEntityManager().find(entityClass, id);
    }

    @Override
    public T update(T t) {
        this.entityManagerWrapper.begin();
        T updated = this.entityManagerWrapper.getEntityManager().merge(t);
        this.entityManagerWrapper.end();
        return updated;
    }

    @Override
    public void delete(Long id) {
        this.entityManagerWrapper.begin();
        T t = this.entityManagerWrapper.getEntityManager().find(entityClass, id);
        this.entityManagerWrapper.getEntityManager().remove(t);
        this.entityManagerWrapper.end();
    }
}
