package ru.job4j.tree;

import java.util.Deque;
import java.util.LinkedList;

/**
 * Бинарное дерево поиска.
 *
 * @author Pyotr Kukharenka
 * @since 30.12.2017
 */

public class BinaryTree<E extends Comparable<E>> {
    /**
     * Корень дерева.
     */
    private TreeNode<E> root;
    /**
     * Количество элементов в дереве.
     */
    private int size;

    /**
     * Инициализации начального размера дерева.
     */
    public BinaryTree() {
        this.size = 0;
    }

    /**
     * Добавление нового значения в дерево.
     * 1. Если приходит нулевой узел -> значит элементовнет,
     * добавляем корень.
     * 2. Если значение для добавления меньше либо равно значению полученного
     * родительского узла -> добавляем в левое поддерево.
     * 3. В остальных случаях в правое поддерево
     *
     * @param e - значение для добавления
     */
    public void add(E e) {
        TreeNode<E> node = this.search(this.root, e);
        if (node == null) {
            this.root = new TreeNode<>(e);
        } else if (e.compareTo(node.value) <= 0) {
            node.left = new TreeNode<>(e);
        } else {
            node.right = new TreeNode<>(e);
        }
        this.size++;
    }

    /**
     * Возвращает нижний ненулевой родительский узел, в который
     * будет добавлен новый узел.
     *
     * @param node - узел для поиска.
     * @param e    - значение для добавления
     * @return - нижний ненулевой родительский узел.
     */
    private TreeNode<E> search(TreeNode<E> node, E e) {
        Deque<TreeNode<E>> data = new LinkedList<>();
        data.offer(node);
        TreeNode<E> current = null;
        while (!data.isEmpty()) {
            current = data.poll();
            if (current == null) {
                break;
            }
            if (e.compareTo(current.value) <= 0 && current.left != null) {
                data.offer(current.left);
            } else if (e.compareTo(current.value) > 0 && current.right != null) {
                data.offer(current.right);
            }
        }
        return current;
    }

    /**
     * Возвращает количество элементов в дереве.
     *
     * @return - размер дерева.
     */
    public int size() {
        return this.size;
    }

    /**
     * Возвращает корневой элемент.
     *
     * @return - корневой элемент.
     */
    public TreeNode<E> getRoot() {
        return root;
    }

    /**
     * Узел бинарного дерева.
     *
     * @param <E> - параметризованный тип
     */
    class TreeNode<E> {
        /**
         * Значение узла.
         */
        private E value;
        /**
         * Указатель на левый дочерний элемент.
         */
        private TreeNode<E> left;
        /**
         * Указатель на правый дочерний элемент.
         */
        private TreeNode<E> right;

        /**
         * Конструктор для инициализации узла.
         *
         * @param value - значение узла.
         */
        public TreeNode(E value) {
            this.value = value;

        }

        //Геттеры
        public E getValue() {
            return value;
        }

        public TreeNode<E> getLeft() {
            return left;
        }

        public TreeNode<E> getRight() {
            return right;
        }
    }
}
