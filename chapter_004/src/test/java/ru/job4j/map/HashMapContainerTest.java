package ru.job4j.map;

import org.junit.Test;

import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class HashMapContainerTest {

    private User first = new User("petr", 3, new GregorianCalendar(2017, 3, 1));
    private User duplicate = new User("petr", 3, new GregorianCalendar(2017, 3, 1));
    private User second = new User("ivan", 1, new GregorianCalendar(2015, 2, 15));
    private User third = new User("artem", 2, new GregorianCalendar(2007, 6, 24));


    /**
     * Проверяем что объект добавляется в контейнер.
     */
    @Test
    public void whenAddOneElemThenSizeOne() {
        HashMapContainer<User, Integer> map = new HashMapContainer<>();
        map.insert(this.first, 1);
        assertThat(map.size(), is(1));
    }

    /**
     * Проверяем что дубликаты не добавляется в контейнер.
     */
    @Test
    public void whenAddDuplicateThenSizeOne() {
        HashMapContainer<User, Integer> map = new HashMapContainer<>();
        map.insert(this.first, 1);
        map.insert(this.duplicate, 2);
        assertThat(map.size(), is(1));
    }

    /**
     * Проверяем что поиск элемента по значение отрабатывает
     * корректно.
     */
    @Test
    public void whenCheckToContainsElementThenTrue() {
        HashMapContainer<User, Integer> map = new HashMapContainer<>();
        map.insert(this.first, 1);
        map.insert(this.second, 3);
        assertThat(map.get(this.first), is(1));
        assertThat(map.get(this.second), is(3));
        assertNull(map.get(this.third));
    }

    /**
     * Проверяем что удаление объекта из контейнера проходит
     * корректно.
     */
    @Test
    public void whenRemoveElementThenTrue() {
        HashMapContainer<User, Integer> map = new HashMapContainer<>();
        map.insert(this.first, 1);
        map.insert(this.second, 2);
        assertThat(map.delete(this.second), is(true));
        assertThat(map.size(), is(1));

    }

    /**
     * Првоеряем, что если такого объекта для удаления нет в
     * контейнере, то вернется false.
     */
    @Test
    public void whenRemoveElementThatNotPresentThenFalse() {
        HashMapContainer<User, Integer> map = new HashMapContainer<>();
        map.insert(this.first, 1);
        map.insert(this.second, 2);
        assertThat(map.delete(this.third), is(false));

    }

    /**
     * Проверяем что массив увеличивается.
     */
    @Test
    public void whenAddMoreThenDefaultCapacity16ThenNextAddWillOk() {
        HashMapContainer<String, Integer> map = new HashMapContainer<>();
        for (int i = 1; i <= 20; i++) {
            map.insert(String.valueOf(i), i);
        }
        assertThat(map.size(), is(20));
    }

    /**
     * Проверяем что итератор работает корректно.
     */
    @Test(expected = NoSuchElementException.class)
    public void whenThen() {
        HashMapContainer<User, Integer> map = new HashMapContainer<>();
        map.insert(this.first, 1);
        map.insert(this.second, 2);
        map.insert(this.third, 2);
        Iterator<User> it = map.iterator();
        assertThat(it.hasNext(), is(true));
        it.next();
        assertThat(it.hasNext(), is(true));
        it.next();
        assertThat(it.hasNext(), is(true));
        it.next();
        assertThat(it.hasNext(), is(false));
        it.next();
    }
}