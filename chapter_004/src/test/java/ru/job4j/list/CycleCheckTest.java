package ru.job4j.list;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

/**
 * Тесты для проверки работы алгоритма поиска зацикленности
 * в связном списке
 *
 * @author Pyotr Kukharenka
 * @since 20.12.2017
 */
public class CycleCheckTest {
    /**
     * Првоеряем что если есть зацикленность вернется true.
     * (петля в середине списка 2-3 элементы)
     */
    @Test
    public void whenLinkedListHasCycleInCenterThenTrue() {
        CycleCheck cycle = new CycleCheck();
        Node<Integer> first = new Node<>(1);
        Node<Integer> second = new Node<>(2);
        Node<Integer> third = new Node<>(3);
        Node<Integer> four = new Node<>(4);

        first.next = second;
        second.next = third;
        third.next = second;
        four.next = first;

        assertThat(cycle.hasCycle(first), is(true));
    }

    /**
     * Првоеряем что если есть зацикленность вернется true.
     * (петля на границе списка списка 1 и 3 элементы)
     */
    @Test
    public void whenLinkedListHasCycleOnBorderThenTrue() {
        CycleCheck cycle = new CycleCheck();
        Node<Integer> first = new Node<>(1);
        Node<Integer> second = new Node<>(2);
        Node<Integer> third = new Node<>(3);

        first.next = second;
        second.next = third;
        third.next = first;

        assertThat(cycle.hasCycle(first), is(true));
    }

    /**
     * Првоеряем что если нет зацикленности вернется false.
     */
    @Test
    public void whenLinkedListHasNotCycleThenFalse() {
        CycleCheck cycle = new CycleCheck();
        Node<Integer> first = new Node<>(1);
        Node<Integer> second = new Node<>(2);
        Node<Integer> third = new Node<>(3);
        Node<Integer> four = new Node<>(4);

        first.next = second;
        second.next = third;
        third.next = four;
        four.next = null;

        assertThat(cycle.hasCycle(first), is(false));
    }

    /**
     * Првоеряем что если есть зацикленность вернется true.
     * (петля в середине списка 2-5 элементы)
     */
    @Test
    public void whenLsNotCycleThenFalse() {
        CycleCheck cycle = new CycleCheck();
        Node<Integer> first = new Node<>(1);
        Node<Integer> second = new Node<>(2);
        Node<Integer> third = new Node<>(3);
        Node<Integer> four = new Node<>(4);
        Node<Integer> five = new Node<>(5);
        Node<Integer> six = new Node<>(6);


        first.next = second;
        second.next = third;
        third.next = four;
        four.next = five;
        five.next = second;
        six.next = null;

        assertThat(cycle.hasCycle(first), is(true));
    }

}