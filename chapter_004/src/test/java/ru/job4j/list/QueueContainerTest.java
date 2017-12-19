package ru.job4j.list;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

/**
 * Тесты для проверки работы очереди.
 *
 * @author Pyotr Kukharenka
 * @since 19.12.2017
 */
public class QueueContainerTest {
    /**
     * Проверяем что элемент попавший первым в список
     * выходит первым.
     */
    @Test
    public void whenPushTwoElementsThenPollIs1() {
        QueueContainer<String> queue = new QueueContainer<>();
        queue.push("1");
        queue.push("2");
        assertThat(queue.poll(), is("1"));
    }

    /**
     * Проверяем что метод poll удаляет из списка элемент.
     */
    @Test
    public void whenPushTwoElementsAndPollOneThenSizeIs1() {
        QueueContainer<String> queue = new QueueContainer<>();
        queue.push("1");
        queue.push("2");
        queue.poll();
        assertThat(queue.size(), is(1));
    }
}