package ru.job4j.crud;

import java.util.Collection;

/**
 * Базовый интерфейс, описывающий основные методы
 * работы хранилища пользователей
 *
 * @author Pyotr Kukharenka
 * @since 19.02.2018
 */
public interface Store {

    Collection<Users> getAll();

    int add(Users user);

    boolean update(Users user);

    boolean delete(int id);

    Users get(int id);
}
