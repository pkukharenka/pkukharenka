package ru.job4j.machine;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

/**
 * Тесты для автомата, выдающего сдачу.
 *
 * @author Pyotr Kukharenka
 * @since 08.12.2017
 */
public class AutomatTest {

    /**
     * Тест проверяет, что автомат выдает сдачу из 3 монет
     */
    @Test
    public void whenValue50AndPrice35ThenArray() {
        Automat automat = new Automat();
        int value = 50;
        int price = 37;
        int[] res = automat.changes(value, price);
        int[] exp = {10, 2, 1};
        assertThat(res, is(exp));
    }
}