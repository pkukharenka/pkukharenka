package ru.job4j.iterator;

import java.util.Iterator;

/**
 * Класс реализует интерфейс Iterable для итерации элементов
 * различных типов любых массивов.
 *
 * @author Pyotr Kukharenka
 * @since 16.12.2017
 */

public class ArrayIterator<T> implements Iterable<T> {
    /**
     * Счетчик строк масисва.
     */
    private int row = 0;
    /**
     * Счетчик столбцов массива.
     */
    private int col = 0;
    /**
     * Двумерный массив.
     */
    private final T[][] values;

    /**
     * Конструктор для инициализации массива.
     *
     * @param values - двумерный массив.
     */
    public ArrayIterator(T[][] values) {
        this.values = values;
    }

    /**
     * Метод возвращает итератор по заданным условиям в его
     * методах условиях.
     *
     * @return итератор.
     */
    @Override
    public Iterator<T> iterator() {

        return new Iterator<T>() {
            /**
             * Метод возвращает true если длина строки массива не равна
             * счетчику колонок.
             *
             * @return - true если значение есть и false если нет.
             */
            @Override
            public boolean hasNext() {
                return values[row].length != col;
            }

            /**
             * Возвращает очередное значение массива. Если метод hasNext
             * возвращает true элемент берется из текущей строки, если
             * false то счетчик строк увеличивается и перербор начинается с
             * первого элемента (col = 0).
             *
             * @return очередное значение массива
             */
            @Override
            public T next() {
                T value;
                if (this.hasNext()) {
                    value = values[row][col++];
                } else {
                    col = 0;
                    value = values[++row][col++];
                }
                return value;
            }
        };
    }
}
