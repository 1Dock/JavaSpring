package kz.bitlab.Lesson2.db;

import java.util.ArrayList;

public class DBManager {
    private static ArrayList<Student> students = new ArrayList<>();
    private static Long id = 1L;

    public static ArrayList<Student> getAllStudents() {
        return students;
    }

    public static Student addStudent(Student student) {
        student.setId(id);
        students.add(student);
        id++;
        return student;
    }
}
