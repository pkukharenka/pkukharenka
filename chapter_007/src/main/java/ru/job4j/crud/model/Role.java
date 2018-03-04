package ru.job4j.crud.model;

/**
 * Объект - роль пользователя в системе
 *
 * @author Pyotr Kukharenka
 * @since 27.02.2018
 */

public class Role {

    private int id;
    private String type;

    public Role(int id) {
        this.id = id;
    }

    public Role(int id, String type) {
        this.id = id;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
