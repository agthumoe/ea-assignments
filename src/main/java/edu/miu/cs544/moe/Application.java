package edu.miu.cs544.moe;

import edu.miu.cs544.moe.service.StudentService;

public class Application {
    private static final String PERSISTENT_UNIT = "my-pu";
    public static void main(String[] args) {
        Util.seed();
        try (StudentService studentService = new StudentService(PERSISTENT_UNIT)) {
            System.out.println("All students with gpa > 3.5 and course capacity > 30:");
            System.out.println(studentService.findStudentByGpaAndCapacity());
            System.out.println("All students who can graduate:");
            System.out.println(studentService.canGraduate());
            System.out.println("All students who are in Distance Education and have gpa < 3.0 and professor Najeeb:");
            System.out.println(studentService.findStudentsInDe());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
