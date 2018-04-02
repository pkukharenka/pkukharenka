package ru.job4j.dao;

import java.util.List;

/**
 * Абстракция уровня доступа к данным
 *
 * @author Pyotr Kukharenka
 * @since 31.03.2018
 */
public interface AbstractDao<T> {

    long save(T t);
    void delete(T t);
    List<T> findAll();
    T findById(long id);
}
