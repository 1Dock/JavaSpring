package kz.bitlab.Lesson3.controllers;

import kz.bitlab.Lesson3.db.DBManager;
import kz.bitlab.Lesson3.db.Task;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;

@Controller
public class TaskManagerController {
    @GetMapping(value = "")
    public String openHome(Model model) {
        ArrayList<Task> tasks = DBManager.getAllTasks();
        model.addAttribute("tasks", tasks);
        return "home";
    }

    @GetMapping(value = "edit/{id}")
    public String openEdit(Model model, @PathVariable(name = "id") Long id) {
        Task task = DBManager.getTask(id);
        model.addAttribute("task", task);
        return "edit";
    }

    @PostMapping(value = "addtask")
    public String addTask(@RequestParam(name = "name", defaultValue = "No name") String name,
                          @RequestParam(name = "description", defaultValue = "No surname") String description,
                          @RequestParam(name = "date", defaultValue = "0") String date) {
        Task task = new Task();
        task.setName(name);
        task.setDescription(description);
        task.setDate(date);
        task.setCompleted(false);

        String redirect = "?error";

        if (DBManager.addTask(task) != null) {
            redirect = "?success";
        }

        return "redirect:/" + redirect;
    }

    @PostMapping(value = "edittask/{id}")
    public String editTask(@PathVariable(name = "id") Long id, @RequestParam(name = "name", defaultValue = "No name") String name,
                          @RequestParam(name = "description", defaultValue = "No surname") String description,
                          @RequestParam(name = "date") String date,
                          @RequestParam(name = "completed", defaultValue = "false") boolean completed) {
        Task task = new Task();
        task.setId(id);
        task.setName(name);
        task.setDescription(description);
        task.setDate(date);
        task.setCompleted(completed);

        String redirect = "?error";

        if (DBManager.editTask(task) != 0) {
            redirect = "?success";
        }

        return "redirect:/" + redirect;
    }

    @GetMapping(value = "deletetask/{id}")
    public String deleteTask(@PathVariable(name = "id") Long id) {
        DBManager.deleteTask(id);
        return "redirect:/?success";
    }
}
