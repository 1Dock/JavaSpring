package kz.bitlab.Lesson5.services;

import kz.bitlab.Lesson5.entity.Task;

public interface TaskService {
    Task findById(Long id);

    Task create(Task task);

    Task update(Task task);

    void deleteById(Long id);
}
