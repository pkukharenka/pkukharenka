package ru.job4j.start.tracker;

import ru.job4j.start.BaseAction;
import ru.job4j.start.tracker.io.Input;
import ru.job4j.start.tracker.models.Item;

/**
 * Класс описывающий основные действия трекера, формирующий меню и
 * отрабатывающий взаимодейтсиве с пользователем.
 *
 * @author Pyotr Kukharenka
 * @since 03.12.2017
 */

public class MenuTracker {
    /**
     * Диапозон возможных чисел, при которых трекер будет функционировать
     */
    private int[] range;
    /**
     * Система ввода/вывода.
     */
    private Input input;
    /**
     * Хранилище заявок.
     */
    private Tracker tracker;
    /**
     * Хранилище действия программы трекер.
     */
    private UserAction[] actions = new UserAction[7];

    /**
     * Конструктор для инициализации меню трекера.
     *
     * @param input   - система ввода/вывода.
     * @param tracker - хранилище заявок.
     */
    public MenuTracker(Input input, Tracker tracker) {
        this.input = input;
        this.tracker = tracker;
    }

    /**
     * Метод, инициализирующий массив с действиями трекера и массив возможных
     * выборов пользователя.
     */
    public void fill() {
        this.actions[0] = this.new AddItem(0, "Добавление новой заявки.");
        this.actions[1] = this.new ShowItems(1, "Показать все заявки заявки.");
        this.actions[2] = this.new UpdateItem(2, "Редактировать заявку.");
        this.actions[3] = new MenuTracker.DeleteItem(3, "Удалить заявку.");
        this.actions[4] = new MenuTracker.FindById(4, "Поиск заявки по Id.");
        this.actions[5] = new FindByName(5, "Поиск заявок по имени.");
        this.actions[6] = this.new Exit(6, "Выход.");
        this.range = new int[this.actions.length];
        for (int index = 0; index < this.range.length; index++) {
            this.range[index] = index;
        }
    }

    /**
     * Метод, реализующий выбор пользователем действия трекера. При выборе действия
     * "выход из программы" (это действие всегда будет последним в списке), метод возвращает
     * значение true, в остальных случаях false.
     *
     * @return true или false.
     */
    public boolean select() {
        boolean flag = false;
        Integer key = this.input.ask("Выберите действие: ", this.range);
        if (!key.equals(this.actions.length - 1)) {
            this.actions[key].execute(this.input, this.tracker);
        } else {
            flag = true;
        }
        return flag;
    }

    /**
     * Метод отображает в консоли меню программы трекер.
     */
    public void showMenu() {
        for (UserAction action : this.actions) {
            System.out.println(action.info());
        }
    }

    /**
     * Класс, описывающий создание новой заявки в трекере.
     */
    private class AddItem extends BaseAction {
        /**
         * Конструктор.
         *
         * @param key  - ключ меню.
         * @param name - имя пункта меню.
         */
        public AddItem(int key, String name) {
            super(key, name);
        }

        /**
         * Метод, осуществляющий взаимодействие с пользователем по
         * добавлению новой заявки.
         *
         * @param input   - ввод/вывод.
         * @param tracker - хранилище заявок
         */
        @Override
        public void execute(Input input, Tracker tracker) {
            String name = input.ask("Введите имя автора заявки: ");
            String desc = input.ask("Введите описание заявки: ");
            Item item = new Item(name, desc, System.currentTimeMillis());
            tracker.add(item);
            System.out.println("Выполнено успешно.");
        }
    }

    /**
     * Класс, описывающий отображение всех заявок в трекере.
     */
    private class ShowItems extends BaseAction {
        /**
         * Конструктор.
         *
         * @param key  - ключ меню.
         * @param name - имя пункта меню.
         */
        public ShowItems(int key, String name) {
            super(key, name);
        }

        /**
         * Метод, осуществляющий взаимодействие с пользователем по
         * выводу всех заявок трекера.
         *
         * @param input   - ввод/вывод.
         * @param tracker - хранилище заявок
         */
        @Override
        public void execute(Input input, Tracker tracker) {
            Item[] items = tracker.findAll();
            if (items.length != 0) {
                for (Item item : items) {
                    System.out.println(item);
                }
            } else {
                System.out.println("***********На данный момент заявок нет.***********");
            }
        }
    }

