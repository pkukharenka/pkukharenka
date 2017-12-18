package ru.job4j.generic;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

/**
 * Тесты для проверки методов контейнера SimpleArray
 *
 * @author Pyotr Kukharenka
 * @since 18.12.2017
 */
public class SimpleArrayTest {
    /**
     * Проверям что при добавлении 5 в контейнер, первый его
     * элемент будет 5
     */
    @Test
    public void whenAddIntThenFirstIs5() {
        SimpleArray<Integer> array = new SimpleArray<>(4);
        array.add(5);
        assertThat(array.get(0), is(5));
    }

    /**
     * Проверям что при добавлении строкового значения в контейнер, первый его
     * элемент будет это значение.
     */
    @Test
    public void whenAddStringThenFirstIsString() {
        SimpleArray<String> array = new SimpleArray<>(4);
        array.add("hello");
        assertThat(array.get(0), is("hello"));
    }

    /**
     * Проверяем что при добавлении одного элемента в контейнер
     * а затем его удаления, значение этой ячейки будет null.
     */
    @Test
    public void whenDeleteIntThenLenght() {
        SimpleArray<Integer> array = new SimpleArray<>(4);
        array.add(5);
        array.delete(0);
        assertNull(array.get(0));
    }

    /**
     * Проверяем что при добалвении элемента в контейнер, а затем
     * изменения значения этого элемента, метод get вернет новое
     * значение.
     */
    @Test
    public void whenUpdateThenNewString() {
        SimpleArray<String> array = new SimpleArray<>(4);
        array.add("hello");
        array.update(0, "new String");
        assertThat(array.get(0), is("new String"));
    }

}
