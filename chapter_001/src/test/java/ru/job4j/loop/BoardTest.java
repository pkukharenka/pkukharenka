package ru.job4j.loop;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

/**
 * Tests for painting board.
 *
 * @author Pyotr Kukharenka
 * @since 22.11.2017
 */
public class BoardTest {

    /**
     * 3x3 board.
     */
    @Test
    public void whenThreeOnThreeThenBoard() {
        Board board = new Board();
        final String result = board.paint(3, 3);
        final String expected = "X X\n X \nX X\n";
        assertThat(result, is(expected));
    }

    /**
     * 5x5 board.
     */
    @Test
    public void whenFiveonFiveThenBoard() {
        Board board = new Board();
        final String result = board.paint(5, 5);
        final String expected = "X X X\n X X \nX X X\n X X \nX X X\n";
        assertThat(result, is(expected));
    }
}