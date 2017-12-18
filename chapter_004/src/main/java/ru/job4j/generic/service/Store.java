package ru.job4j.generic.service;

import ru.job4j.generic.models.Base;

/**
 * Интерфейс хранилища.
 *
 * @author Pyotr Kukharenka
 * @see Base
 * @since 18.12.2017
 */
public interface Store<T extends Base> {
    /**
     * Метод для добавления нового объекта в хранилище.
     * Объект должен быть наследником класса Base.
     *
     * @param model - объект типа Base
     */
    void add(T model);

    /**
     * Метод для замены одного объекта хранилища на другой.
     *
     * @param id    - ID обекта
     * @param model - новый объект для записи в хранилище на
     *              место старого.
     * @return - true если все прошло успешно.
     */
    boolean replace(String id, T model);

    /**
     * Метод для удаления объекта из хранилища.
     *
     * @param id - ID обекта
     * @return true если все прошло успешно.
     */
    boolean delete(String id);

    /**
     * Метод для поиска объекта в хранилище. Если
     * объект найден вернет его.
     *
     * @param id - ID обекта
     * @return найденный объект, либо null если он отсутствует
     */
    T findById(String id);
}
