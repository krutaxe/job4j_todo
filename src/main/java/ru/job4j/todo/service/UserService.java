package ru.job4j.todo.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.todo.model.User;
import ru.job4j.todo.repository.UserStore;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {
    private final UserStore userStore;

    public Optional<User> add(User user) {
        return userStore.add(user);
    }

    public Optional<User> checkUser(String login, String pwd) {
        return userStore.checkUser(login, pwd);
    }
}
