package kz.bitlab.Lesson5.services.impl;

import kz.bitlab.Lesson5.entity.Category;
import kz.bitlab.Lesson5.entity.Folder;
import kz.bitlab.Lesson5.entity.Task;
import kz.bitlab.Lesson5.repositories.FolderRepository;
import kz.bitlab.Lesson5.services.CategoryService;
import kz.bitlab.Lesson5.services.FolderService;
import kz.bitlab.Lesson5.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FolderServiceImpl implements FolderService {
    @Autowired
    private FolderRepository folderRepository;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private TaskService taskService;

    @Override
    public Folder save(Folder folder) {
        return folderRepository.save(folder);
    }

    @Override
    public List<Folder> findAll() {
        return folderRepository.findAll();
    }

    @Override
    public Folder findById(Long id) {
        return folderRepository.findById(id).get();
    }

    @Override
    public Folder addCategory(Long folderId, Long categoryId) {
        Folder folder = this.findById(folderId);

        List<Category> categories = folder.getCategories();
        Category category = categoryService.findById(categoryId);
        categories.add(category);
        folder.setCategories(categories);

        return this.save(folder);
    }

    @Override
    public Folder addTask(Long folderId, Task task) {
        Folder folder = this.findById(folderId);
        taskService.create(task);

        folder.getTasks().add(task);

        return this.save(folder);
    }

    @Override
    public void deleteCategory(Long folderId, Long categoryId) {
        Folder folder = this.findById(folderId);
        Category category = categoryService.findById(categoryId);

        List<Category> categories = folder.getCategories();
        categories.remove(category);

        this.save(folder);
    }

    @Override
    public void deleteTask(Long folderId, Long taskId) {
        Folder folder = this.findById(folderId);
        Task task = taskService.findById(taskId);

        List<Task> tasks = folder.getTasks();
        tasks.remove(task);

        taskService.deleteById(taskId);
        this.save(folder);
    }

    @Override
    public void deleteById(Long id) {
        Folder folder = this.findById(id);

        List<Category> categories = folder.getCategories();
        folder.getCategories().removeAll(categories);
        List<Task> tasks = folder.getTasks();
        folder.getTasks().removeAll(tasks);

        this.save(folder);

        folderRepository.deleteById(id);
    }
}
