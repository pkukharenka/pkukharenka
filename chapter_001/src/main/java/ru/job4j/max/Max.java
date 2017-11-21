package ru.job4j.max;

/**
 * Program that determines the maximum number.
 *
 * @author Pyotr Kukharenka
 * @since 21.11.2017
 */

public class Max {

    /**
     * Method that determines max from two numbers.
     *
     * @param first  - first number.
     * @param second - second number.
     * @return maximum.
     */
    public int max(final int first, final int second) {
        return first > second ? first : second;
    }

    /**
     * Method that determines max from three numbers
     *
     * @param first  - first number.
     * @param second - second number.
     * @param third  - third number
     * @return maximum
     */
    public int max(final int first, final int second, final int third) {
        return max(first, max(second, third));
    }
}
