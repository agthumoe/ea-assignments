package edu.miu.cs544.moe;

import edu.miu.cs544.moe.entity.OnCampus;
import edu.miu.cs544.moe.entity.Student;
import edu.miu.cs544.moe.service.DistanceEducationService;
import edu.miu.cs544.moe.service.OnCampusService;
import edu.miu.cs544.moe.service.StudentService;
import jakarta.persistence.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MultiThreadApplication {
    private static final String PERSISTENT_UNIT = "my-pu";

    public static Thread createOptimisticLockThread(EntityManagerFactory emf) {
        return new Thread(() -> {
            EntityManager em = emf.createEntityManager();
            EntityTransaction tx = em.getTransaction();
            tx.begin();
            String jpql = "SELECT s FROM Student s WHERE s.name = :name";
            Student student = em.createQuery(jpql, Student.class)
                    .setParameter("name", "Alice")
                    .getSingleResult();
            student.incrementGpa();
            tx.commit();
        });
    }

    public static Thread createPessimisticLockThread(EntityManagerFactory emf) {
        return new Thread(() -> {
            EntityManager em = emf.createEntityManager();
            EntityTransaction tx = em.getTransaction();
            tx.begin();
            TypedQuery<Student> namedQuery = em.createNamedQuery("Student.canGraduate", Student.class);
            List<Student> result = namedQuery.getResultList();
            result.forEach(Student::incrementGpa);
            tx.commit();
        });
    }

    public static Thread createThreadForCacheTesting(EntityManager em) {
        em.setProperty("javax.persistence.sharedCache.mode", "DISABLE_SELECTIVE");
//        em.setProperty("javax.persistence.cache.retrieveMode", CacheRetrieveMode.USE);
        return new Thread(() -> {
            em.find(OnCampus.class, 7L);
            em.find(OnCampus.class, 7L);
        });
    }

    public static void main(String[] args) {
        System.out.println("Testing concurrency");
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(PERSISTENT_UNIT);
        Thread[] threads = new Thread[3]; // change numbers of thread here.
        for (int i = 0; i < threads.length; i++) {
            System.out.println("Thread " + i);
            threads[i] = createThreadForCacheTesting(emf.createEntityManager());
            threads[i].start();
        }
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        emf.close();
    }
}
