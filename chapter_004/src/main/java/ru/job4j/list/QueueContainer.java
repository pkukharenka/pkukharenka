package ru.job4j.list;

/**
 * Класс реализует контенйнер типа очередь. Очередь
 * реализуется по принципу FIFO (первый вошел - первый вышел).
 * Класс расширяет класс LinkedContainer.
 *
 * @author Pyotr Kukharenka
 * @see LinkedContainer
 * @since 19.12.2017
 */

public class QueueContainer<E> extends LinkedContainer<E> {
    /**
     * Количество элементов в очереди.
     */
    private int size;

    /**
     * Пустой конструктор
     */
    public QueueContainer() {
    }

    /**
     * Добавляет новый элемент в конец списка.
     *
     * @param value - новый элемент.
     */
    public void push(E value) {
        super.add(value);
        this.size++;
    }

    /**
     * Возвращает значение первого элемента в списке и
     * удаляет ссылку на него (другими словами удаляет его из списка)
     *
     * @return - значение первого элемента в списке
     */
    public E poll() {
        E element = null;
        if (super.searchNode(0) != null) {
            element = super.unlinkFirst();
        }
        this.size--;
        return element;
    }

    /**
     * Возвращает размер списка.
     *
     * @return - размер списка
     */
    @Override
    public int size() {
        return this.size;
    }
}
