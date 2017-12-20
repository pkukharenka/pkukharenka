package ru.job4j.list;

/**
 * Алгоритм поиска зацикленнсти в односвязном списке.
 *
 * @author Pyotr Kukharenka
 * @since 20.12.2017
 */

public class CycleCheck {
    /**
     * Возвращает true, если в списке есть зацикленность. Логика
     * алгоритма заключается в двух указателей, один из которых
     * на один узел опережает первый. В случае если петля есть
     * эти указатели встретяться в петле. В противном случае,
     * если петли нет, то у последнего узла указаетль на следующий
     * равен null - > выход из цикла.
     *
     * @param first - первый узел связного списка.
     * @return - возвращает true, если в списке есть зацикленность
     */
    public boolean hasCycle(Node first) {
        boolean flag = false;
        Node fast = first;
        Node slow = first;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                flag = true;
                break;
            }
        }
        return flag;
    }
}

/**
 * Узел связного списка.
 *
 * @param <T> - тип значание узла.
 */
class Node<T> {
    /**
     * Значение узла.
     */
    T value;
    /**
     * Указатель на следующий элемент списка
     */
    Node<T> next;

    /**
     * Конструктор.
     *
     * @param value - значение ущла.
     */
    public Node(T value) {
        this.value = value;
    }
}