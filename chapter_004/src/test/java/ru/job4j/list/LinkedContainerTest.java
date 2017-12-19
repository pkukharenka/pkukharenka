package ru.job4j.list;

import org.junit.Test;
import java.util.Iterator;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

/**
 * Тесты для проверки работы контейнера на базе связных списков
 *
 * @author Pyotr Kukharenka
 * @since 19.12.2017
 */
public class LinkedContainerTest {
    /**
     * Проверяем что при добавлении 3 элементов
     * размер списка будет 3.
     */
    @Test
    public void whenAddThreeElemThenSizeThree() {
        LinkedContainer<Integer> list = new LinkedContainer<>();
        list.add(1);
        list.add(2);
        list.add(3);
        assertThat(list.size(), is(3));
    }

    /**
     * Проверяем что при добавлении 5 элементов, по индексу 3
     * контейнер вернет значение 4.
     */
    @Test
    public void whenAddStringAndGetThemThenHello() {
        LinkedContainer<String> list = new LinkedContainer<>();
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");
        list.add("5");
        String res = list.get(3);
        assertThat(res, is("4"));
    }

    /**
     * Проверяем что итератор на второй итерации вернет второе значение.
     */
    @Test
    public void whenAddThreeElemAndIterateTwoTimeThenTwo() {
        LinkedContainer<Integer> list = new LinkedContainer<>();
        list.add(1);
        list.add(2);
        list.add(3);
        Iterator<Integer> it = list.iterator();
        it.next();
        assertThat(it.next(), is(2));

    }

    /**
     * Првоеряем коректность определения наличия следующего элемента
     * в контейнере, после итерации единсвтенного элемента hasNext
     * вернет false.
     */
    @Test
    public void whenNoMoreItemToIterateThenFalse() {
        LinkedContainer<Integer> list = new LinkedContainer<>();
        list.add(1);
        Iterator<Integer> it = list.iterator();
        it.next();
        assertThat(it.hasNext(), is(false));

    }
}