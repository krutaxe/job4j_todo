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
        return Optional.of(taskStore.findById(id));
    }

    public boolean delete(int id) {
        boolean rsl = false;
        if (findById(id).isPresent()) {
            taskStore.delete(id);
            rsl = true;
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
        boolean rsl = false;
        if (findById(task.getId()).isPresent()) {
            taskStore.update(task);
            rsl = true;
        }
        return rsl;
    }
}
