package edu.miu.cs544.moe.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

@Entity
@Table(name = "DISTANCE_EDUCATION")
@Cacheable(false)
public class DistanceEducation extends Course {
    private String examProfessor;

    @ElementCollection
    @CollectionTable(name = "WEBINAR_SESSION_DATES")
    private Collection<Date> webinarSessionDates = new ArrayList<>();

    public DistanceEducation() {
        super();
    }

    public DistanceEducation(String title, Date startDate, String professorName, String examProfessor, Collection<Date> webinarSessionDates) {
        super(title, startDate, professorName);
        this.examProfessor = examProfessor;
        this.webinarSessionDates = webinarSessionDates;
    }

    public String getExamProfessor() {
        return examProfessor;
    }

    public void setExamProfessor(String examProfessor) {
        this.examProfessor = examProfessor;
    }

    public Collection<Date> getWebinarSessionDates() {
        return webinarSessionDates;
    }

    public void setWebinarSessionDates(Collection<Date> webinarSessionDates) {
        this.webinarSessionDates = webinarSessionDates;
    }
}
