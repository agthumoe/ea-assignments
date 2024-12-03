package edu.miu.cs544.moe.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "TYPE")
public abstract class Course {
    @Id
    @GeneratedValue
    private Long id;
    private String title;
    @Temporal(TemporalType.DATE)
    private Date startDate;
    private String professorName;

    @OneToMany(mappedBy = "courseAttending")
    private Collection<Student> attendingStudents = new ArrayList<>();

    @ManyToMany(mappedBy = "coursesAttended")
    private Collection<Student> attendedStudents = new ArrayList<>();

    public Course() {
    }

    public Course(String title, Date startDate, String professorName) {
        this.title = title;
        this.startDate = startDate;
        this.professorName = professorName;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public String getProfessorName() {
        return professorName;
    }

    public void setProfessorName(String professorName) {
        this.professorName = professorName;
    }

    public Collection<Student> getAttendingStudents() {
        return attendingStudents;
    }

    public void setAttendingStudents(Collection<Student> attendingStudents) {
        this.attendingStudents = attendingStudents;
    }

    public Collection<Student> getAttendedStudents() {
        return attendedStudents;
    }

    public void setAttendedStudents(Collection<Student> attendedStudents) {
        this.attendedStudents = attendedStudents;
    }
}
