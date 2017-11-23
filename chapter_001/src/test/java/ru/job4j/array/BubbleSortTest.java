package ru.job4j.array;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

/**
 * Test for array bubble sorting method.
 *
 * @author Pyotr Kukharenka
 * @since 23.11.2017
 */
public class BubbleSortTest {
    /**
     * Test for sort simple array.
     */
    @Test
    public void whenSimpleArrayThenSort() {
        BubbleSort bs = new BubbleSort();
        int[] arr = {2, 3, 6, 1, 8, 10, 24, 12, 4};
        final int[] res = bs.sort(arr);
        final int[] exp = {1, 2, 3, 4, 6, 8, 10, 12, 24};
        assertThat(res, is(exp));
    }

}