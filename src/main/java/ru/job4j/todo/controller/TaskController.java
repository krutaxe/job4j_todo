package ru.job4j.todo.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.job4j.todo.model.Task;
import ru.job4j.todo.service.TaskService;
import ru.job4j.todo.util.SessionHttp;
import javax.servlet.http.HttpSession;

@Controller
@AllArgsConstructor
@RequestMapping("/tasks")
public class TaskController {
    private final TaskService taskService;

    @GetMapping("/IsDone")
    public String indexIsDone(Model model, HttpSession session) {
        SessionHttp.getSessionUser(model, session);
        model.addAttribute("tasks", taskService.findAll()
                .stream().filter(Task::isDone).toList());
        return "task/tasks";
    }

    @GetMapping("/NotDone")
    public String indexNotDone(Model model, HttpSession session) {
        SessionHttp.getSessionUser(model, session);
        model.addAttribute("tasks", taskService.findAll()
                .stream().filter(e -> !e.isDone()).toList());
        return "task/tasks";
    }

    @GetMapping("/createdForm")
    public String create() {
        return "task/created";
    }

    @PostMapping("/save")
    public String add(@ModelAttribute Task task) {
        taskService.save(task);
        return "redirect:/";
    }

    @GetMapping("/{id}")
    public String show(Model model, @PathVariable("id") int id) {
        model.addAttribute("task", taskService.findById(id));
        return "task/show";
    }

    @PostMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        try {
            taskService.delete(id);
        } catch (Exception e) {
            return "task/failDelete";
        }
        return "redirect:/";
    }

    @PostMapping("/show/{id}")
    public String completed(@PathVariable("id") int id) {
        taskService.completed(id);
        return "redirect:/tasks/IsDone";
    }

    @GetMapping("/{id}/edit")
    private String edite(Model model, @PathVariable("id") int id) {
        try {
            model.addAttribute("task", taskService.findById(id));
        } catch (Exception e) {
            return "task/failEdit";
        }
        return "task/edite";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute("task") Task task) {
        try {
            taskService.update(task);
        } catch (Exception e) {
            return "task/failEdit";
        }
        return "redirect:/tasks/" + task.getId();
    }
}
