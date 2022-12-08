package ru.job4j.todo.repository;

import lombok.AllArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import ru.job4j.todo.model.Task;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class TaskStore {
    private final SessionFactory sf;
    private final CrudRepository crudRepository;

    public List<Task> findAll() {
        return crudRepository.query("FROM Task order by id", Task.class);
    }

    public void save(Task task) {
        crudRepository.run(session -> session.persist(task));
    }

    public Task findById(int id) {
        return crudRepository.optional("from Task where id = :fId", Task.class,
                Map.of("fId", id)).get();
    }

    public void delete(int id) {
        crudRepository.run(session -> session.delete(session.get(Task.class, id)));
    }

    public void completed(int id) {
        crudRepository.run(session -> {
            Task task = session.get(Task.class , id);
            task.setDone(true);
        });
    }

    public void update(Task task) {
        crudRepository.run(session -> session.merge(task));
    }
}