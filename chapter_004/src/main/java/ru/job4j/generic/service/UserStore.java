package ru.job4j.generic.service;

import ru.job4j.generic.SimpleArray;
import ru.job4j.generic.models.User;

/**
 * Хранилище объектов типа User.
 *
 * @author Pyotr Kukharenka
 * @since 18.12.2017
 */

public class UserStore extends BaseStore<User> {
    /**
     * Конструктор, вызывающий конструктор суперкласса.
     *
     * @param users - контейнер для хранения.
     */
    public UserStore(SimpleArray<User> users) {
        super(users);
    }
}
