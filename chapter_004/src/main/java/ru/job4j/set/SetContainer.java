package ru.job4j.set;

import ru.job4j.list.ArrayContainer;
import ru.job4j.list.ListContainer;

/**
 * Контейнер типа Set, позволяющий хранить только уникальные
 * значения элементов. Реализован на базе массивов.
 *
 * @author Pyotr Kukharenka
 * @see ArrayContainer
 * @see ListContainer
 * @since 20.12.2017
 */

public class SetContainer<E> {
    /**
     * Хранилище элементов на базе массива.
     */
    private ArrayContainer<E> array = new ArrayContainer<>();

    /**
     * Метод добавляет новый элемент в контейнер. При этом изначально
     * проверяется, есть ли такой элемент в коллекции методом contains
     *
     * @param value - значение элемента
     */
    public boolean add(E value) {
        boolean flag = false;
        if (!this.array.contains(value)) {
            this.array.add(value);
            flag = true;
        }
        return flag;
    }

    /**
     * Возвращает размер контейнера
     *
     * @return - размер контейнера.
     */
    public int size() {
        return this.array.size();
    }

}
