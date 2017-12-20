package ru.job4j.set;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

/**
 * Тесты для проверки работы контейнера типа Set
 *
 * @author Pyotr Kukharenka
 * @since 20.12.2017
 */
public class SetContainerTest {
    /**
     * Првоеряем что дубликат не добавился, и размер списка 2.
     */
    @Test
    public void whenAddDuplicateThenSizeNotChange() {
        SetContainer<String> set = new SetContainer<>();
        set.add("1");
        set.add("2");
        set.add("1");
        assertThat(set.size(), is(2));
    }

}