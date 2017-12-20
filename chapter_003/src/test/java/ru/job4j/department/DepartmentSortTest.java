package ru.job4j.department;

import org.junit.Test;


import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

/**
 * Тесты для сортировки подразделений
 *
 * @author Pyotr Kukharenka
 * @since 15.12.2017
 */
public class DepartmentSortTest {
    /**
     * Массив строк для проверки работоспособности программы.
     */
    private final String[] array = {"K1\\SK1", "K1\\SK2", "K1\\SK1\\SSK1", "K1\\SK1\\SSK2", "K2",
            "K2\\SK1\\SSK1", "K2\\SK1\\SSK2"};

    /**
     * Тест осуществляет проверку сортировки массива по возрастанию.
     */
    @Test
    public void whenArraySortAscendingThen() {
        DepartmentSort ds = new DepartmentSort();
        AscComparator asc = new AscComparator();
        final String[] exp = {"K1", "K1\\SK1", "K1\\SK1\\SSK1", "K1\\SK1\\SSK2", "K1\\SK2", "K2",
                "K2\\SK1", "K2\\SK1\\SSK1", "K2\\SK1\\SSK2"};
        final String[] res = ds.checkDepart(this.array, asc);
        assertThat(res, is(exp));
    }
    /**
     * Тест осуществляет проверку сортировки массива по убыванию.
     */
    @Test
    public void whenArraySortDescendingThen() {
        DepartmentSort ds = new DepartmentSort();
        DescComparator desc = new DescComparator();
        final String[] exp = {"K2", "K2\\SK1", "K2\\SK1\\SSK1", "K2\\SK1\\SSK2",
                "K1", "K1\\SK1", "K1\\SK1\\SSK1", "K1\\SK1\\SSK2", "K1\\SK2"};
        final String[] res = ds.checkDepart(this.array, desc);
        assertThat(res, is(exp));
    }
}