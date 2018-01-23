package ru.job4j.test;

/**
 * Класс, описывающий точку, куда совершает движение
 * модель игры.
 *
 * @author Pyotr Kukharenka
 * @since 1
 */

public class Dest {

    /**
     * Положение по горизонтали.
     */
    private int x;
    /**
     * Положение по вертикали
     */
    private int y;

    /**
     * Конструктор инициализирующий поля.
     *
     * @param x - положение по горизонтали.
     * @param y - положение по вертикали.
     */
    public Dest(int x, int y) {
        this.x = x;
        this.y = y;
    }

    //Геттеры и сеттеры.
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
