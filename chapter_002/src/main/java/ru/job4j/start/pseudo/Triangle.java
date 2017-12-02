package ru.job4j.start.pseudo;

/**
 * Класс отрисовки треугольника.
 *
 * @author Pyotr Kukharenka
 * @since 02.12.2017
 */

public class Triangle implements Shape {
    /**
     * Метод возвращает строковый вид треугольника в псевдографике.
     *
     * @return - треугольник.
     */
    @Override
    public String draw() {
        StringBuilder sb = new StringBuilder();
        sb.append("   *   \n");
        sb.append("  ***  \n");
        sb.append(" ***** \n");
        sb.append("*******");
        return sb.toString();
    }
}
