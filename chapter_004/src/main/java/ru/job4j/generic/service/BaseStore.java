package ru.job4j.generic.service;

import ru.job4j.generic.SimpleArray;
import ru.job4j.generic.models.Base;

/**
 * Абстрактный класс реализующий интерфейс Store. В качестве
 * контейнера для хранения данных используется класс SimpleArray.
 *
 * @author Pyotr Kukharenka
 * @see SimpleArray
 * @see Store
 * @see Base
 * @since 18.12.2017
 */

public abstract class BaseStore<T extends Base> implements Store<T> {

    /**
     * Контейнер для хранения типизированных данных.
     */
    private SimpleArray<T> elements;

    /**
     * Указатель на индекс ячейки контейнера для найденного
     * элемента.
     */
    private int pos;

    /**
     * Конструктор инициализирующий хранилище.
     *
     * @param elements - типизированный контейнер.
     */
    public BaseStore(SimpleArray<T> elements) {
        this.elements = elements;
    }

    /**
     * Добавление объекта в хранилище.
     *
     * @param model - объект типа Base
     */
    public void add(T model) {
        this.elements.add(model);
    }

    /**
     * Метод для замены одного объекта хранилища на другой.
     *
     * @param id    - ID обекта
     * @param model - новый объект для записи в хранилище на
     *              место старого.
     * @return - true если все прошло успешно.
     */
    public boolean replace(String id, T model) {
        boolean flag = false;
        T base = this.findById(id);
        if (base != null) {
            this.elements.update(this.pos, model);
            flag = true;
        }
        return flag;
    }

    /**
     * Метод для удаления объекта из хранилища.
     *
     * @param id - ID обекта
     * @return - true если все прошло успешно.
     */
    public boolean delete(String id) {
        boolean flag = false;
        T base = this.findById(id);
        if (base != null) {
            this.elements.delete(this.pos);
            flag = true;
        }
        return flag;
    }

    /**
     * Метод для поиска объекта в хранилище. С помощью итератора
     * проходим по хранилищу, если объект найден возвращаем его
     *
     * @param id - ID обекта
     * @return - найденный объект или null.
     */
    public T findById(String id) {
        T result = null;
        this.pos = 0;
        for (T base : this.elements) {
            if (base != null && base.getId().equals(id)) {
                result = base;
                break;
            }
            this.pos++;
        }
        return result;
    }
}
