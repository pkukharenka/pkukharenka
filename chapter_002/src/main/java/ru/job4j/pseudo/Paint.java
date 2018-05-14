package ru.job4j.pseudo;

/**
 * Класс, который осущствляет отрисовку нужной
 * фигуры.
 *
 * @author Pyotr Kukharenka
 * @since 02.12.2017
 */

public class Paint {
    /**
     * Фигура.
     */
    private Shape shape;

    /**
     * Конструктор инициализирующий фигуру
     *
     * @param shape - фигура.
     */
    public Paint(Shape shape) {
        this.shape = shape;
    }

    /**
     * Метод возвращает в консоль нарисованную фигуру
     */
    public void draw() {
        System.out.println(this.shape.draw());
    }
}
