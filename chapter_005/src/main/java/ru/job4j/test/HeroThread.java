package ru.job4j.test;


/**
 * Класс осуществляющий пользовательское движение
 * героя
 *
 * @author Pyotr Kukharenka
 * @since 23.01.2018
 */

public class HeroThread extends Thread {
    /**
     * Класс игровое поле.
     */
    private final Bomber bomber;
    /**
     * Класс модели.
     */
    private final Model model;

    /**
     * Конструктор для инициализации.
     *
     * @param bomber - игровое поле.
     * @param model  - модель
     */
    public HeroThread(Bomber bomber, Model model) {
        this.bomber = bomber;
        this.model = model;
    }

    /**
     * Апи для движения героя согласно пользовательского ввода.
     */
    @Override
    public void run() {
        while (true) {
            String key = this.checkKey();
            if (key.equalsIgnoreCase("w")) {
                this.bomber.moveUp(this.model);
            } else if (key.equalsIgnoreCase("s")) {
                this.bomber.moveDown(this.model);
            } else if (key.equalsIgnoreCase("a")) {
                this.bomber.moveLeft(this.model);
            } else if (key.equalsIgnoreCase("d")) {
                this.bomber.moveRight(this.model);
            }
        }
    }

    /**
     * Метод для проверки вводимого пользователем значения
     *
     * @return - введное значение
     */
    private String checkKey() {
        //Нативные метод для считывания нажатий кнопок
        //на клавиатуре.
        return "";
    }
}
