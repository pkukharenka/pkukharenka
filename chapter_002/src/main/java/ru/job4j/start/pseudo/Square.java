package ru.job4j.start.pseudo;

/**
 * Класс отрисовки квадрата.
 *
 * @author Pyotr Kukharenka
 * @since 02.12.2017
 */

public class Square implements Shape {
    /**
     * Метод возвращает строковый вид квадрата в псевдографике.
     *
     * @return - квадрат.
     */
    @Override
    public String draw() {
        StringBuilder sb = new StringBuilder();
        sb.append("*******\n");
        sb.append("*******\n");
        sb.append("*******\n");
        sb.append("*******\n");
        sb.append("*******\n");
        sb.append("*******\n");
        sb.append("*******");
        return sb.toString();
    }
}
