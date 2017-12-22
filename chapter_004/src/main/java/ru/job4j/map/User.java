package ru.job4j.map;

import java.util.Calendar;

/**
 * Модель типового пользователя.
 *
 * @author Pyotr Kukharenka
 * @since 22.12.2017
 */

public class User {
    /**
     * Имя пользователя.
     */
    private String name;
    /**
     * Количество детей пользователя.
     */
    private int children;
    /**
     * Дата рождения пользователя.
     */
    private Calendar birthday;

    /**
     * Конструктор для инициализации пользователя по
     * трем параметрам.
     *
     * @param name - Имя пользователя.
     * @param children - Количество детей пользователя.
     * @param birthday - Дата рождения пользователя.
     */
    public User(String name, int children, Calendar birthday) {
        this.name = name;
        this.children = children;
        this.birthday = birthday;
    }
}
