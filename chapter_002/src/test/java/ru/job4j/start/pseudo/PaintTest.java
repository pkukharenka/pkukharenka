package ru.job4j.start.pseudo;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

/**
 * Класс для тестирвоания прорисовки фигур в консоли
 *
 * @author Pyotr Kukharenka
 * @since 02.12.2017
 */
public class PaintTest {
    /**
     * Стандартный вывод в консоль.
     */
    private PrintStream ps = System.out;
    /**
     * Вывод в память для проверки в тестах.
     */
    private ByteArrayOutputStream out = new ByteArrayOutputStream();

    /**
     * Тест для квадрата. Меняем вывод из консоли в память.
     * Сравниваем результаты. Возвращаем вывод в консоль.
     */
    @Test
    public void whenDrawSquare() {
        System.setOut(new PrintStream(this.out));
        new Paint(new Square()).draw();
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
                .append("*******")
                .append(System.lineSeparator()).toString();
        assertThat(new String(this.out.toByteArray()), is(exp));
        System.setOut(this.ps);
    }

    /**
     * Тест для треугольника. Меняем вывод из консоли в память.
     * Сравниваем результаты. Возвращаем вывод в консоль.
     */
    @Test
    public void whenDrawTriangle() {
        System.setOut(new PrintStream(this.out));
        new Paint(new Triangle()).draw();
        String exp = new StringBuilder()
                .append("   *   ")
                .append(System.lineSeparator())
                .append("  ***  ")
                .append(System.lineSeparator())
                .append(" ***** ")
                .append(System.lineSeparator())
                .append("*******")
                .append(System.lineSeparator()).toString();
        assertThat(new String(this.out.toByteArray()), is(exp));
        System.setOut(this.ps);
    }
}