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

    public Optional<Task> findById(int id) {
        return taskStore.findById(id);
    }

    public boolean delete(int id) {
        return taskStore.delete(id);
    }

    public void completed(int id) {
        taskStore.completed(id);
    }

    public void save(Task task) {
        taskStore.save(task);
    }

    public boolean update(Task task) {
        return taskStore.update(task);
    }
}
