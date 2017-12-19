package ru.job4j.list;

import java.util.Arrays;
import java.util.Iterator;

/**
 * Реализация динамического контейнера, позволяющего
 * хранить любые объекты.
 *
 * @author Pyotr Kukharenka
 * @since 18.12.2017
 */

public class ArrayContainer<E> implements ListContainer<E> {
    /**
     * Размер контейнера по умолчанию
     */
    private static final int INIT_CAPACITY = 10;
    /**
     * Массив для хранения элементов.
     */
    private Object[] array;

    private int index = 0;

    /**
     * Конструктор по умолчанию создает контейнер для
     * хранения 10 элементов.
     */
    public ArrayContainer() {
        this.array = new Object[INIT_CAPACITY];
    }

    /**
     * Конструктор в котором можно задать начальную размерность
     * контейнера.
     *
     * @param capacity - рамер массива.
     */
    public ArrayContainer(final int capacity) {
        this.array = new Object[capacity];
    }

    /**
     * Метод добавляет новый элемент в контейнер. При этом изначально
     * с помощью метода checkCapacity проверяеся хватает ли текущей
     * размерности массива для добавления нового элемента.
     *
     * @param value - значение элемента
     */
    @Override
    public void add(E value) {
        this.checkCapacity(this.index + 1);
        this.array[this.index++] = value;
    }

    /**
     * Метод проверяет хватает ли места для нового элемента.
     * Если разница начальной размерности массива и увеличееной на единицу
     * размерности меньше нуля -> увеличиваем размерность в 3 раза, путем
     * создания нового массива с увеличееным размером и копирования туда
     * всех старых элементов.
     *
     * @param capacity - следующий индекс массива.
     */
    private void checkCapacity(int capacity) {
        int oldCapacity = this.array.length;
        if (oldCapacity - capacity < 0) {
            int newCapacity = oldCapacity * 3;
            this.array = Arrays.copyOf(this.array, newCapacity);
        }
    }

    /**
     * Метод возвращает элемент контейнера по указанному
     * индеку. Если индекс больше текущего метод выбрасывает
     * Exception.
     *
     * @param index - индекс ячейки контейнера.
     * @return - значение элемента по указанному индеку.
     * @throws IndexOutOfBoundsException - в случае если такого индекса
     *                                   в массиве нет.
     */
    @Override
    public E get(int index) throws IndexOutOfBoundsException {
        if (index < this.index) {
            return (E) this.array[index];
        } else {
            throw new IndexOutOfBoundsException("Вы вышли за пределы размерности массива.");
        }
    }

    /**
     * Возвращает размер контейнера
     *
     * @return - размер контейнера.
     */
    @Override
    public int size() {
        return this.index;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
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
                return this.pos != index;
            }

            /**
             * {@inheritDoc}
             */
            @Override
            public E next() {
                return (E) array[pos++];
            }
        };
    }
}
