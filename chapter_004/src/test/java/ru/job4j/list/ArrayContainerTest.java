package ru.job4j.list;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

/**
 * Тесты для проверки работы контейнера ArrayContainer
 *
 * @author Pyotr Kukharenka
 * @since 18.12.2017
 */
public class ArrayContainerTest {
    /**
     * Првоеряем что размер массива корректно увеличивается при
     * добавлении более 2 элементов (ставим начальный размер массива 2)
     */
    @Test
    public void whenAddMoreThanCapacityThenNewCapacity() {
        ArrayContainer<Integer> list = new ArrayContainer<>(2);
        list.add(1);
        list.add(2);
        list.add(3);
        assertThat(list.size(), is(3));
    }

    /**
     * Проверяем, что метод корректно возвращает значение
     * элемента под индексом 0.
     */
    @Test
    public void whenAddElemAndGetHimThenElementValueIs1() {
        ArrayContainer<Integer> list = new ArrayContainer<>(2);
        list.add(1);
        assertThat(list.get(0), is(1));
    }

    /**
     * Проверяем что контейнер корректно отрабатывает
     * со строковыми значениями.
     */
    @Test
    public void whenAddStringTypeThenAllCorrect() {
        ArrayContainer<String> list = new ArrayContainer<>(2);
        list.add("test");
        assertThat(list.size(), is(1));
    }

    /**
     * Првоеряем что при добавлении 2 элементов метод size
     * возвращает размер 2, а не размер массива - 4.
     */
    @Test
    public void whenAddOneElemThenSizeIsOne() {
        ArrayContainer<String> list = new ArrayContainer<>(4);
        list.add("test1");
        list.add("test2");
        assertThat(list.size(), is(2));
    }

    /**
     * Првоеряем, что метод contains возвращет true если такой объект
     * содержится в контейнере.
     */
    @Test
    public void whenAddElementAndCheckContainsItThenTrue() {
        ArrayContainer<String> list = new ArrayContainer<>(4);
        list.add("test1");
        assertThat(list.contains("test1"), is(true));
    }
    /**
     * Првоеряем, что метод contains возвращет false если такой объект
     * не содержится в контейнере.
     */
    @Test
    public void whenAddElementAndCheckContainsAnotherValueThenFalse() {
        ArrayContainer<Integer> list = new ArrayContainer<>(4);
        list.add(2);
        assertThat(list.contains(3), is(false));
    }

}