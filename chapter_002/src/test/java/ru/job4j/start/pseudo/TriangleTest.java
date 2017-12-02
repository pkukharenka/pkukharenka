package ru.job4j.start.pseudo;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;
/**
 * Класс для тестирования отрисовки треугольника в псевдографике.
 *
 * @author Pyotr Kukharenka
 * @since 02.12.2017
 */
public class TriangleTest {
    /**
     * Тест метода draw класса Triangle.
     */
    @Test
    public void whenDrawTriangleThenShow() {
        Shape triangle = new Triangle();
        String res = triangle.draw();
        String exp = new StringBuilder()
                .append("   *   \n")
                .append("  ***  \n")
                .append(" ***** \n")
                .append("*******").toString();

        assertThat(res, is(exp));
    }

}