package ru.job4j.sort;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

/**
 * Класс для тестирования компаратора класса User.
 *
 * @author Pyotr Kukharenka
 * @since 13.12.2017
 */
public class UserTest {
    /**
     * Константы для тестирования.
     */
    private static final User FIRST = new User("petr", 12);
    private static final User SECOND = new User("ivan", 13);
    private static final User THIRD = new User("artem", 13);

    /**
     * Метод сравнивает двух пользователей и должен вернуть -1, т.к.
     * у первого возраст меньше чем у второго.
     */
    @Test
    public void whenFirstUserLessThenSecondThenMinus() {
        assertThat(FIRST.compareTo(SECOND), is(-1));
    }

    /**
     * Метод сравнивает двух пользователей и должен вернуть 1, т.к.
     * у второго возраст больше чем у первого.
     */
    @Test
    public void whenSecondUserMoreThenFirstThenPlus() {
        assertThat(SECOND.compareTo(FIRST), is(1));
    }

    /**
     * Метод сравнивает двух пользователей и должен вернуть значение
     * больше 0, т.к. возрасты двух пользователей равны и мы сравниваем
     * имена, т.к. имя по алфавиту первого пользователя выше чем у второго
     * должно получится положительное число.
     */
    @Test
    public void whenSecondAgeEqualsThirdThenZero() {
        assertThat(SECOND.compareTo(THIRD) > 0, is(true));
    }
}