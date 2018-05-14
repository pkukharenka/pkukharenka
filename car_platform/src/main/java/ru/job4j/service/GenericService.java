package ru.job4j.service;

import java.io.Serializable;
import java.util.List;

/**
 * TODO comments
 *
 * @author Pyotr Kukharenka
 * @since 06.04.2018
 */
public interface GenericService<T, ID extends Serializable> {

    T saveOrUpdate(T instance);

    void delete(T instance);

    List<T> findAll();

    T findById(ID id);
}
