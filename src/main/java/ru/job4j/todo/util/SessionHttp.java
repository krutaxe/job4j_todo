package ru.job4j.todo.util;

import org.springframework.ui.Model;
import ru.job4j.todo.model.User;
import javax.servlet.http.HttpSession;

public class SessionHttp {

    private SessionHttp() {}

    public static void getSessionUser(Model model, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            user = new User();
            user.setName("Гость");
        }
        model.addAttribute("userSession", user);
    }
}
