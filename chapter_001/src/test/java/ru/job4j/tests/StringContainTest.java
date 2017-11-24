package ru.job4j.tests;

import org.junit.Test;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.*;

/**
 * Test for string containing.
 *
 * @author Pyotr Kukharenka
 * @since 24.11.2017
 */
public class StringContainTest {
    @Test
    public void whenSubstringInOriginStringThenTrue() {
        StringContain contain = new StringContain();
        final String origin = "Привет";
        final String sub = "вет";
        final boolean res = contain.contains(origin, sub);
        assertThat(res, equalTo(true));
    }

}