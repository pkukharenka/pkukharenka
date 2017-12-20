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

    /**
     * Проверяем что добавив два элемента и удалив указатели на
     * первый элемент, первым элементов станет второй элемент
     */
    @Test
    public void whenAddTwoElementsAndUnlinkThenFirstElementIsSecond() {
        LinkedContainer<Integer> list = new LinkedContainer<>();
        list.add(1);
        list.add(2);
        list.unlinkFirst();
        assertThat(list.get(0), is(2));
    }

    /**
     * Проверяем что при вызове метода unlinkFirst размер списка
     * уменьшается на 1.
     */
    @Test
    public void whenAddThreeAndUnlinkThenSizeTwo() {
        LinkedContainer<Integer> list = new LinkedContainer<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.unlinkFirst();
        assertThat(list.size(), is(2));
    }

    /**
     * Проверяем что метод addFirst добавляет элемент
     * первым в список.
     */
    @Test
    public void whenAddFirstThenFirstIs5() {
        LinkedContainer<Integer> list = new LinkedContainer<>();
        list.add(1);
        list.addFirst(5);
        assertThat(list.get(0), is(5));
    }
    /**
     * Првоеряем, что метод contains возвращет true если такой объект
     * содержится в контейнере.
     */

    @Test
    public void whenAddElementAndCheckContainsItThenTrue() {
        LinkedContainer<String> list = new LinkedContainer<>();
        list.add("test1");
        assertThat(list.contains("test1"), is(true));
    }
    /**
     * Првоеряем, что метод contains возвращет false если такой объект
     * не содержится в контейнере.
     */
    @Test
    public void whenAddElementAndCheckContainsAnotherValueThenFalse() {
        LinkedContainer<Integer> list = new LinkedContainer<>();
        list.add(2);
        assertThat(list.contains(3), is(false));
    }
}