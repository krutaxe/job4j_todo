package ru.job4j.todo.util;

import ru.job4j.todo.model.Task;

import java.time.ZoneId;

public class SetTimeZone {
    public static void setZone(Task task) {
        ZoneId zoneId = task.getUser().getTimeZone().toZoneId();
        task.setCreated(task.getCreated()
                .atZone(zoneId)
                .withZoneSameInstant(zoneId)
                .toLocalDateTime());
    }
}
