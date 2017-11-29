package ru.job4j.tracker;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.isEmptyOrNullString;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.*;

/**
 * Класс для тестирования трекера
 *
 * @author Pyotr Kukharenka
 * @since 29.11.2017
 */
public class TrackerTest {
    private static final Item FIRST = new Item("test1", "testDescription", 123L);
    private static final Item SECOND = new Item("test2", "testDescription2", 1234L);

    /**
     * Тест добавления нового объекта.
     */
    @Test
    public void whenAddItemThenReturnItemName() {
        Tracker tracker = new Tracker();
        tracker.add(FIRST);
        assertThat(tracker.findById(FIRST.getId()).getName(), is("test1"));
    }

    /**
     * Тест обновления объекта в массиве.
     */
    @Test
    public void whenUpdateNameThenReturnNewName() {
        Tracker tracker = new Tracker();
        tracker.add(FIRST);
        SECOND.setId(FIRST.getId());
        tracker.update(SECOND);
        assertThat(tracker.findById(FIRST.getId()).getName(), is("test2"));
    }

    /**
     * Тест удаления объекта из массива.
     */
    @Test
    public void whenDeleteItemThenItemEqualsNull() {
        Tracker tracker = new Tracker();
        tracker.add(FIRST);
        tracker.add(SECOND);
        tracker.delete(FIRST);
        assertNull(tracker.findAll()[0]);
    }

    /**
     * Тест поиска всех объектов массива.
     */
    @Test
    public void whenFindAllItemsThenTwo() {
        Tracker tracker = new Tracker();
        tracker.add(FIRST);
        tracker.add(SECOND);
        tracker.findAll();
        assertThat(tracker.findAll().length, is(2));
    }

    /**
     * Тест поиска объекта по его Id.
     */
    @Test
    public void whenFindByIdThenReturnItemName() {
        Tracker tracker = new Tracker();
        tracker.add(FIRST);
        tracker.add(SECOND);
        assertThat(tracker.findById(SECOND.getId()).getName(), is("test2"));
    }

    /**
     * Тест поиска объектов по имени.
     */
    @Test
    public void whenFindByNameTestThenEqualsTwo() {
        Tracker tracker = new Tracker();
        tracker.add(FIRST);
        tracker.add(SECOND);
        final String key = "est";
        assertThat(tracker.findByName(key).length, is(2));
    }

}