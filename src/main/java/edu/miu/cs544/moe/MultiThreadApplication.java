package edu.miu.cs544.moe;

import edu.miu.cs544.moe.entity.Student;
import jakarta.persistence.*;

import java.util.List;

public class MultiThreadApplication {
    private static final String PERSISTENT_UNIT = "my-pu";

    public static Thread createOptimisticLockThread(EntityManager em) {
        return new Thread(() -> {
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

    public static Thread createPessimisticLockThread(EntityManager em) {
        return new Thread(() -> {
            EntityTransaction tx = em.getTransaction();
            tx.begin();
            TypedQuery<Student> namedQuery = em.createNamedQuery("Student.canGraduate", Student.class);
            List<Student> result = namedQuery.getResultList();
            result.forEach(Student::incrementGpa);
            tx.commit();
        });
    }

    public static void main(String[] args) throws InterruptedException {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(PERSISTENT_UNIT);
        EntityManager em = emf.createEntityManager();
        Thread[] threads = new Thread[100]; // change numbers of thread here.
        for (int i = 0; i < threads.length; i++) {
            threads[i] = createOptimisticLockThread(em);
            threads[i].start();
        }
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        em.close();
        emf.close();
    }
}
