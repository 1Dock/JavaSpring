package kz.bitlab.Lesson5.services;

import kz.bitlab.Lesson5.entity.Folder;
import kz.bitlab.Lesson5.entity.Task;

import java.util.List;

public interface FolderService {
    Folder save(Folder folder);

    List<Folder> findAll();

    Folder findById(Long id);

    Folder addCategory(Long folderId, Long categoryId);

    Folder addTask(Long folderId, Task task);

    void deleteCategory(Long folderId, Long categoryId);

    void deleteTask(Long folderId, Long taskId);

    void deleteById(Long id);
}
