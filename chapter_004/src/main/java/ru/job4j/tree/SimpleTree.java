package ru.job4j.tree;

import java.util.Optional;

/**
 * Интерфейс древовидной структуры.
 *
 * @author Pyotr Kukharenka
 * @since 26.12.2017
 */
public interface SimpleTree<E extends Comparable<E>> extends Iterable<E> {
    /**
     * Возвращает true если элемент добавлен в дерево.
     * Дубликаты в дерево не добавляются.
     *
     * @param parent - элемент родитель
     * @param child  - добавляемый элемент.
     * @return - true если элемент добавлен в дерево
     */
    boolean add(E parent, E child);
    /**
     * Возвращает ненулевой узел, найденный по значению на
     * основе алгоритма посика по ширине дерева.
     *
     * @param value - искомое значение.
     * @return - ненулевой узел.
     */
    Optional<Node<E>> findBy(E value);
}
