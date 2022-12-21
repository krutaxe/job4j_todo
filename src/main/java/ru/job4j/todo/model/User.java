package ru.job4j.todo.model;

import lombok.*;
import javax.persistence.*;
import java.util.Date;
import java.util.TimeZone;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "todo_user")
@ToString
public class User {
    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    @EqualsAndHashCode.Include
    private String login;

    private String password;

    @Column(name = "user_zone")
    private TimeZone timeZone;
}
