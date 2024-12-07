package edu.miu.cs544.moe.service;

import edu.miu.cs544.moe.configs.EntityManagerWrapper;
import edu.miu.cs544.moe.entity.Course;
import edu.miu.cs544.moe.entity.DistanceEducation;
import edu.miu.cs544.moe.entity.Student;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;

import java.util.Collection;

public class StudentService extends AbstractService<Student> implements Service<Student> {

    public StudentService(EntityManagerWrapper entityManagerWrapper) {
        super(entityManagerWrapper, Student.class);
    }

    public Collection<Student> findStudentByGpaAndCapacity() {
        String qlString = "SELECT s FROM Student s WHERE s.gpa > 3.5 AND TYPE(s.courseAttending) = OnCampus AND TREAT(s.courseAttending as OnCampus).capacity > 30";
        TypedQuery<Student> query = this.entityManagerWrapper.getEntityManager().createQuery(qlString, Student.class);
        return query.getResultList();
    }

    public Collection<Student> canGraduate() {
        this.entityManagerWrapper.begin();
        TypedQuery<Student> namedQuery = this.entityManagerWrapper.getEntityManager().createNamedQuery("Student.canGraduate", Student.class);
        var result = namedQuery.getResultList();
        this.entityManagerWrapper.end();
        return result;
    }

    public Collection<Student> findStudentsInDe() {
        CriteriaBuilder cb = this.entityManagerWrapper.getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Student> cq = cb.createQuery(Student.class);
        Root<Student> student = cq.from(Student.class);

        Join<Student, Course> course = student.join("courseAttending");

        Predicate gpaPredicate = cb.lessThan(student.get("gpa"), 3.0);
        Predicate courseTypePredicate = cb.equal(cb.literal(DistanceEducation.class), course.type());
        Predicate professorPredicate = cb.equal(course.get("professorName"), "Najeeb");

        cq.select(student).where(cb.and(gpaPredicate, courseTypePredicate, professorPredicate)).distinct(true);
        return this.entityManagerWrapper.getEntityManager().createQuery(cq).getResultList();
    }
}
