package edu.miu.cs544.moe;

import edu.miu.cs544.moe.service.StudentService;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class XmlBasedApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("configs.xml");
//        Seeder seeder = context.getBean(Seeder.class);
//        seeder.seed();
        StudentService studentService = context.getBean(StudentService.class);
        System.out.println("All students with gpa > 3.5 and course capacity > 30:");
        System.out.println(studentService.findStudentByGpaAndCapacity());
        System.out.println("All students who can graduate:");
        System.out.println(studentService.canGraduate());
        System.out.println("All students who are in Distance Education and have gpa < 3.0 and professor Najeeb:");
        System.out.println(studentService.findStudentsInDe());
    }
}
