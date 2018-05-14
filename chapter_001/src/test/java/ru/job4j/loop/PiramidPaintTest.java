package ru.job4j.loop;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

/**
 * Tests for piramid painting.
 *
 * @author Pyotr Kukharenka
 * @since 23.11.2017
 */
public class PiramidPaintTest {
    /**
     * Piramid height = 3, base = 5.
     */
    @Test
    public void whenHeightThreeThenPiramid() {
        PiramidPaint piramidPaint = new PiramidPaint();
        final String result = piramidPaint.piramid(3);
        final String expected = "  ^  \n ^^^ \n^^^^^\n";
        assertThat(result, is(expected));
    }

    /**
     * Piramid height = 4, base = 7.
     */
    @Test
    public void whenHeightFiveThenPiramid() {
        PiramidPaint piramidPaint = new PiramidPaint();
        final String result = piramidPaint.piramid(4);
        final String expected = "   ^   \n  ^^^  \n ^^^^^ \n^^^^^^^\n";
        assertThat(result, is(expected));
    }

    /**
     * Piramid height = 2, base = 3.
     */
    @Test
    public void whenHeightTwoThenPiramid() {
        PiramidPaint piramidPaint = new PiramidPaint();
        final String result = piramidPaint.piramid(2);
        final String expected = " ^ \n^^^\n";
        assertThat(result, is(expected));
    }
}