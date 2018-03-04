package ru.job4j.crud.model;

import java.time.LocalDate;

/**
 * Объект - пользователь
 *
 * @author Pyotr Kukharenka
 * @since 21.02.2018
 */

public class User {

    private int id;
    private String name;
    private String login;
    private String password;
    private String email;
    private LocalDate createDate;
    private Role role;

    public User(int id, String name, String login, String password, String email, LocalDate createDate, Role role) {
        this.id = id;
        this.name = name;
        this.login = login;
        this.password = password;
        this.email = email;
        this.createDate = createDate;
        this.role = role;
    }

    public User(String name, String login, String password, String email, Role role) {
        this.name = name;
        this.login = login;
        this.password = password;
        this.email = email;
        this.role = role;
    }

    public User(int id, String name, String login, String email, LocalDate createDate) {
        this.id = id;
        this.name = name;
        this.login = login;
        this.email = email;
        this.createDate = createDate;
    }

    public User(int id, String name, String login, String email, Role role) {
        this.id = id;
        this.name = name;
        this.login = login;
        this.email = email;
        this.role = role;
    }

    public User(int id, String name, String login, String password, String email, Role role) {
        this.id = id;
        this.name = name;
        this.login = login;
        this.password = password;
        this.email = email;
        this.role = role;
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

    public String getPassword() {
        return password;
    }

    public Role getRole() {
        return role;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        User user = (User) o;

        if (id != user.id) {
            return false;
        }
        if (login != null ? !login.equals(user.login) : user.login != null) {
            return false;
        }
        return password != null ? password.equals(user.password) : user.password == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (login != null ? login.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        return result;
    }


}
