package ru.job4j.start.tracker;

import ru.job4j.start.tracker.models.Item;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Класс трекер, который включает в себя основные инструменты
 * по работе с массивом объектов типа Item.
 *
 * @author Pyotr Kukharenka
 * @since 28.11.2017
 */

public class Tracker {
    private List<Item> items = new ArrayList<>();
    private int id = 1;

    /**
     * Метод добавления нового объекта в массив.
     *
     * @param item - объект.
     * @return - объект.
     */
    public Item add(Item item) {
        item.setId(this.id++);
        this.items.add(item);
        return item;
    }

    /**
     * Метод, позволяющий произвести изменение объекта в массиве
     * по его Id.
     *
     * @param item - обновленный объект.
     */
    public void update(Item item) {
        for (Item value : this.items) {
            if (value.getId() == item.getId()) {
                int index = this.items.indexOf(value);
                this.items.set(index, item);

            }
        }
    }

    /**
     * Метод присваивает null ячейке массива, содержащей
     * объект для удаления
     *
     * @param item - удаляемый объект
     */
    public void delete(Item item) {
        this.items.remove(item);
    }

    /**
     * Метод возвращает все объекты, содержащиеся в массиве.
     *
     * @return -  массив найденных объектов.
     */
    public List<Item> findAll() {
        return this.items;
    }

    /**
     * Метод производит поиск объектов в массиве по строковому ключу.
     *
     * @param key - строковый ключ.
     * @return массив найденных объектов
     */
    public List<Item> findByName(String key) {
        List<Item> list = new ArrayList<>();
        for (Item item : this.items) {
            if (item.getName().contains(key)) {
                list.add(item);
            }
        }
        return list;
    }

    /**
     * Метод производит поиск объекта в массиве по его Id.
     *
     * @param id - Id объекта
     * @return - найденный объект.
     */
    public Item findById(int id) {
        Item result = null;
        for (Item item : this.items) {
            if (item.getId() == id) {
                result = item;
            }
        }
        return result;
    }

}
