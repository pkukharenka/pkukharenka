package ru.job4j;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Test for Calculate class.
 *
 * @author Pyotr Kukharenka
 * @since 19.11.2017
 */

public class CalculateTest {

    /**
     * Test for echo method.
     */

    @Test
    public void whenTakeNameThenTreeEchoPlusName() {
        String input = "Pyotr Kukharenka";
        String expect = "Echo, echo, echo : Pyotr Kukharenka";
        Calculate calc = new Calculate();
        String result = calc.echo(input);
        assertThat(result, is(expect));
    }
}