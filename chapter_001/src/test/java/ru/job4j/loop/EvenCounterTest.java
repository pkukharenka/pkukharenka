package ru.job4j.loop;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

/**
 * Tests for counting even numbers in range.
 *
 * @author Pyotr Kukharenka
 * @since 22.11.2017
 */
public class EvenCounterTest {

    /**
     * Test counting even numers in range 1 to 10 = 30
     */
    @Test
    public void whenOneAndTenThen30() {
        EvenCounter evenCounter = new EvenCounter();
        int result = evenCounter.add(1, 10);
        assertThat(result, is(30));
    }
}