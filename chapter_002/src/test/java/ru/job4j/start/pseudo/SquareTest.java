package ru.job4j.start.pseudo;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

/**
 * Класс для тестирования отрисовки квадарат в псевдографике.
 *
 * @author Pyotr Kukharenka
 * @since 02.12.2017
 */
public class SquareTest {
    /**
     * Тест метода draw класса Suqare.
     */
    @Test
    public void whenDrawSuareThenShow() {
        Shape square = new Square();
        String res = square.draw();
        String exp = new StringBuilder()
                .append("*******")
                .append(System.lineSeparator())
                .append("*******")
                .append(System.lineSeparator())
                .append("*******")
                .append(System.lineSeparator())
                .append("*******")
                .append(System.lineSeparator())
                .append("*******")
                .append(System.lineSeparator())
                .append("*******")
                .append(System.lineSeparator())
                .append("*******").toString();
        assertThat(res, is(exp));
    }
}