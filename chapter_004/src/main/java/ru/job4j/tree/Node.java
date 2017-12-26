package ru.job4j.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * Узел древовидной структуры.
 *
 * @author Pyotr Kukharenka
 * @since 26.12.2017
 */

public class Node<E> {
    /**
     * Значение узла.
     */
    private final E value;
    /**
     * Дети узла.
     */
    private final List<Node<E>> list = new ArrayList<>();

    /**
     * Конструктор, инициализирующий узел.
     *
     * @param value - значение узла.
     */
    public Node(E value) {
        this.value = value;
    }

    /**
     * Добавление нового ребенка.
     *
     * @param child - значение ребенка.
     */
    public void add(Node<E> child) {
        this.list.add(child);
    }

    /**
     * Возвращает список детей данного узла.
     *
     * @return - список детей данного узла.
     */
    public List<Node<E>> childs() {
        return this.list;
    }

    /**
     * Возвращает true, если значение текущего узла
     * равно передаваемому значению.
     *
     * @param that - значение для проверки.
     * @return - true, если значения равны.
     */
    public boolean eqValue(E that) {
        return this.value.equals(that);
    }

    /**
     * Возвращает значение текущего узла.
     *
     * @return - значение текущего узла.
     */
    public E getValue() {
        return value;
    }
}
