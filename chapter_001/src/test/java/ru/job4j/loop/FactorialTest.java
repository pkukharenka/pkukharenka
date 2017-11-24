package ru.job4j.loop;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

/**
 * Test for factorial task.
 *
 * @author Pyotr Kukharenka
 * @since 22.11.2017
 */
public class FactorialTest {

    /**
     * Test factorial with normal number.
     */
    @Test
    public void whenFactorial5Then120() {
        Factorial fact = new Factorial();
        final int result = fact.calc(5);
        assertThat(result, is(120));
    }

    /**
     * Test factorial with negative number.
     */
    @Test
    public void whenNegativeNumberThenException() {
        Factorial fact = new Factorial();
        final int result = fact.calc(-5);
        assertThat(result, is(-1));
    }

    /**
     * Test factorial with zero.
     */
    @Test
    public void whenZeroThenOne() {
        Factorial fact = new Factorial();
        final int result = fact.calc(0);
        assertThat(result, is(1));
    }

}