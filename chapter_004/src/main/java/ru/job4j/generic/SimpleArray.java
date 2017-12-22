package ru.job4j.generic;

import java.util.Iterator;

/**
 * Пераметризованный контейнер. Реализует интерйфейс Iterable, что
 * позволит перебирать элементы контейнера с помощью foreach или
 * реализованного итератора.
 *
 * @author Pyotr Kukharenka
 * @see Iterable
 * @since 18.12.2017
 */

public class SimpleArray<T> implements Iterable<T> {
    /**
     * Индекс текущей ячейки массива.
     */
    private int index;
    /**
     * Массив для хранения объектов.
     */
    private Object[] array;

    /**
     * Конструктор, инициализирущий массив с размеров
     * size.
     *
     * @param size - размер массива.
     */
    public SimpleArray(int size) {
        this.array = new Object[size];
    }

    /**
     * Добавление элемента в массив. После каждого
     * добавления значение индекса увеличивается.
     *
     * @param value - элемент типа Т.
     */
    public void add(T value) {
        this.array[index++] = value;
    }

    /**
     * Удаление элемента из массива по индексу. Удаление
     * элемента осуществляется за счет копирования всех
     * элементов текущего массива с позиции index + 1 в
     * этот же массив начиная с index (другими словами
     * смещение всех элементов массива на единицу влево),
     * при этом последний элемент затирается.
     *
     * @param index - индекс ячейки для удаления.
     */
    public void delete(int index) {
        System.arraycopy(this.array, index + 1, this.array, index, this.array.length - index - 1);
        this.array[--this.index] = null;
    }

    /**
     * Изменяет значение элемента в ячейке под индексом
     * index.
     *
     * @param index - номер ячейки для обновления
     * @param value - новое значение элемента
     */
    public void update(int index, T value) {
        this.array[index] = value;
    }

    /**
     * Возвращает значение из указанной ячейки.
     *
     * @param index - номер ячейки
     * @return - значение из указанной ячейки
     */

    //Uncheked cast
    public T get(int index) {
        return (T) this.array[index];
    }

    /**
     * Итератор для итерации элементов контейнера. Реализз
     *
     * @return итератор по элементам.
     */
    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            /**
             * Позиция указателя в при переборе
             * элементов контейнера
             */
            private int pos = 0;

            /**
             * {@inheritDoc}
             */
            @Override
            public boolean hasNext() {
                return pos != array.length;
            }
            /**
             * {@inheritDoc}
             */
            @Override
            public T next() {
                return (T) array[pos++];
            }
        };
    }
}

