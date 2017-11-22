package ru.job4j.loop;

/**
 * Class for counting even numbers in a range.
 *
 * @author Pyotr Kukharenka
 * @since 22.11.2017
 */
public class Counter {

    /**
     * Counting even numbers in a range from start to finish.
     *
     * @param start  - start number.
     * @param finish - finish number
     * @return - sum.
     */
    public int add(final int start, final int finish) {
        int res = 0;
        for (int i = start; i <= finish; i++) {
            if (i % 2 == 0) {
                res = res + i;
            }
        }
        return res;
    }

}
