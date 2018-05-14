package ru.job4j.generic.model;

/**
 * Класс описывающий общее поведение объектов
 *
 * @author Pyotr Kukharenka
 * @since 18.12.2017
 */

public abstract class Base {
    /**
     * Уникальный идентификатор объекта
     */
    private final String id;

    /**
     * Конструктор инициализирующий объект
     *
     * @param id - уникальный идентификатор объекта
     */
    public Base(final String id) {
        this.id = id;
    }

    /**
     * Метод возвращает Id объекта
     *
     * @return - Id объекта
     */
    public String getId() {
        return id;
    }


}
