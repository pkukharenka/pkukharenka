package ru.job4j.nonblocking;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Пользователь.
 *
 * @author Pyotr Kukharenka
 * @since 09.01.2018
 */

public class User {
    /**
     * Id пользователя
     */
    private int id;
    /**
     * Имя пользователя.
     */
    private String name;
    /**
     * Версия обновления пользователя.
     */
    private int version;

    /**
     * Конструктор для инициализации.
     *
     * @param id   - Id пользователя
     * @param name - Имя пользователя.
     */
    public User(int id, String name) {
        this.id = id;
        this.name = name;
        this.version = 0;
    }

    //Геттеры, сеттеры
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
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
        return name != null ? name.equals(user.name) : user.name == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }
}
