package ru.job4j.chess;

/**
 * Класс, описывающий ячейку шахматной доски. Шахматная доска
 * представляет собой систему координат x*y.
 *
 * @author Pyotr Kukharenka
 * @since 05.12.2017
 */

public class Cell {
    /**
     * Х координата.
     */
    private int x;
    /**
     * Y координата
     */
    private int y;

    /**
     * Конструктор для инициализации ячейки.
     *
     * @param x - Х координата.
     * @param y - Y координата
     */
    public Cell(int x, int y) {
        this.x = x;
        this.y = y;
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
