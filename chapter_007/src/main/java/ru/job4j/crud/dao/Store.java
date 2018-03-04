package ru.job4j.crud.dao;

import java.util.List;

/**
 * Базовый интерфейс, описывающий основные методы
 * работы хранилища пользователей
 *
 * @author Pyotr Kukharenka
 * @since 19.02.2018
 */
public interface Store<T> {

    List<T> findAll();

}
