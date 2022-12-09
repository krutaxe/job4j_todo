package ru.job4j.todo.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.todo.model.Task;
import ru.job4j.todo.repository.TaskStore;
import java.util.List;

@Service
@AllArgsConstructor
public class TaskService {
    private final TaskStore taskStore;

    public List<Task> findAll() {
        return taskStore.findAll();
    }

    public Task findById(int id) {
        return taskStore.findById(id);
    }

    public boolean delete(int id) {
        boolean rsl = true;
        try {
            taskStore.delete(id);
        } catch (Exception e) {
            rsl = false;
        }
        return rsl;
    }

    public void completed(int id) {
        taskStore.completed(id);
    }

    public void save(Task task) {
        taskStore.save(task);
    }

    public boolean update(Task task) {
        boolean rsl = true;
        try {
            taskStore.update(task);
        } catch (Exception e) {
            rsl = false;
        }
        return rsl;
    }
}
