package ru.job4j.tracker.model;

import java.sql.Timestamp;

/**
 * Класс, описывающий объект типа Item.
 *
 * @author Pyotr Kukharenka
 * @since 28.11.2017
 */

public class Item {
    private int id;
    private String name;
    private String desc;
    private Timestamp created;
    private String[] comments;

    public Item(int id, String name, String desc, Timestamp created) {
        this.id = id;
        this.name = name;
        this.desc = desc;
        this.created = created;
    }

    public Item(String name, String desc, Timestamp created) {
        this.name = name;
        this.desc = desc;
        this.created = created;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Timestamp getCreated() {
        return created;
    }

    public void setCreated(Timestamp created) {
        this.created = created;
    }

    public String[] getComments() {
        return comments;
    }

    public void setComments(String[] comments) {
        this.comments = comments;
    }

    @Override
    public String toString() {
        return "id='" + id + '\''
                + ", имя='" + name + '\''
                + ", описание='" + desc + '\''
                + '}';
    }
}
