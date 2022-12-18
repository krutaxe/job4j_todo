package ru.job4j.todo.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.todo.model.Priority;
import ru.job4j.todo.repository.PriorityStore;

import java.util.List;

@Service
@AllArgsConstructor
public class PriorityService {
    private final PriorityStore priorityStore;

    public List<Priority> findAll() {
        return priorityStore.findAll();
    }

    public Priority findById(int id) {
        return priorityStore.findById(id).orElse(new Priority(2, "normal", 2));
    }
}
