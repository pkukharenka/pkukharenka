package ru.job4j.condition;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.number.IsCloseTo.closeTo;
import static org.junit.Assert.*;

/**
 * Triangle area tests
 *
 * @author Pyotr Kukharenka
 * @since 21.11.2017
 */

public class TriangleTest {

    /**
     * Test of standart triangle with normal lines.
     */
    @Test
    public void whenSetThreePointsThenArea() {
        Point a = new Point(0, 0);
        Point b = new Point(0, 2);
        Point c = new Point(2, 0);

        Triangle triangle = new Triangle(a, b, c);
        double result = triangle.area();
        double expected = 2D;
        assertThat(result, closeTo(expected, 0.1));

    }

    /**
     * Test for calculating distance between two points.
     */
    @Test
    public void whenDistanceDiffThen10() {
        Point left = new Point(0, 10);
        Point right = new Point(0, 0);
        Triangle triangle = new Triangle(null, null, null);
        double result = triangle.distance(left, right);
        assertThat(result, closeTo(10, 0.01));

    }

    /**
     * Test when triangle doesn't exist.
     */
    @Test
    public void whenOneLineIsAboveSumOfTwoThenTriangleNotExist() {
        Point a = new Point(0, 0);
        Point b = new Point(-4, 0);
        Point c = new Point(2, 0);

        Triangle triangle = new Triangle(a, b, c);
        double result = triangle.area();
        double expected = -1;
        assertThat(result, is(expected));
    }
}