    /**
     * Класс, описывающий редактирование заявки в трекере.
     */
    private class UpdateItem extends BaseAction {
        /**
         * Конструктор.
         *
         * @param key  - ключ меню.
         * @param name - имя пункта меню.
         */
        public UpdateItem(int key, String name) {
            super(key, name);
        }

        /**
         * Метод, осуществляющий взаимодействие с пользователем по
         * редактированию заявки.
         *
         * @param input   - ввод/вывод.
         * @param tracker - хранилище заявок
         */
        @Override
        public void execute(Input input, Tracker tracker) {
            new ShowItems(1, "Показать все заявки заявки.").execute(input, tracker);
            String id = input.ask("***********Укажите ID заявки для редактирования:*********** ");
            Item item = tracker.findById(id);
            System.out.println("Редактирование заявки с ID " + id);
            String name = input.ask("Введите нового автора заявки");
            String desc = input.ask("Введите новое описание заявки");
            item.setName(name);
            item.setDesc(desc);
            tracker.update(item);
            System.out.println("Выполнено успешно.");
        }
    }

    /**
     * Статический класс, описывающий поиск заявки по ID в трекере.
     */
    private static class FindById extends BaseAction {
        /**
         * Конструктор.
         *
         * @param key  - ключ меню.
         * @param name - имя пункта меню.
         */
        public FindById(int key, String name) {
            super(key, name);
        }

        /**
         * Метод, осуществляющий взаимодействие с пользователем по
         * поиску заявки по ее Id.
         *
         * @param input   - ввод/вывод.
         * @param tracker - хранилище заявок
         */
        @Override
        public void execute(Input input, Tracker tracker) {
            String id = input.ask("***********Укажите ID заявки для поиска: ***********");
            Item item = tracker.findById(id);
            if (item != null) {
                System.out.println("Заявка с ID " + item.getId() + " найдена.\n"
                        + "Имя автора заявки: " + item.getName()
                        + "\nОписание заявки: " + item.getDesc());
            } else {
                System.out.println("***********Заявка с ID " + id + " не найдена.***********");
            }
        }
    }

    /**
     * Класс, описывающий выход из трекера.
     */
    private class Exit extends BaseAction {
        /**
         * Конструктор.
         *
         * @param key  - ключ меню.
         * @param name - имя пункта меню.
         */
        public Exit(int key, String name) {
            super(key, name);
        }

        @Override
        public void execute(Input input, Tracker tracker) {

        }
    }

    /**
     * Класс, описывающий удаление заявки из трекера.
     */
    private class DeleteItem extends BaseAction {
        /**
         * Конструктор.
         *
         * @param key  - ключ меню.
         * @param name - имя пункта меню.
         */
        public DeleteItem(int key, String name) {
            super(key, name);
        }

        /**
         * Метод, осуществляющий взаимодействие с пользователем по
         * удалению заявки.
         *
         * @param input   - ввод/вывод.
         * @param tracker - хранилище заявок
         */
        @Override
        public void execute(Input input, Tracker tracker) {
            new MenuTracker.ShowItems(1, "Показать все заявки заявки.").execute(input, tracker);
            String id = input.ask("***********Укажите ID заявки для удаления: ***********");
            Item item = tracker.findById(id);
            String answer = input.ask("Вы уверены что хотите удалить заявку с ID " + id + "[yes/no]");
            if (answer.equals("yes")) {
                tracker.delete(item);
                System.out.println("***********Заявка с ID " + item.getId() + " успешно удалена.***********");
            }
        }
    }
}

/**
 * Класс, описывающий поиск заявок по имени в трекере.
 */
class FindByName extends BaseAction {
    /**
     * Конструктор.
     *
     * @param key  - ключ меню.
     * @param name - имя пункта меню.
     */
    public FindByName(int key, String name) {
        super(key, name);
    }

    /**
     * Метод, осуществляющий взаимодействие с пользователем по
     * поиску заявок по имени.
     *
     * @param input   - ввод/вывод.
     * @param tracker - хранилище заявок
     */
    @Override
    public void execute(Input input, Tracker tracker) {
        String key = input.ask("***********Укажите имя автора заявки для поиска: ***********");
        Item[] items = tracker.findByName(key);
        if (items.length != 0) {
            System.out.println("***********Заявки автора " + key + ".***********");
            for (Item item : items) {
                System.out.println(item);
            }
        } else {
            System.out.println("***********Заявки с именем " + key + " не найдены.***********");
        }
    }
}
