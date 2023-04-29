package kz.bitlab.Lesson3.db;

import java.util.ArrayList;

public class DBManager {
    private static final ArrayList<Task> tasks = new ArrayList<>();
    private static Long id = 1L;

    public static ArrayList<Task> getAllTasks() {
        return tasks;
    }

    public static Task getTask(Long id) {
        for (Task task : tasks) {
            if (task.getId().equals(id)) {
                return task;
            }
        }

        return null;
    }

    public static Task addTask(Task task) {
        task.setId(id);
        tasks.add(task);
        id++;
        return task;
    }

    public static int editTask(Task task) {
        int row = 0;

        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).getId().equals(task.getId())) {
                tasks.set(i, task);
                row = 1;
            }
        }

        return row;
    }

    public static void deleteTask(Long id) {
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).getId().equals(id)) {
                tasks.remove(i);
                break;
            }
        }
    }
}
