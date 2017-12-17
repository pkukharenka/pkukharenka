package ru.job4j.iterator;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

/**
 * Тесты для проверки работы итератора итераторов.
 *
 * @author Pyotr Kukharenka
 * @since 17.12.2017
 */
public class ComplexIterTest {
    /**
     * Коллекция итераторов.
     */
    private List<Iterator<Integer>> list = new ArrayList<>();

    /**
     * Метод запускается перед тестами для инициализации коллекции
     * итераторов. Коллекци содержит 2 итератора, 1 - только четные
     * числа, 2 - только простые числа.
     */
    @Before
    public void init() {
        this.list.add(new EvenIterator(new int[]{2, 1, 4})); //вернет 2 и 4
        this.list.add(new PrimeIterator(new int[]{7, 15, 2})); //вернет 7, 2
    }

    /**
     * Проверяем что при вызове 3 раз метода next значение
     * будет 7, т.к. 1 итератор пройдет 2 значения (2 и 4),
     * 2 итератор остановится на 1 значении  - 7.
     */
    @Test
    public void whenNext3TiemThenElement7() {
        ComplexIter complex = new ComplexIter();
        Iterator<Iterator<Integer>> iter = this.list.iterator();

        Iterator<Integer> target = complex.convert(iter);
        target.next();
        target.next();

        assertThat(target.next(), is(7));
    }

    /**
     * Проверяем что при проходе всех элементов метод hasNext
     * вернет false.
     */
    @Test
    public void whenNext4TimeThenHasNextFalse() {
        ComplexIter complex = new ComplexIter();
        Iterator<Iterator<Integer>> iter = this.list.iterator();

        Iterator<Integer> target = complex.convert(iter);
        target.next();
        target.next();
        target.next();
        target.next();

        assertThat(target.hasNext(), is(false));
    }
}