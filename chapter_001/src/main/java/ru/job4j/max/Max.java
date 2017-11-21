package ru.job4j.max;

/**
 * Program that determines the maximum of two numbers.
 *
 * @author Pyotr Kukharenka
 * @since 21.11.2017
 */

public class Max {

    /**
     * Method that determines max number.
     *
     * @param first  - first number.
     * @param second - second number.
     * @return maximum.
     */
    public int max(final int first, final int second) {
        return first > second ? first : second;
    }
}
