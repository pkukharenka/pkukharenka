package ru.job4j.tree;

import java.util.*;

/**
 * Древовидная структура для хранения элементов.
 *
 * @author Pyotr Kukharenka
 * @see SimpleTree
 * @since 26.12.2017
 */

public class Tree<E extends Comparable<E>> implements SimpleTree<E> {
    /**
     * Корневой элемент дерева.
     */
    private Node<E> root;

    /**
     * Конструктор инициализирующий дерево с его корневым
     * элементом.
     *
     * @param value - корневой элемент.
     */
    public Tree(E value) {
        this.root = new Node<>(value);
    }

    /**
     * Возвращает true если элемент добавлен в дерево.
     * Дубликаты в дерево не добавляются.
     *
     * @param parent - элемент родитель
     * @param child  - добавляемый элемент.
     * @return - true если элемент добавлен в дерево
     * @throws InvalidRootException - выбрасывается, если родитель в дереве
     *                              не найден.
     */
    @Override
    public boolean add(E parent, E child) {
        boolean flag = false;
        Optional<Node<E>> par = this.findBy(parent);
        if (par.isPresent()) {
            Node<E> e = par.get();
            if (!e.childs().contains(child)) {
                e.add(new Node<>(child));
                flag = true;
            }
        } else {
            throw new InvalidRootException("Такого родителя нет в дереве.");
        }
        return flag;
    }

    /**
     * Возвращает ненулевой узел, найденный по значению на
     * основе алгоритма посика по ширине дерева. Структура
     * очереди позволяет поочередно извлекать элементы
     * каждого уровня дерева. Если узел найден, выходим
     * из цикла.
     *
     * @param value - искомое значение.
     * @return - ненулевой узел.
     */
    @Override
    public Optional<Node<E>> findBy(E value) {
        Optional<Node<E>> res = Optional.empty();
        Deque<Node<E>> data = new LinkedList<>();
        data.offer(this.root);
        while (!data.isEmpty()) {
            Node<E> e = data.poll();
            if (e.eqValue(value)) {
                res = Optional.of(e);
                break;
            }
            for (Node<E> child : e.childs()) {
                data.offer(child);
            }
        }
        return res;
    }

    /**
     * Возвращает true если дерево бинарное.
     *
     * @return - true если дерево бинарное.
     */
    public boolean isBinary() {
        boolean flag = true;
        Deque<Node<E>> data = new LinkedList<>();
        data.offer(this.root);
        while (!data.isEmpty()) {
            Node<E> next = data.poll();
            if (next.childs().size() <= 2) {
                for (Node<E> e : next.childs()) {
                    data.offer(e);
                }
            } else {
                flag = false;
                break;
            }
        }
        return flag;
    }

    /**
     * Возвращает итератор по элементам дерева.
     *
     * @return - итератор по элементам дерева.
     */
    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            Deque<Node<E>> data = new LinkedList<>();

            {
                this.data.offer(root);
            }

            Node<E> next;

            /**
             * Возвращает true, если количество элементов в очереди
             * больше 0.
             *
             * @return - true, если количество элементов в очереди больше 0.
             */
            @Override
            public boolean hasNext() {
                return this.data.size() > 0;
            }

            /**
             * Возвращает очередной элемент дерева.
             * @return - очередной элемент дерева.
             * @throws NoSuchElementException - выбрасывается если отсутствует элемент
             * для возврата
             */
            @Override
            public E next() {
                this.next = this.data.poll();
                if (this.next != null) {
                    if (data.isEmpty()) {
                        for (Node<E> child : next.childs()) {
                            this.data.offer(child);
                        }
                    }
                    return next.getValue();
                } else {
                    throw new NoSuchElementException();
                }
            }
        };
    }
}
