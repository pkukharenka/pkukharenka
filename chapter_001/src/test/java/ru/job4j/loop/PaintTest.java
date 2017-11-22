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
public class PaintTest {
    /**
     * Piramid height = 3, base = 5.
     */
    @Test
    public void whenHeightThreeThenPiramid() {
        Paint paint = new Paint();
        final String result = paint.piramid(3);
        final String expected = "  ^  \n ^^^ \n^^^^^\n";
        assertThat(result, is(expected));
    }

    /**
     * Piramid height = 4, base = 7.
     */
    @Test
    public void whenHeightFiveThenPiramid() {
        Paint paint = new Paint();
        final String result = paint.piramid(4);
        final String expected = "   ^   \n  ^^^  \n ^^^^^ \n^^^^^^^\n";
        assertThat(result, is(expected));
    }

    /**
     * Piramid height = 2, base = 3.
     */
    @Test
    public void whenHeightTwoThenPiramid() {
        Paint paint = new Paint();
        final String result = paint.piramid(2);
        final String expected = " ^ \n^^^\n";
        assertThat(result, is(expected));
    }
}