package ru.job4j.test;

/**
 * Класс модель (герой или чудовище)
 *
 * @author Pyotr Kukharenka
 * @since 12.01.2018
 */

public class Model {
    /**
     * Имя модели
     */
    private String name;
    /**
     * Положение по горизонтали.
     */
    private int x;
    /**
     * Положение по вертикали
     */
    private int y;

    /**
     * Инициализация модели с начальным положением.
     *
     * @param name - имя модели
     * @param x    - положение по горизонтали.
     * @param y    - положение по вертикали
     */
    public Model(String name, int x, int y) {
        this.name = name;
        this.x = x;
        this.y = y;
    }

    //Геттеры и сеттеры
    public String getName() {
        return name;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
