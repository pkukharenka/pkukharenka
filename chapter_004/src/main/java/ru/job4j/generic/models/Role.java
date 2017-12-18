package ru.job4j.generic.models;

/**
 * Объект типа Role, наследующий поведение класса Base
 *
 * @author Pyotr Kukharenka
 * @see Base
 * @since 18.12.2017
 */

public class Role extends Base {
    /**
     * Конструктор, вызывающий конструктор суперкласса и
     * инициализирубщий объект.
     *
     * @param id - идентификатор объекта
     */
    public Role(String id) {
        super(id);
    }
}
