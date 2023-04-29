package kz.bitlab.Lesson5.services.impl;

import kz.bitlab.Lesson5.entity.Task;
import kz.bitlab.Lesson5.repositories.TaskRepository;
import kz.bitlab.Lesson5.services.FolderService;
import kz.bitlab.Lesson5.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaskServiceImpl implements TaskService {
    @Autowired
    private TaskRepository taskRepository;

    @Override
    public Task findById(Long id) {
        return taskRepository.findById(id).get();
    }

    @Override
    public Task create(Task task) {
        return taskRepository.save(task);
    }

    @Override
    public Task update(Task task) {
        return taskRepository.save(task);
    }

    @Override
    public void deleteById(Long id) {
        taskRepository.deleteById(id);
    }
}
