package edu.miu.cs544.moe.configs;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

public class EntityManagerWrapper implements AutoCloseable {
    private final EntityManagerFactory entityManagerFactory;
    private final EntityManager entityManager;

    public EntityManagerWrapper(String persistenceUnitName) {
        this.entityManagerFactory = jakarta.persistence.Persistence.createEntityManagerFactory(persistenceUnitName);
        this.entityManager = entityManagerFactory.createEntityManager();
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }

    @Override
    public void close() {
        entityManager.close();
        entityManagerFactory.close();
    }

    public void begin() {
        entityManager.getTransaction().begin();
    }

    public void end() {
        entityManager.getTransaction().commit();
    }
}
