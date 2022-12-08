package ru.job4j.todo.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.job4j.todo.service.TaskService;
import ru.job4j.todo.util.SessionHttp;

import javax.servlet.http.HttpSession;

@Controller
@AllArgsConstructor
public class IndexController {
    private final TaskService taskService;

    @GetMapping("/")
    public String index(Model model, HttpSession session) {
        SessionHttp.getSessionUser(model, session);
        model.addAttribute("tasks", taskService.findAll());
        return "task/tasks";
    }
}
