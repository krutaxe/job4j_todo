package ru.job4j.todo.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.todo.model.Task;
import ru.job4j.todo.repository.TaskStore;

import java.util.List;
import java.util.Optional;

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

    public void delete(int id) {
        taskStore.delete(id);
    }

    public void completed(int id) {
        taskStore.completed(id);
    }

    public void save(Task task) {
        taskStore.save(task);
    }

    public void update(int id, Task task) {
        taskStore.update(id, task);
    }
}
