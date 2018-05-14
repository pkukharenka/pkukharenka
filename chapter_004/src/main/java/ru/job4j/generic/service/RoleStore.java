package ru.job4j.generic.service;

import ru.job4j.generic.SimpleArray;
import ru.job4j.generic.model.Role;

/**
 * Хранилище объектов типа Role.
 *
 * @author Pyotr Kukharenka
 * @since 18.12.2017
 */

public class RoleStore extends BaseStore<Role> {

    /**
     * Конструктор, вызывающий конструктор суперкласса.
     *
     * @param roles - контейнер для хранения.
     */
    public RoleStore(SimpleArray<Role> roles) {
        super(roles);
    }
}
