package edu.miu.cs544.moe.configs;

import edu.miu.cs544.moe.entity.DistanceEducation;
import edu.miu.cs544.moe.entity.OnCampus;
import edu.miu.cs544.moe.entity.Student;
import edu.miu.cs544.moe.service.DistanceEducationService;
import edu.miu.cs544.moe.service.OnCampusService;
import edu.miu.cs544.moe.service.StudentService;

import java.util.Arrays;
import java.util.Date;

public class Seeder {
    private StudentService studentService;
    private DistanceEducationService distanceEducationService;
    private OnCampusService onCampusService;

    public void setStudentService(StudentService studentService) {
        this.studentService = studentService;
    }

    public void setDistanceEducationService(DistanceEducationService distanceEducationService) {
        this.distanceEducationService = distanceEducationService;
    }

    public void setOnCampusService(OnCampusService onCampusService) {
        this.onCampusService = onCampusService;
    }

    public void seed() {
            Student alice = studentService.save(new Student("Alice", 3));
            Student bob = studentService.save(new Student("Bob", 3.6));
            Student charlie = studentService.save(new Student("Charlie", 4));
            Student david = studentService.save(new Student("David", 2.5));
            Student emily = studentService.save(new Student("Emily", 2.9));
            Student frank = studentService.save(new Student("Frank", 3.0));

            System.out.println("Seed 6 students:");
            studentService.findAll().forEach(System.out::println);

            OnCampus c1 = onCampusService.save(new OnCampus("EA", new Date(), "Najeeb", "H101", 31));
            OnCampus c2 = onCampusService.save(new OnCampus("WAA", new Date(), "David", "H102", 20));
            OnCampus c3 = onCampusService.save(new OnCampus("OOP", new Date(), "Sarah", "H103", 40));
            OnCampus c4 = onCampusService.save(new OnCampus("Database Systems", new Date(), "Michael", "H104", 25));
            OnCampus c5 = onCampusService.save(new OnCampus("Software Engineering", new Date(), "Emily", "H105", 50));
            OnCampus c6 = onCampusService.save(new OnCampus("Data Structures", new Date(), "James", "H106", 35));

            DistanceEducation de1 = distanceEducationService.save(new DistanceEducation(
                    "Cloud Computing",
                    new Date(),
                    "Bruce",
                    "Robert",
                    Arrays.asList(new Date(), new Date(System.currentTimeMillis() + 7 * 24 * 60 * 60 * 1000))
            ));
            DistanceEducation de2 = distanceEducationService.save(new DistanceEducation(
                    "Machine Learning",
                    new Date(),
                    "Sophia",
                    "Anna",
                    Arrays.asList(new Date(), new Date(System.currentTimeMillis() + 10 * 24 * 60 * 60 * 1000))
            ));
            DistanceEducation de3 = distanceEducationService.save(new DistanceEducation(
                    "Artificial Intelligence",
                    new Date(),
                    "Mark",
                    "Alice",
                    Arrays.asList(new Date(), new Date(System.currentTimeMillis() + 5 * 24 * 60 * 60 * 1000))
            ));
            DistanceEducation de4 = distanceEducationService.save(new DistanceEducation(
                    "Cybersecurity",
                    new Date(),
                    "Oliver",
                    "Paul",
                    Arrays.asList(new Date(), new Date(System.currentTimeMillis() + 14 * 24 * 60 * 60 * 1000))
            ));
            DistanceEducation de5 = distanceEducationService.save(new DistanceEducation(
                    "Big Data",
                    new Date(),
                    "Najeeb",
                    "Grace",
                    Arrays.asList(new Date(), new Date(System.currentTimeMillis() + 3 * 24 * 60 * 60 * 1000))
            ));
            DistanceEducation de6 = distanceEducationService.save(new DistanceEducation(
                    "DevOps",
                    new Date(),
                    "Ethan",
                    "Frank",
                    Arrays.asList(new Date(), new Date(System.currentTimeMillis() + 20 * 24 * 60 * 60 * 1000))
            ));

            System.out.println("Seed 12 courses");
            onCampusService.findAll().forEach(System.out::println);

            // set attending course for students
            alice.setCourseAttending(c1);
            studentService.update(alice);
            bob.setCourseAttending(c1);
            studentService.update(bob);
            david.setCourseAttending(de5);
            studentService.update(david);
            emily.setCourseAttending(de5);
            studentService.update(emily);
            frank.setCourseAttending(de5);
            studentService.update(frank);
            // charlie has attended c1, de2, c2, de3, c3, de4, c4, de5, de6
            charlie.getCoursesAttended().add(c1);
            charlie.getCoursesAttended().add(de2);
            charlie.getCoursesAttended().add(c2);
            charlie.getCoursesAttended().add(de3);
            charlie.getCoursesAttended().add(c3);
            charlie.getCoursesAttended().add(de4);
            charlie.getCoursesAttended().add(c4);
            charlie.getCoursesAttended().add(de5);
            charlie.getCoursesAttended().add(de6);
            studentService.update(charlie);
            System.out.println("Done seeding...");
    }
}
