package ru.job4j.start.tracker;

import org.junit.Test;
import ru.job4j.start.tracker.io.Input;
import ru.job4j.start.tracker.io.StubInput;
import ru.job4j.start.tracker.models.Item;

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
    private static final Item THIRD = new Item("test3", "testDescription3", 122234L);

    /**
     * Тестирование добавления новой заявки.
     */
    @Test
    public void whenAddNewItemThenName() {
        Tracker tracker = new Tracker();
        Input input = new StubInput(new String[]{"0", "name1", "desc1", "6"});
        MenuTracker menu = new MenuTracker(input, tracker);
        new StartUI(input, tracker).init();
        assertThat(tracker.findAll()[0].getName(), is("name1"));
    }

    /**
     * Тестирование обновления существующей заявки по ID.
     * Добавляем две заявки, первую удаляем. Находим вторую заявку по ID и меняем имя автора
     * и опсание. Сравниваем имя автора заявки под индексом 0.
     */
    @Test
    public void whenDeleteFirstItemAndUpdateSecondThenItemHasNewName() {
        Tracker tracker = new Tracker();
        Item item1 = tracker.add(FIRST);
        Item item2 = tracker.add(SECOND);
        tracker.delete(item1);
        Input input = new StubInput(new String[]{"2", item2.getId(), "name2", "desc2", "6"});
        MenuTracker menu = new MenuTracker(input, tracker);
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
        Input input = new StubInput(new String[]{"1", "6"});
        MenuTracker menu = new MenuTracker(input, tracker);
        new StartUI(input, tracker).init();
        assertThat(tracker.findAll().length, is(2));
    }

    /**
     * Тестирование удаления заявки. Добавляем две заявки
     * Удаляем заявку первую добавленную заявку. Проверяем, что имя заявка по индексом 0
     * равна имени второй заявки.
     */
    @Test
    public void whenAddTwoItemsAndDeleteOneThenLenghtOne() {
        Tracker tracker = new Tracker();
        Item item1 = tracker.add(FIRST);
        tracker.add(SECOND);
        Input input = new StubInput(new String[]{"3", item1.getId(), "yes", "6"});
        MenuTracker menu = new MenuTracker(input, tracker);
        new StartUI(input, tracker).init();
        assertThat(tracker.findAll()[0].getName(), is("test2"));
    }

    /**
     * Тестирование поиска заявки по ID. Добавляем новую заявку
     * Производим поиск по ее ID, сравниваем имена авторов.
     */
    @Test
    public void whenFindByIdThenItemHasSameName() {
        Tracker tracker = new Tracker();
        Item item = tracker.add(FIRST);
        Input input = new StubInput(new String[]{"4", item.getId(), "6"});
        MenuTracker menu = new MenuTracker(input, tracker);
        new StartUI(input, tracker).init();
        assertThat(tracker.findById(item.getId()).getName(), is("test1"));
    }

    /**
     * Тестирование поиска авторов заявок по имени.
     * Добавляем две заявки, первую удаляем и добавляем еще одну заявку.
     * Производми поис по ключу key.
     * Длина найденного массива длолжна соответствовать
     * количеству заявок с ключом.
     */
    @Test
    public void whenFindByNameTestThenArrayLenghtTwo() {
        Tracker tracker = new Tracker();
        tracker.add(FIRST);
        tracker.add(THIRD);
        tracker.delete(FIRST);
        String key = "test";
        Input input = new StubInput(new String[]{"5", key, "6"});
        MenuTracker menu = new MenuTracker(input, tracker);
        new StartUI(input, tracker).init();
        assertThat(tracker.findByName(key).length, is(1));
    }
}