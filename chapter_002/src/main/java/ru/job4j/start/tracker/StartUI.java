package ru.job4j.start.tracker;

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

    /**
     * Константа для добавления новых заявок.
     */
    private static final String ADD = "1";
    /**
     * Константа для отображения всех существующих заявок пользователя.
     */
    private static final String SHOW = "2";
    /**
     * Константа для редактирования заявок.
     */
    private static final String EDIT = "3";
    /**
     * Константа для удаления заявок.
     */
    private static final String DELETE = "4";
    /**
     * Константа для поиска заявок по id.
     */
    private static final String BY_ID = "5";
    /**
     * Константа для поиска заявок по имени.
     */
    private static final String BY_NAME = "6";
    /**
     * Константа для выхода из программы.
     */
    private static final String EXIT = "7";

    /**
     * Конуструктор для инициализации программы.
     *
     * @param input   - система ввода/вывода.
     * @param tracker - хранилище заявок.
     */
    public StartUI(Input input, Tracker tracker) {
        this.input = input;
        this.tracker = tracker;
    }

    /**
     * Основной цикл программы.
     */
    public void init() {
        boolean flag = false;
        while (!flag) {
            this.showMenu();
            String answer = this.input.ask("\nВведите пункт меню:");
            if (ADD.equals(answer)) {
                this.createItem();
            } else if (SHOW.equals(answer)) {
                this.showAllItems();
            } else if (EDIT.equals(answer)) {
                this.editItem();
            } else if (DELETE.equals(answer)) {
                this.deleteItem();
            } else if (BY_ID.equals(answer)) {
                this.searchItemById();
            } else if (BY_NAME.equals(answer)) {
                this.searchItemByName();
            } else if (EXIT.equals(answer)) {
                flag = true;
            } else {
                System.out.println("Укажите цифру от 1 до 7");
            }
        }
    }

    /**
     * Метод, отображающий основное меню программы.
     */
    private void showMenu() {
        StringBuilder sb = new StringBuilder();
        System.out.print("\nПрограмма учета заявок.");
        System.out.println("Для работы с программой Вам необходимо выбрать пункт меню и нажать ENTER.\n");
        System.out.println("Меню:");
        System.out.println("1. Добавить новую заявку.");
        System.out.println("2. Показать все заявки.");
        System.out.println("3. Редактировать заявку.");
        System.out.println("4. Удалить заявку.");
        System.out.println("5. Найти заявку по ID номеру.");
        System.out.println("6. Найти заявки по имени.");
        System.out.println("7. Выйти из программы.");
    }

    /**
     * Взаимодействие с пользователем при создании новой заявки.
     */
    private void createItem() {
        System.out.println("***********Добавление новой заявки:***********");
        String name = this.input.ask("Введите имя заявки: ");
        String desc = this.input.ask("Введите описание заявки: ");
        Item item = new Item(name, desc, System.currentTimeMillis());
        tracker.add(item);
        System.out.println("***********Заявка с ID " + item.getId() + " успешно добавлена.***********");
    }

    /**
     * Взаимодействие с пользователем при отображении всех заявок.
     */
    private void showAllItems() {
        Item[] items = tracker.findAll();
        if (items.length != 0) {
            System.out.println("***********Существующие заявки:***********");
            for (Item item : items) {
                System.out.println(item);
            }
        } else {
            System.out.println("***********На данный момент заявок нет.***********");
        }

    }

    /**
     * Взаимодействие с пользователем при редактировании заявки.
     */
    private void editItem() {
        this.showAllItems();
        String id = this.input.ask("***********Укажите ID заявки для редактирования:*********** ");
        Item item = tracker.findById(id);
        System.out.println("Редактирование заявки с ID " + id);
        String name = this.input.ask("Введите новое имя заявки");
        String desc = this.input.ask("Введите новое описание заявки");
        item.setName(name);
        item.setDesc(desc);
        tracker.update(item);
        System.out.println("***********Заявка с ID " + item.getId() + " успешно обновлена.***********");
    }

    /**
     * Взаимодействие с пользователем при удалении заявки.
     */
    private void deleteItem() {
        this.showAllItems();
        String id = this.input.ask("***********Укажите ID заявки для удаления: ***********");
        Item item = tracker.findById(id);
        String answer = this.input.ask("Вы уверены что хотите удалить заявку с ID " + id + "[yes/no]");
        if (answer.equals("yes")) {
            tracker.delete(item);
            System.out.println("***********Заявка с ID " + item.getId() + " успешно удалена.***********");
        }

    }

    /**
     * Взаимодействие с пользователем при поиске заявки по id.
     */
    private void searchItemById() {
        String id = this.input.ask("***********Укажите ID заявки для поиска: ***********");
        Item item = tracker.findById(id);
        if (item != null) {
            System.out.println("Заявка с ID " + item.getId() + " найдена.\n"
                    + "Имя заявки: " + item.getName()
                    + "\nОписание заявки: " + item.getDesc());
        } else {
            System.out.println("***********Заявка с ID " + id + " не найдена.***********");
        }
    }

    /**
     * Взаимодействие с пользователем при поиске заявки по имени.
     */
    private void searchItemByName() {
        String key = this.input.ask("***********Укажите имя заявки для поиска: ***********");
        Item[] items = tracker.findByName(key);
        if (items.length != 0) {
            for (Item item : items) {
                System.out.println(item);
            }
        } else {
            System.out.println("***********Заявки с именем " + key + " не найдены.***********");
        }
    }

    /**
     * Запуск программы.
     */
    public static void main(String[] args) {
        new StartUI(new ConsoleInput(), new Tracker()).init();
    }
}
