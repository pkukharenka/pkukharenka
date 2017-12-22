package ru.job4j.set;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

/**
 * Тесты для првоерки работы функционала контейнера на базе
 * хэш-таблицы.
 *
 * @author Pyotr Kukharenka
 * @since 22.12.2018
 */
public class HashSetContainerTest {
    /**
     * Проверяем что объект добавляется в контейнер.
     */
    @Test
    public void whenAddOneElemThenSizeOne() {
        HashSetContainer<String> set = new HashSetContainer<>();
        set.add("1");
        assertThat(set.size(), is(1));
    }

    /**
     * Проверяем что дубликаты не добавляется в контейнер.
     */
    @Test
    public void whenAddDuplicateThenSizeOne() {
        HashSetContainer<Integer> set = new HashSetContainer<>();
        set.add(1);
        set.add(1);
        assertThat(set.size(), is(1));
    }

    /**
     * Проверяем что поиск элемента по значение отрабатывает
     * корректно.
     */
    @Test
    public void whenCheckToContainsElementThenTrue() {
        HashSetContainer<Integer> set = new HashSetContainer<>();
        set.add(1);
        set.add(2);
        assertThat(set.contains(2), is(true));
    }

    /**
     * Проверяем что удаление объекта из контейнера проходит
     * корректно.
     */
    @Test
    public void whenRemoveElementThenTrue() {
        HashSetContainer<Integer> set = new HashSetContainer<>();
        set.add(1);
        set.add(2);
        assertThat(set.remove(1), is(true));

    }

    /**
     * Првоеряем, что если такого объекта для удаления нет в
     * контейнере, то вернется false.
     */
    @Test
    public void whenRemoveElementThatNotPresentThenFalse() {
        HashSetContainer<Integer> set = new HashSetContainer<>();
        set.add(1);
        set.add(2);
        assertThat(set.remove(5), is(false));

    }

    /**
     * Проверяем что массив увеличивается.
     */
    @Test
    public void whenAddMoreThenDefaultCapacity16ThenNextAddWillOk() {
        HashSetContainer<Integer> set = new HashSetContainer<>();
        for (int i = 1; i <= 20; i++) {
            set.add(i);
        }
        assertThat(set.size(), is(20));

    }

    /**
     * Проверяем что при добавлении null выбрасывается
     * ошибка NullPointerException
     */
    @Test(expected = NullPointerException.class)
    public void whenAddNullThenException() {
        HashSetContainer<Integer> set = new HashSetContainer<>();
       set.add(null);

    }
}