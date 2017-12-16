package ru.job4j.iterator;

import org.junit.Test;

import java.util.Iterator;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

/**
 * Тесты для проверки работоспособности итерации
 * двумерных массивов
 *
 * @author Pyotr Kukharenka
 * @since 16.12.2017
 */
public class ArrayIteratorTest {
    /**
     * Проверка правильности итерации по матрице. Проходим 3 элемента
     * он должен быть 3.
     */
    @Test
    public void whenIterateMatrixThenThirdElementIs3() {
        ArrayIterator<Integer> ai = new ArrayIterator<Integer>(new Integer[][]{{1, 2}, {3, 4}, {5, 6}});
        Iterator<Integer> iter = ai.iterator();
        iter.next();
        iter.next();
        int res = iter.next();
        assertThat(res, is(3));
    }

    /**
     * Проверка правильности итерации по jagger матрице. Проходим 5 элементов,
     * при этом строки массива разного размера, он должен быть 5.
     */
    @Test
    public void whenIterateJaggerArrayThenElementIs5() {
        ArrayIterator<Integer> ai = new ArrayIterator<Integer>(new Integer[][]{{1, 2, 3}, {4}, {5, 6}});
        Iterator<Integer> iter = ai.iterator();
        int res = 0;
        for (int count = 0; count < 5; count++) {
            res = iter.next();
        }
        assertThat(res, is(5));
    }

    /**
     * Проверка, что метод hasNext возвращает false если
     * следующего элемента нет.
     */
    @Test
    public void whenNotNextThenFalse() {
        ArrayIterator<Integer> ai = new ArrayIterator<Integer>(new Integer[][]{{1}, {4}});
        Iterator<Integer> iter = ai.iterator();
        iter.next();
        iter.next();
        boolean res = iter.hasNext();
        assertThat(res, is(false));
    }

}