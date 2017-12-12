package ru.job4j.convert;

/**
 * Класс описывающий объект типа User. Каждый User
 * имеет 3 поля, это - id, name и city.
 *
 * @author Pyotr Kukharenka
 * @since 12.12.2017
 */

public class User {
    /**
     * Уникальный Id каждого пользователя.
     */
    private int id;
    /**
     * Имя пользователя.
     */
    private String name;
    /**
     * Город проживания пользователя.
     */
    private String city;

    /**
     * Конструктор, инициализирующий пользователя.
     *
     * @param id - уникальный id пользователя.
     * @param name - имя пользователя.
     * @param city - город пользователя
     */
    public User(int id, String name, String city) {
        this.id = id;
        this.name = name;
        this.city = city;
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

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
