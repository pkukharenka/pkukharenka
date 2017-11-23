package ru.job4j.array;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

/**
 * Array turn tests.
 *
 * @author Pyotr Kukharenka
 * @since 23.11.2017
 */
public class TurnTest {
    /**
     * Test for odd array.
     */
    @Test
    public void whenTurnOddArrayThen() {
        Turn turn = new Turn();
        int[] arr = {1, 2, 3, 4, 5};
        final int[] res = turn.back(arr);
        final int[] exp = {5, 4, 3, 2, 1};
        assertThat(res, is(exp));
    }

    /**
     * Test for even array.
     */
    @Test
    public void whenTurnEvenArrayThen() {
        Turn turn = new Turn();
        int[] arr = {4, 1, 6, 2};
        final int[] res = turn.back(arr);
        final int[] exp = {2, 6, 1, 4};
        assertThat(res, is(exp));
    }
}