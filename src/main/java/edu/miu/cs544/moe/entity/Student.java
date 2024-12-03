package edu.miu.cs544.moe.entity;

import jakarta.persistence.*;

import java.util.Collection;

@Entity
@NamedQuery(name = "Student.canGraduate", query = "SELECT s FROM Student s WHERE s.gpa >= 3.0 AND SIZE(s.coursesAttended) >= 9 AND s.courseAttending IS NULL", lockMode = LockModeType.PESSIMISTIC_READ)
public class Student {
    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private double gpa;

    @Version
    private int version;

    @ManyToOne
    @JoinColumn
    private Course courseAttending;

    @ManyToMany
    private Collection<Course> coursesAttended;

    public Student() {
    }

    public Student(String name, double gpa) {
        this.name = name;
        this.gpa = gpa;
    }

    public Long getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setGpa(double gpa) {
        this.gpa = gpa;
    }

    public double getGpa() {
        return gpa;
    }

    public Course getCourseAttending() {
        return courseAttending;
    }

    public void setCourseAttending(Course course) {
        this.courseAttending = course;
    }

    public Collection<Course> getCoursesAttended() {
        return coursesAttended;
    }

    public void setCoursesAttended(Collection<Course> coursesAttended) {
        this.coursesAttended = coursesAttended;
    }

    public void incrementGpa() {
        this.gpa += 1.0;
    }
    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", gpa=" + gpa +
                '}';
    }
}
