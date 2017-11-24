package ru.job4j.array;

import java.util.Arrays;

/**
 * Remove duplicate string form array.
 *
 * @author Pyotr Kukharenka
 * @since 24.11.2017
 */

public class ArrayDuplicate {

    /**
     * Remove duplicate string form array.
     *
     * @param array - simple array.
     * @return array with unique string.
     */
    public String[] remove(String[] array) {
        int dupl = 0;
        for (int i = 0; i < array.length; i++) {
            for (int j = i + 1; j < array.length - dupl;) {
                if (array[i].equals(array[j])) {
                    array[j] = array[array.length - dupl - 1];
                    array[array.length - dupl - 1] = "duplicate";
                    dupl++;
                 } else {
                    j++;
                }
            }
        }
        return Arrays.copyOf(array, array.length - dupl);
    }
}
