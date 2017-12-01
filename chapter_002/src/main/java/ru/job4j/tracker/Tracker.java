package ru.job4j.tracker;

import java.util.Arrays;
import java.util.Random;

/**
 * Класс трекер, который включает в себя основные инструменты
 * по работе с массивом объектов типа Item.
 *
 * @author Pyotr Kukharenka
 * @since 28.11.2017
 */

public class Tracker {
    private Item[] items = new Item[100];
    private int position = 0;
    private static final Random RN = new Random();

    /**
     * Метод добавления нового объекта в массив.
     *
     * @param item - объект.
     * @return - объект.
     */
    public Item add(Item item) {
        item.setId(this.generatedId());
        this.items[this.position++] = item;
        return item;
    }

    /**
     * Метод, позволяющий произвести изменение объекта в массиве
     * по его Id.
     *
     * @param item - обновленный объект.
     */
    public void update(Item item) {
        for (int index = 0; index < this.items.length; index++) {
            if (this.items[index].getId().equals(item.getId())) {
                this.items[index] = item;
                break;
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
        int index = this.positionSearch(item);
        this.items[index] = null;
    }

    /**
     * Метод возвращает все объекты, содержащиеся в массиве.
     *
     * @return -  массив найденных объектов.
     */
    public Item[] findAll() {
        Item[] values = new Item[this.position];
        values = Arrays.copyOf(this.items, this.position);
        return values;
    }

    /**
     * Метод производит поиск объектов в массиве по строковому ключу.
     *
     * @param key - строковый ключ.
     * @return массив найденных объектов
     */
    public Item[] findByName(String key) {
        int count = 0;
        Item[] values = new Item[this.position];
        for (Item item : this.items) {
            if (item != null && item.getName().contains(key)) {
                values[count] = item;
                count++;
            }
        }

        return values;
    }

    /**
     * Метод производит поиск объекта в массиве по его Id.
     *
     * @param id - Id объекта
     * @return - найденный объект.
     */
    public Item findById(String id) {
        Item result = null;
        for (Item item : this.items) {
            if (item.getId().equals(id)) {
                result = item;
                break;
            }
        }
        return result;
    }

    /**
     * Генерация уникального Id состоящего из произведения времени создания объекта
     * в милисекундах и рандомного числа в диапозоне от 1 до 100.
     *
     * @return уникальный Id.
     */
    private String generatedId() {
        return String.valueOf(System.currentTimeMillis() * (RN.nextInt(5) + 1));
    }

    /**
     * Метод возвращает индекс объекта в массиве.
     *
     * @param item - объект для которого находится индекс
     * @return - индекс.
     */
    private int positionSearch(Item item) {
        return Arrays.asList(this.items).indexOf(item);
    }
}
