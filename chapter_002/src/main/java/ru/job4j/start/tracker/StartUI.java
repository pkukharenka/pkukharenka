package ru.job4j.start.tracker;

import ru.job4j.start.tracker.io.Input;
import ru.job4j.start.tracker.io.ValidateInput;

/**
 * Класс содержит в себе методы для взаимодействия с пользователем.
 *
 * @author Pyotr Kukharenka
 * @since 01.12.2017
 */

public class StartUI {
    /**
     * Получение данных от пользователя.
     */
    private Input input;
    /**
     * Хранилище заявок пользователя.
     */
    private Tracker tracker;

    private Util util;

    /**
     * Конуструктор для инициализации программы.
     *
     * @param input   - система ввода/вывода.
     * @param tracker - хранилище заявок.
     */
    public StartUI(Input input, Tracker tracker) {
        this.input = input;
        this.tracker = tracker;
        this.util = new Util();
    }

    /**
     * Основной цикл программы.
     * 1. Проверяется созданы ли табоицы базы данных, если нет,
     * то создаются.
     * 2. Производится наполнение меню программы значениями возможных функций,
     * а также вывод меню в консоль и запрос у пользователя выбора действия.
     */
    public void init() {
        boolean flag = false;
        this.util.checkTable();
        MenuTracker menu = new MenuTracker(this.input, this.tracker);
        menu.fill();
        while (!flag) {
            menu.showMenu();
            flag = menu.select();
        }
    }

    /**
     * Запуск программы.
     */
    public static void main(String[] args) {
        new StartUI(new ValidateInput(), new Tracker()).init();
    }
}
