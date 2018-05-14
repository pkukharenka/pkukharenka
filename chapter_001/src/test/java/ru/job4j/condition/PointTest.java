package ru.job4j.condition;

import org.junit.Test;
import ru.job4j.condition.triangle.Point;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

/**
 * Tests for calculate distance from one point to second.
 *
 * @author Pyotr Kukharenk
 * @since 21.11.2017
 */

public class PointTest {

    /**
     * Simple test.
     */
    @Test
    public void whenPointToPointThenTwo() {
        Point a = new Point(2, 1);
        Point b = new Point(4, 1);
        double result = a.distanceTo(b);
        double expected = 2D;
        assertThat(result, is(expected));
    }

}