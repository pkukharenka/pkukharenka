package ru.job4j.set;

import ru.job4j.list.LinkedContainer;
import ru.job4j.list.ListContainer;

import java.util.Iterator;

/**
 * Контейнер типа Set, позволяющий хранить только уникальные
 * значения элементов. Реализован на базе свявзного списка.
 *
 * @author Pyotr Kukharenka
 * @see LinkedContainer
 * @see ListContainer
 * @since 20.12.2017
 */

public class LinkedSetContainer<E> implements Iterable<E> {

    private LinkedContainer<E> list = new LinkedContainer<>();

    /**
     * Метод добавляет новый элемент в контейнер. При этом изначально
     * проверяется, есть ли такой элемент в коллекции методом contains
     *
     * @param value - значение элемента
     * @return - true, если элемент добавлен в контейнер, false если нет
     */
    public boolean add(E value) {
        boolean flag = false;
        if (!this.list.contains(value)) {
            this.list.add(value);
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
        return this.list.size();
    }

    @Override
    public Iterator<E> iterator() {
        return this.list.iterator();
    }
}
