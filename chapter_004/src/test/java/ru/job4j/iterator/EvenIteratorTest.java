package ru.job4j.iterator;

import org.junit.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

/**
 * Тесты для проверки работы итратора по четным числам
 * массива.
 *
 * @author Pyotr Kukharenka
 * @since 16.12.2017
 */
public class EvenIteratorTest {
    /**
     * Проверяем, что следующий элемент, возвращаемый методом
     * next является 2.
     */
    @Test
    public void whenIterateArrayWithTwoEvenNumbersThenNextElement2() {
        Iterator<Integer> it = new EvenIterator(new int[]{4, 1, 5, 2, 6, 3});
        it.next();
        final int res = it.next();
        assertThat(res, is(2));
    }

    /**
     * Проверяем что если в массиве нет ни одного четного элемента
     * и ожидаем NoSuchElementException.
     */
    @Test(expected = NoSuchElementException.class)
    public void whenNoEvenNumbersThenException() {
        Iterator<Integer> it = new EvenIterator(new int[]{1, 1, 5, 7, 9, 3});
        it.next();

    }

    /**
     * Проверяем, что метод неоднократный вызов метода hasNext
     * не влияет на правильность работы программы и она возвращает
     * первое четное число - 4.
     */
    @Test
    public void whenHasNextSameTimeThenNextElement4() {
        Iterator<Integer> it = new EvenIterator(new int[]{4, 1, 5, 2, 6, 3});
        it.hasNext();
        it.hasNext();
        final int res = it.next();
        assertThat(res, is(4));
    }
}