package ru.job4j.pseudo;

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
        sb.append("*******");
        sb.append(System.lineSeparator());
        sb.append("*******");
        sb.append(System.lineSeparator());
        sb.append("*******");
        sb.append(System.lineSeparator());
        sb.append("*******");
        sb.append(System.lineSeparator());
        sb.append("*******");
        sb.append(System.lineSeparator());
        sb.append("*******");
        sb.append(System.lineSeparator());
        sb.append("*******");
        return sb.toString();
    }
}
