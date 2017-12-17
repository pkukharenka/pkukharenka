package ru.job4j.iterator;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

/**
 * Тесты для проверки итератора по простым числам.
 *
 * @author Pyotr Kukharenka
 * @since 17.12.2017
 */
public class PrimeIteratorTest {
    /**
     * Проверяем что итератор правильно возвращает элемент
     * массива при воторой итерации - это простое число 5.
     */
    @Test
    public void whenTwoTimeNextThen5() {
        PrimeIterator it = new PrimeIterator(new int[]{3, 4, 5, 6, 7});
        it.next();
        final int res = it.next();
        assertThat(res, is(5));
    }

    /**
     * Проверяем что итератор вернет false если все значения массива
     * являются не простыми числами.
     */
    @Test
    public void whenArrayNoyContainPrimeThenException() {
        PrimeIterator it = new PrimeIterator(new int[]{15, 4, 8, 6, 10});
        assertThat(it.hasNext(), is(false));
    }

    /**
     * Проверяем что итератор правильно находит простое число 2.
     */
    @Test
    public void whenArrayContain2ThenElement2() {
        PrimeIterator it = new PrimeIterator(new int[]{6, 4, 2, 7, 11});
        assertThat(it.next(), is(2));
    }
}