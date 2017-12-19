package ru.job4j.list;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Контейнер для хранения данных на базе связного списка
 *
 * @author Pyotr Kukharenka
 * @since 19.12.2017
 */

public class LinkedContainer<E> implements ListContainer<E> {

    /**
     * Указатель на первый элемент контейнера.
     */
    private Node<E> first;
    /**
     * Указатель на последний элемент контейнера.
     */
    private Node<E> last;
    /**
     * Количество элементов в контейнере
     */
    private int size = 0;

    /**
     * Конструктор по умолчанию, создающий пустой контейнер.
     */
    public LinkedContainer() {
    }

    /**
     * Возвращает значение элемента, стоящего первым в списке
     * и удаляет на него ссылку (работает GC). Первым элементов
     * становиться следующим за ним. Выбрасывает ошибку если в
     * контейнере нет элементов
     *
     * @return - значение элемента, стоящего первым в списке
     * @throws NoSuchElementException - выбрасывается в случае отсутствия
     *                                элементов в списке
     */
    public E unlinkFirst() throws NoSuchElementException {
        if (this.first == null) {
            throw new NoSuchElementException("В списке нет элементов");
        }
        Node<E> temp = this.first;
        Node<E> next = temp.next;
        if (next != null) {
            this.first = next;
            next.prev = null;
        }
        this.size--;
        return temp.value;
    }

    /**
     * Метод добавляет новый эелемент в конец списка. При этом
     * если последний элемент списка - null, значит список пустой
     * и мы добавляем первый элемент, если не null, то меняем ссылку у
     * последнего элемента на очередной добавляемый элемент.
     *
     * @param value - значение элемента
     */
    @Override
    public void add(E value) {
        final Node<E> tempLast = this.last;
        final Node<E> newNode = new Node<>(this.last, value, null);
        this.last = newNode;
        if (tempLast == null) {
            first = newNode;
        } else {
            tempLast.next = newNode;
        }
        this.size++;
    }

    /**
     * Добавляет элемент первым в список, меняя ссылку
     * бывшего первого элемента на данный элемент. Если список
     * пустой выполняется обчное добавление (в конец списка)
     *
     * @param value - новый элемент.
     */
    public void addFirst(E value) {
        if (this.first == null) {
            this.add(value);
        }
        final Node<E> temp = this.first;
        final Node<E> newNode = new Node<>(null, value, temp);
        this.first = newNode;
        temp.prev = this.first;
        this.size++;
    }

    /**
     * Получение элемента из списка по индексу. Во-первых проверяем
     * валидность указанного индекса методом checkIndex (> 0 && < size),
     * Зная индекс и имея указателя на первый и последний элемент списка
     * мы можем определить с какой стороны ближе произвести итерацию, от первого
     * или последнего элемента.
     *
     * @param index - индекс ячейки контейнера.
     * @return
     */
    @Override
    public E get(int index) {
        checkIndex(index);
        return this.searchNode(index).value;
    }

    /**
     * Возвращает узел под указанным индексом. При этом поиск
     * осуществляется со сложностью O(N/2), т.к. в зависимости
     * от индекса поиск осуществялется либо с начала списка,
     * (если значение индекса меньше значения размерности списка
     * на 1 бит сдвинутого вправо.), либо с конца в противном случае.
     *
     * @param index - индекс элемента в контейнере
     * @return узел в контейнере под индексом index.
     */
    public Node<E> searchNode(int index) {
        Node<E> elem;
        if (index < (size >> 1)) {
            elem = this.first;
            for (int i = 0; i < index; i++) {
                elem = elem.next;
            }
        } else {
            elem = this.last;
            for (int i = --size; i > index; i--) {
                elem = elem.prev;
            }
        }
        return elem;
    }

    /**
     * Проверка валидности требуемого индекса. В случае отсуствия
     * индекса выбрасываем Exception.
     *
     * @param index - индекс для проверки
     * @throws IndexOutOfBoundsException - выбрасывается если индекс не в диапозоне
     *                                   списка
     */
    private void checkIndex(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index > this.size) {
            throw new IndexOutOfBoundsException("Неверный индекс элемента");
        }
    }

    /**
     * Возвращает количество элементов в списке.
     *
     * @return - количество элементов в списке
     */
    @Override
    public int size() {
        return this.size;
    }

    /**
     * Итератор для последовательного перербора элементов контейнера.
     *
     * @return - итератор.
     */
    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            /**
             * Указатель на последний возвращенный элемент методо next
             */
            private Node<E> lastReturn;
            /**
             * Указатель на очередной элемент контейнера
             */
            private Node<E> next = first;
            /**
             * Счетчик итерированных значений.
             */
            private int pos = 0;

            /**
             * Метод проверяет есть ли очередное значение
             * для возврщения.
             *
             * @return - true если значение есть.
             */
            @Override
            public boolean hasNext() {
                return this.pos != size;
            }

            /**
             * Возвращает очередное значение контейнера и сдвигает
             * указатель на оследующий элемент.
             *
             * @return - очередной элемент контейнера
             * @throws NoSuchElementException - выбрасывается если и элементов
             *                                  в контейнере больше нет.
             */
            @Override
            public E next() throws NoSuchElementException {
                if (!this.hasNext()) {
                    throw new NoSuchElementException("Больше нет элементов");
                }
                this.lastReturn = this.next;
                this.next = this.next.next;
                this.pos++;
                return this.lastReturn.value;
            }
        };
    }

    /**
     * Статический класс, описывающий узел связного списка.
     * Каждый узел имеет собственно значение, а также ссылку
     * на предыдущий и последующий элемент.
     *
     * @param <E> - тип узла.
     */
    public static class Node<E> {
        /**
         * Указатель на предыдущий узел.
         */
        Node<E> prev;
        /**
         * Значение узла.
         */
        E value;
        /**
         * Указатель на следующий узел.
         */
        Node<E> next;

        /**
         * Конструктор, инициализирующий узел.
         *
         * @param prev  - Указатель на предыдущий узел.
         * @param value - значение узла.
         * @param next  - Указатель на следующий узел.
         */
        public Node(Node<E> prev, E value, Node<E> next) {
            this.prev = prev;
            this.value = value;
            this.next = next;
        }
    }
}



