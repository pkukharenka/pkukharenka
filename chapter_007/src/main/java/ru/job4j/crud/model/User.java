package ru.job4j.crud.model;

import java.time.LocalDate;

/**
 * Объект - пользователь
 *
 * @author Pyotr Kukharenka
 * @since 21.02.2018
 */

public class User {
    /**
     * Уникальный id пользователя
     */
    private int id;
    /**
     * Имя пользователя
     */
    private String name;
    /**
     * Логин пользователя
     */
    private String login;
    /**
     * Почтовый адрес пользователя
     */
    private String email;
    /**
     * Дата создания пользователя
     */
    private LocalDate createDate;

    public User(int id, String name, String login, String email, LocalDate createDate) {
        this.id = id;
        this.name = name;
        this.login = login;
        this.email = email;
        this.createDate = createDate;
    }

    public User(int id, String name, String login, String email) {
        this.id = id;
        this.name = name;
        this.login = login;
        this.email = email;
    }

    public User(String name, String login, String email) {
        this.name = name;
        this.login = login;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLogin() {
        return login;
    }

    public String getEmail() {
        return email;
    }

    public LocalDate getCreateDate() {
        return createDate;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "User{"
                + "id=" + id
                + ", name='" + name + '\''
                + ", login='" + login + '\''
                + ", email='" + email + '\''
                + ", createDate=" + createDate
                + '}';
    }
}
