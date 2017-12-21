package ru.job4j.set;

import org.junit.Test;

import java.util.Iterator;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

/**
 * Тесты для проверки работы контейнера типа Set на базе связного списка
 *
 * @author Pyotr Kukharenka
 * @since 21.12.2017
 */
public class LinkedSetContainerTest {
    /**
     * Првоеряем что дубликат не добавился, и размер списка 1.
     */
    @Test
    public void whenAddDuplicateThenSizeNotChange() {
        LinkedSetContainer<Integer> set = new LinkedSetContainer<>();
        set.add(1);
        set.add(1);
        set.add(1);
        assertThat(set.size(), is(1));
    }

    /**
     * Првоеряем что дубликат не добавился, и размер списка 2.
     */
    @Test
    public void whenAddThenSizeChange() {
        LinkedSetContainer<Integer> set = new LinkedSetContainer<>();
        set.add(1);
        set.add(2);
        set.add(1);
        assertThat(set.size(), is(2));
    }
}