package kz.bitlab.Lesson2.controllers;

import kz.bitlab.Lesson2.db.DBManager;
import kz.bitlab.Lesson2.db.Student;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;

@Controller
public class StudentListController {
    @GetMapping(value = "")
    public String openHome(Model model) {
        ArrayList<Student> students = DBManager.getAllStudents();
        model.addAttribute("students", students);
        return "home";
    }

    @GetMapping(value = "add-student")
    public String openAddStudent() {
        return "add-student";
    }

    @PostMapping(value = "addstudent")
    public String AddStudent(@RequestParam(name = "name", defaultValue = "No name") String name,
                                 @RequestParam(name = "surname", defaultValue = "No surname") String surname,
                                 @RequestParam(name = "exam", defaultValue = "0") int exam) {
        Student student = new Student();
        student.setName(name);
        student.setSurname(surname);
        student.setExam(exam);

        if (exam >= 90) {
            student.setMark("A");
        } else if (exam >= 75 && exam <= 89) {
            student.setMark("B");
        } else if (exam >= 60 && exam <= 74) {
            student.setMark("C");
        } else if (exam >= 50 && exam <= 59) {
            student.setMark("D");
        } else {
            student.setMark("F");
        }

        String redirect = "add-student?error";

        if (DBManager.addStudent(student) != null) {
            redirect = "add-student?success";
        }

        return "redirect:/" + redirect;
    }
}
