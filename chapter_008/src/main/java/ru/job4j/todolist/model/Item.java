package ru.job4j.todolist.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import ru.job4j.todolist.util.LocalDateDeserializer;
import ru.job4j.todolist.util.LocalDateSerializer;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * Простой объект.
 *
 * @author Pyotr Kukharenka
 * @since 21.03.2018
 */

public class Item implements Serializable {
    private int id;
    private String desc;
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate created;
    private boolean done;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public LocalDate getCreated() {
        return created;
    }

    public void setCreated(LocalDate created) {
        this.created = created;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    @Override
    public String toString() {
        return "Item{"
                + "id=" + id
                + ", desc='" + desc + '\''
                + ", created=" + created
                + ", done=" + done
                + '}';
    }
}
