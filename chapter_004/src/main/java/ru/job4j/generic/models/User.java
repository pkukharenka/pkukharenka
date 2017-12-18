package ru.job4j.generic.models;

/**
 * Объект типа User, наследующий поведение класса Base
 *
 * @author Pyotr Kukharenka
 * @see Base
 * @since 18.12.2017
 */

public class User extends Base {
    /**
     * Конструктор, вызывающий конструктор суперкласса и
     * инициализирубщий объект.
     *
     * @param id - идентификатор объекта
     */
    public User(String id) {
        super(id);
    }
}
