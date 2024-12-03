package edu.miu.cs544.moe;

import edu.miu.cs544.moe.entity.OnCampus;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class CachedApplication {
    private static final String PERSISTENT_UNIT = "my-pu";

    public static Thread createThreadForCacheTesting(EntityManager em) {
        return new Thread(() -> {
            System.out.println(em.find(OnCampus.class, 7L));
        });
    }

    public static void main(String[] args) throws InterruptedException {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(PERSISTENT_UNIT);
        EntityManager em = emf.createEntityManager();
        Thread[] threads = new Thread[5]; // change numbers of thread here.
        for (int i = 0; i < threads.length; i++) {
            Thread.sleep(100 * i);
            threads[i] = createThreadForCacheTesting(em);
            threads[i].start();
        }
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        // this is expected to be a cached entity
        OnCampus course = em.find(OnCampus.class, 7L);
        int capacity = (int) (Math.random() * 100);
        System.out.println("Updating with random capacity: " + capacity);
        course.setCapacity(capacity);
        tx.commit();
        System.out.println(course);
        System.out.println(em.find(OnCampus.class, 7L));
        em.close();
        emf.close();
    }
}
