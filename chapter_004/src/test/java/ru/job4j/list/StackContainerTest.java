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
public class StackContainerTest {
    /**
     * Проверяем что элемент попавший последним в список
     * выходит первым.
     */
    @Test
    public void whenPushThreeElementsThenPollReturnLast() {
        StackContainer<Integer> stack = new StackContainer<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        assertThat(stack.poll(), is(3));
    }

    /**
     * Проверяем что метод poll удаляет из списка элемент.
     */
    @Test
    public void whenPushOneElementAndPollThenSizeZero() {
        StackContainer<Integer> stack = new StackContainer<>();
        stack.push(1);
        stack.poll();
        assertThat(stack.size(), is(0));
    }
}