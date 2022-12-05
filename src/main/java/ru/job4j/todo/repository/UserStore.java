package ru.job4j.todo.repository;

import lombok.AllArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import ru.job4j.todo.model.User;
import org.hibernate.query.Query;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class UserStore {

    private final SessionFactory sf;

    public Optional<User> add(User user) {
        Optional<User> rsl;
        try (Session session = sf.openSession()) {
            session.save(user);
            rsl = Optional.of(user);
        } catch (Exception e) {
            rsl = Optional.empty();
        }
        return rsl;
    }

    public Optional<User> checkUser(String login, String pwd) {
        try (Session session = sf.openSession()) {
            Query query = session.createQuery("from User where "
                   + "login = :paramLogin and password = :paramPwd");
            query.setParameter("paramLogin", login);
            query.setParameter("paramPwd", pwd);
            return query.uniqueResultOptional();
        }
    }
}
