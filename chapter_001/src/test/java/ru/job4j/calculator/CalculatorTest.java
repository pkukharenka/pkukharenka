package ru.job4j.calculator;

import org.junit.Assert;
import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

/**
 * Tests for Calculator/
 *
 * @author Pyotr Kukharenka
 * @since 21.11.2017
 */

public class CalculatorTest {
    /**
     * Test for add (1+1=2)
     */

    @Test
    public void whenAddOnePlusOneThenTwo() {
        Calculator calc = new Calculator();
        calc.add(1D, 1D);
        double result = calc.getResult();
        double expected = 2D;
        assertThat(result, is(expected));
    }

    /**
     * Test for subtract (3-1=2)
     */
    @Test
    public void whenThreeSubtractOneThenTwo() {
        Calculator calc = new Calculator();
        calc.subtract(3D, 1D);
        double result = calc.getResult();
        double expected = 2D;
        assertThat(result, is(expected));
    }

    /**
     * Test for multiple (2*2=4)
     */
    @Test
    public void whenTwoMultipleTwoThenFour() {
        Calculator calc = new Calculator();
        calc.multiple(2D, 2D);
        double result = calc.getResult();
        double expected = 4D;
        assertThat(result, is(expected));
    }

    /**
     * Test for div (4/2=2)
     */
    @Test
    public void whenFourDivTwoThenTwo() {
        Calculator calc = new Calculator();
        calc.div(4D, 2D);
        double result = calc.getResult();
        double expected = 2D;
        assertThat(result, is(expected));
    }

    /**
     * Test for div (4/0=Exception)
     */
    @Test(expected = ArithmeticException.class)
    public void whenFourDivZeroThenThrowException() {
        Calculator calc = new Calculator();
        calc.div(4D, 0);
    }

}