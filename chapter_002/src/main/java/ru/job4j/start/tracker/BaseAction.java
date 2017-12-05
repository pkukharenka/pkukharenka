package ru.job4j.start.tracker;

import ru.job4j.start.tracker.UserAction;

/**
 * Абстрактный класс, реализующий конструктор Action и
 * функционал отображения пункта меню по каждому Action трекера.
 *
 * @author Pyotr Kukharenka
 * @since 04.12.2017
 */

public abstract class BaseAction implements UserAction {
    /**
     * Ключ меню трекера.
     */
    private int key;
    /**
     * Имя пункта меню трекера
     */
    private String name;

    /**
     * Конструктор, инициализирующий пункт меню трекера
     * с ключом и именем пункта.
     *
     * @param key  - ключ меню
     * @param name - имя пункта меню
     */
    public BaseAction(int key, String name) {
        this.key = key;
        this.name = name;
    }

    /**
     * Метод возвращает информацию о пункте меню трекера.
     *
     * @return - информация о действии.
     */
    public String info() {
        return String.format("%s. %s", this.key, this.name);
    }
}
