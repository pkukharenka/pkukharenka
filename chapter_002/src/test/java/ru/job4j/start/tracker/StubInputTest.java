package ru.job4j.start.tracker;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

/**
 * Тестирование работы программы Tracker с использованием заглушки системы
 * ввода/вывода
 *
 * @author Pyotr Kukharenka
 * @since 02.12.2017
 */
public class StubInputTest {
    /**
     * Объекты для тестов.
     */
    private static final Item FIRST = new Item("test1", "testDescription", 123L);
    private static final Item SECOND = new Item("test2", "testDescription2", 1234L);

    /**
     * Тестирование добавления новой заявки.
     */
    @Test
    public void whenAddNewItemThenName() {
        Tracker tracker = new Tracker();
        Input input = new StubInput(new String[]{"1", "name1", "desc1", "7"});
        new StartUI(input, tracker).init();
        assertThat(tracker.findAll()[0].getName(), is("name1"));
    }

    /**
     * Тестирование обновления существующей заявки по ID.
     * Добавляем новую заявку, находим ее по ID и меняем имя автора
     * и опсание. Сравниваем имя автора заявки.
     */
    @Test
    public void whenUpdateItemThenItemHasNewName() {
        Tracker tracker = new Tracker();
        Item item = tracker.add(FIRST);
        Input input = new StubInput(new String[]{"3", item.getId(), "name2", "desc2", "2", "7"});
        new StartUI(input, tracker).init();
        assertThat(tracker.findAll()[0].getName(), is("name2"));
    }

    /**
     * Тестирование отображения всех существующиз заявок.
     * Добавляем 2 заявки напрямую через Tracker. Сравниваем
     * длину храшилища.
     */
    @Test
    public void whenShowAllItemsThenCountIsTwo() {
        Tracker tracker = new Tracker();
        tracker.add(FIRST);
        tracker.add(SECOND);
        Input input = new StubInput(new String[]{"2", "7"});
        new StartUI(input, tracker).init();
        assertThat(tracker.findAll().length, is(2));
    }

    /**
     * Тестирование удаления заявки. Добавляем две заявки
     * Удаляем заявку по ID. Проверяем, что заявка по этим индексом
     * равна null.
     */
    @Test
    public void whenAddTwoItemsAndDeleteOneThenLenghtOne() {
        Tracker tracker = new Tracker();
        Item item1 = tracker.add(FIRST);
        Item item2 = tracker.add(SECOND);
        Input input = new StubInput(new String[]{"4", item1.getId(), "yes", "2", "7"});
        new StartUI(input, tracker).init();
        assertNull(tracker.findAll()[0]);
    }

    /**
     * Тестирование поиска заявки по ID. Добавляем новую заявку
     * Производим поиск по ее ID, сравниваем имена авторов.
     */
    @Test
    public void whenFindByIdThenItemHasSameName() {
        Tracker tracker = new Tracker();
        Item item = tracker.add(FIRST);
        Input input = new StubInput(new String[]{"5", item.getId(), "7"});
        new StartUI(input, tracker).init();
        assertThat(tracker.findById(item.getId()).getName(), is("test1"));
    }

    /**
     * Тестирование поиска авторов заявок по имени.
     * Добавляем две заявки, по ключу производим поиск.
     * Длина найденного массива длолжна соответствовать
     * количеству заявок с ключом.
     */
    @Test
    public void whenFindByNameTestThenArrayLenghtTwo() {
        Tracker tracker = new Tracker();
        Item item1 = tracker.add(FIRST);
        Item item2 = tracker.add(SECOND);
        String key = "test";
        Input input = new StubInput(new String[]{"6", key, "7"});
        new StartUI(input, tracker).init();
        assertThat(tracker.findByName(key).length, is(2));
    }
}