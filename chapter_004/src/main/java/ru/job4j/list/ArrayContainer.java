package ru.job4j.list;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Потокобезопасная реализация динамического контейнера, позволяющего
 * хранить любые объекты.
 *
 * @author Pyotr Kukharenka
 * @since 18.12.2017
 */

@ThreadSafe
public class ArrayContainer<E> implements ListContainer<E> {
    /**
     * Размер контейнера по умолчанию
     */
    private static final int INIT_CAPACITY = 10;
    /**
     * Массив для хранения элементов.
     */
    @GuardedBy("this")
    private Object[] array;
    /**
     * Количество элементов в контейнере.
     */
    private int size = 0;

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
    public synchronized void add(E value) {
        this.checkCapacity(this.size + 1);
        this.array[this.size++] = value;
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
    private synchronized void checkCapacity(int capacity) {
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
    public synchronized E get(int index) throws IndexOutOfBoundsException {
        if (index < this.size) {
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
        return this.size;
    }

    /**
     * Возвращает true если контейнер содержит элемент value
     *
     * @param value - элемент для проверки наличия его в контейнере.
     * @return - true если контейнер содержит элемент value.
     */
    @Override
    public synchronized boolean contains(E value) {
        boolean flag = false;
        for (Object o : this.array) {
            if (o != null && o.equals(value)) {
                flag = true;
                break;
            }
        }
        return flag;
    }

    /**
     * Возвращает итератор по элементам контейнера.
     *
     * @return - возвращает итератор по элементам контейнера
     */
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
                return this.pos != size;
            }

            /**
             * {@inheritDoc}
             */
            @Override
            public E next() {
                if (this.pos > size) {
                    throw new NoSuchElementException("No more elements");
                }
                return (E) array[pos++];
            }
        };
    }
}
