package ru.job4j.loop;

/**
 * Class for counting factorial n.
 *
 * @author Pyotr Kukharenka
 * @since 22.11.2017
 */
public class Factorial {

    /**
     * Factorial n.
     *
     * @param n - number.
     * @return factorial n or exception.
     */
    public int calc(final int n) {
        int result = -1;
        if (n >= 0) {
            result = 1;
            for (int i = 1; i <= n; i++) {
                result = result * i;
            }
        }
        return result;
    }
}