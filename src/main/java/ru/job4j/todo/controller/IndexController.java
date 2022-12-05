package ru.job4j.todo.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.job4j.todo.model.Task;
import ru.job4j.todo.service.TaskService;
import ru.job4j.todo.util.SessionHttp;

import javax.servlet.http.HttpSession;

@Controller
@AllArgsConstructor
public class IndexController {
    private final TaskService taskService;

    @GetMapping("/tasks")
    public String index(Model model, HttpSession session) {
        SessionHttp.getSessionUser(model, session);
        model.addAttribute("tasks", taskService.findAll());
        return "tasks";
    }

    @GetMapping("/tasksIsDone")
    public String indexIsDone(Model model, HttpSession session) {
        SessionHttp.getSessionUser(model, session);
        model.addAttribute("tasks", taskService.findAll()
                .stream().filter(Task::isDone).toList());
        return "tasks";
    }

    @GetMapping("/tasksNotDone")
    public String indexNotDone(Model model, HttpSession session) {
        SessionHttp.getSessionUser(model, session);
        model.addAttribute("tasks", taskService.findAll()
                .stream().filter(e -> !e.isDone()).toList());
        return "tasks";
    }
}
