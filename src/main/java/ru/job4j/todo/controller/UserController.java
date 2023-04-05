package ru.job4j.todo.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.job4j.todo.model.User;
import ru.job4j.todo.service.UserService;
import ru.job4j.todo.util.SessionHttp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.TimeZone;

@Controller
@AllArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/addUser")
    public String addUser(Model model, HttpSession session) {
        SessionHttp.getSessionUser(model, session);
        List<TimeZone> zones = new ArrayList<>();
        for (String timeId : TimeZone.getAvailableIDs()) {
            zones.add(TimeZone.getTimeZone(timeId));
        }
        model.addAttribute("zones", zones);

        return "user/addUser";
    }

    @PostMapping("/registration")
    public String registration(Model model, @ModelAttribute User user,
                               @RequestParam("zone") String zone) {
        user.setTimeZone(TimeZone.getTimeZone(ZoneId.of(zone)));
        Optional<User> regUser = userService.add(user);
        if (regUser.isEmpty()) {
            model.addAttribute("message",
                    "Пользователь с таким логином уже существует");
            return "redirect:/fail";
        }
        return "redirect:/success";
    }

    @GetMapping("/fail")
    public String fail() {
        return "user/fail";
    }

    @GetMapping("/success")
    public String success() {
        return "user/success";
    }

    @GetMapping("/loginPage")
    public String loginPage(Model model, @RequestParam(
            name = "fail", required = false) Boolean fail, HttpSession session) {
        SessionHttp.getSessionUser(model, session);
        model.addAttribute("fail", fail != null);
        return "user/login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute User user, HttpServletRequest req) {
        Optional<User> userDb = userService.checkUser(user.getLogin(), user.getPassword());
        if (userDb.isEmpty()) {
            return "redirect:/loginPage?fail=true";
        }
        HttpSession session = req.getSession();
        session.setAttribute("user", userDb.get());
        return "redirect:/";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/loginPage";
    }
}
