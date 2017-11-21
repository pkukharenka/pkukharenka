package ru.job4j.max;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

/**
 * Test for max task.
 *
 * @author Pyotr Kukharenka
 * @since 21.11.2017
 */

public class MaxTest {

    /**
     * First less
     */
    @Test
    public void whenFirstLessSecond() {
        Max maximum = new Max();
        int result = maximum.max(1, 2);
        assertThat(result, is(2));
    }
    /**
     * Second less
     */
    @Test
    public void whenSecondLessFirst() {
        Max maximum = new Max();
        int result = maximum.max(5, -1);
        assertThat(result, is(5));
    }

}