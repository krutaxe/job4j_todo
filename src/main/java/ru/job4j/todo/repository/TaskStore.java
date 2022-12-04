package ru.job4j.todo.repository;

import lombok.AllArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import ru.job4j.todo.model.Task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Repository
@AllArgsConstructor
public class TaskStore {
    private final SessionFactory sf;

    public List<Task> findAll() {
        try (Session session = sf.openSession()) {
         return session.createQuery("FROM Task order by id", Task.class).list();
        }
    }

    public void save(Task task) {
        try (Session session = sf.openSession()) {
            session.beginTransaction();
            session.save(task);
            session.getTransaction().commit();
        }
    }

    public Task findById(int id) {
        try (Session session = sf.openSession()) {
            return session.get(Task.class, id);
        }
    }

    public void delete(int id) {
        try (Session session = sf.openSession()) {
            session.beginTransaction();
            session.delete(session.get(Task.class, id));
            session.getTransaction().commit();
        }
    }

    public void completed(int id) {
        try (Session session = sf.openSession()) {
            session.beginTransaction();
            Task task = session.get(Task.class, id);
            task.setDone(true);
            session.getTransaction().commit();
        }
    }

    public void update(int id, Task task) {
        try (Session session = sf.openSession()) {
            session.beginTransaction();
            Task taskUpdate = session.get(Task.class, task.getId());
            taskUpdate.setName(task.getName());
            taskUpdate.setDescription(task.getDescription());
            session.merge(taskUpdate);
            session.getTransaction().commit();
        }
    }
}