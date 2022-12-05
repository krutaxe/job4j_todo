package ru.job4j.todo.controller;


import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.job4j.todo.model.Task;
import ru.job4j.todo.service.TaskService;

@Controller
@AllArgsConstructor
@RequestMapping("/tasks")
public class TaskController {
    private final TaskService taskService;

    @GetMapping("/createdForm")
    public String create() {
        return "created";
    }

    @PostMapping("/save")
    public String add(@ModelAttribute Task task) {
        taskService.save(task);
        return "redirect:/tasks";
    }

    @GetMapping("/{id}")
    public String show(Model model, @PathVariable("id") int id) {
        model.addAttribute("task", taskService.findById(id));
        return "show";
    }

    @PostMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        taskService.delete(id);
        return "redirect:/tasks";
    }

    @PostMapping("/show/{id}")
    public String completed(@PathVariable("id") int id) {
        taskService.completed(id);
        return "redirect:/tasksIsDone";
    }

    @GetMapping("/{id}/edit")
    private String edite(Model model, @PathVariable("id") int id) {
        model.addAttribute("task", taskService.findById(id));
        return "edite";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute("task") Task task) {
        taskService.update(task.getId(), task);
        return "redirect:/tasks/" + task.getId();
    }
}
