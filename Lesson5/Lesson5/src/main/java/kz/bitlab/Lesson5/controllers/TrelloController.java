package kz.bitlab.Lesson5.controllers;


import kz.bitlab.Lesson5.entity.Folder;
import kz.bitlab.Lesson5.entity.Task;
import kz.bitlab.Lesson5.repositories.StatusRepository;
import kz.bitlab.Lesson5.services.CategoryService;
import kz.bitlab.Lesson5.services.CommentService;
import kz.bitlab.Lesson5.services.FolderService;
import kz.bitlab.Lesson5.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class TrelloController {
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private CommentService commentService;
    @Autowired
    private FolderService folderService;
    @Autowired
    private StatusRepository statusRepository;
    @Autowired
    private TaskService taskService;

    @GetMapping(value = "")
    public String openAllFolders(Model model) {
        model.addAttribute("folders", folderService.findAll());

        return "folders";
    }

    @GetMapping(value = "details-folder/{id}")
    public String openDetailsFolder(Model model, @PathVariable(name = "id") Long id) {
        model.addAttribute("folder", folderService.findById(id));
        model.addAttribute("categories", categoryService.findAll());

        return "details-folder";
    }

    @GetMapping(value = "details-task/{id}")
    public String openDetailsTask(Model model, @PathVariable(name = "id") Long id) {
        model.addAttribute("task", taskService.findById(id));
        model.addAttribute("statuses", statusRepository.findAll());

        return "details-task";
    }

    @PostMapping(value = "add-folder")
    public String addFolder(@RequestParam(name = "name", defaultValue = "New folder") String name) {
        String redirect = "?error";

        Folder folder = new Folder();
        folder.setName(name);

        if (folderService.save(folder) != null) redirect = "?success";

        return "redirect:/" + redirect;
    }

    @PostMapping(value = "update-folder")
    public String updateFolder(@RequestParam(name = "folder_id") Long folderId,
                               @RequestParam(name = "folder_name", defaultValue = "No name") String name) {
        String redirect = "details-folder/" + folderId;

        Folder folder = folderService.findById(folderId);
        folder.setName(name);

        if (folderService.save(folder) != null) redirect += "?success";
        else redirect += "?error";

        return "redirect:/" + redirect;
    }

    @PostMapping(value = "delete-folder")
    public String deleteFolder(@RequestParam(name = "folder_id") Long folderId) {
        folderService.deleteById(folderId);

        return "redirect:/";
    }

    @PostMapping(value = "add-task")
    public String addTask(@RequestParam(name = "folder_id") Long folderId,
                          @RequestParam(name = "title", defaultValue = "New task") String title,
                          @RequestParam(name = "description", defaultValue = "") String description) {
        String redirect = "details-folder/" + folderId;

        Task task = new Task();
        task.setTitle(title);
        task.setStatus(statusRepository.findById(1L).get());
        task.setDescription(description);
        task.setFolderId(folderId);

        if (folderService.addTask(folderId, task) != null) redirect += "?success";
        else redirect += "?error";

        return "redirect:/" + redirect;
    }

    @PostMapping(value = "update-task")
    public String updateTask(@RequestParam(name = "task_id") Long taskId,
                          @RequestParam(name = "title", defaultValue = "New task") String title,
                          @RequestParam(name = "status") Long statusId,
                          @RequestParam(name = "description", defaultValue = "") String description) {
        String redirect = "details-task/" + taskId;

        Task task = taskService.findById(taskId);
        task.setTitle(title);
        task.setStatus(statusRepository.findById(statusId).get());
        task.setDescription(description);

        if (taskService.update(task) != null) redirect += "?success";
        else redirect += "?error";

        return "redirect:/" + redirect;
    }

    @PostMapping(value = "delete-task")
    public String deleteTask(@RequestParam(name = "folder_id") Long folderId,
                            @RequestParam(name = "task_id") Long taskId) {
        folderService.deleteTask(folderId, taskId);

        return "redirect:/details-folder/" + folderId;
    }

    @PostMapping(value = "add-category")
    public String addCategory(@RequestParam(name = "folder_id") Long folderId,
                              @RequestParam(name = "category_id") Long categoryId) {
        String redirect = "details-folder/" + folderId;

        if (folderService.addCategory(folderId, categoryId) != null) redirect += "?success";
        else redirect += "?error";

        return "redirect:/" + redirect;
    }

    @PostMapping(value = "delete-category")
    public String deleteCategory(@RequestParam(name = "folder_id") Long folderId,
                                 @RequestParam(name = "category_id") Long categoryId) {
        folderService.deleteCategory(folderId, categoryId);

        return "redirect:/details-folder/" + folderId;
    }


}
