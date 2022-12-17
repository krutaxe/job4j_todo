package ru.job4j.todo.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "priorities")
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Priority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private int id;
    private String name;
    private int position;
}
