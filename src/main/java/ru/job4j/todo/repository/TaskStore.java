package ru.job4j.todo.repository;

import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Repository;
import ru.job4j.todo.model.Task;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.TimeZone;

@Repository
@AllArgsConstructor
public class TaskStore {
    private final CrudRepository crudRepository;

    public List<Task> findAll() {
        return crudRepository.query("SELECT DISTINCT t FROM  Task t "
                + "left JOIN FETCH t.categories order by t.id", Task.class);
    }

    public void save(Task task) {
        crudRepository.run(session -> session.persist(task));
    }

    public Optional<Task> findById(int id) {
        return crudRepository.optional("from Task where id = :fId", Task.class,
                Map.of("fId", id));
    }

    public void delete(int id) {
        crudRepository.run("delete from Task where id = : fId",
                Map.of("fId", id));
    }

    public void update(Task task) {
        crudRepository.run(session -> session.merge(task));
    }

    public void completed(int id) {
        crudRepository.run(session -> {
            Task task = session.get(Task.class, id);
            task.setDone(true);
        });
    }
}