package ru.job4j.todo.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.todo.model.Task;
import ru.job4j.todo.repository.TaskStore;

import javax.transaction.Transactional;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class TaskService {
    private final TaskStore taskStore;

    @Transactional
    public List<Task> findAll() {
        List<Task> taskList = taskStore.findAll();
        taskList.forEach(task -> {
            task.setCreated(task.getCreated()
                    .atZone(ZoneId.of(task.getUser().getTimeZone().getID()))
                    .withZoneSameInstant(ZoneId.of(task.getUser().getTimeZone().getID()))
                    .toLocalDateTime());
        });
        return taskList;
    }

    public Optional<Task> findById(int id) {
        return taskStore.findById(id);
    }

    @Transactional
    public boolean delete(int id) {
        Optional<Task> task = taskStore.findById(id);
        task.ifPresent(t -> taskStore.delete(id));
        return task.isPresent();
    }

    public void completed(int id) {
        taskStore.completed(id);
    }

    public void save(Task task) {
        taskStore.save(task);
    }

    @Transactional
    public boolean update(Task task) {
        Optional<Task> taskUpdate = taskStore.findById(task.getId());
        taskUpdate.ifPresent(t -> taskStore.update(task));
        return taskUpdate.isPresent();
    }
}
