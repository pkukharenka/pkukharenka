package ru.job4j.array;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

/**
 * Rotate array test.
 * @author Pyotr Kukharenka
 * @since 23.11.2017
 */
public class RotateArrayTest {
    /**
     * Test for rotating array.
     */
    @Test
    public void whenSimpleArrayThenRotateArray() {
        RotateArray rotateArray = new RotateArray();
        int[][] arr = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        final int[][] res = rotateArray.rotate(arr);
        final int[][] exp = {{7, 4, 1}, {8, 5, 2}, {9, 6, 3}};
        assertThat(res, is(exp));
    }

}