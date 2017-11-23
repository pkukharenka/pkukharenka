package ru.job4j.array;

/**
 * Class for sort array of bubble sorting method.
 *
 * @author Pyotr Kukharenka
 * @since 23.11.2017
 */
public class BubbleSort {
    /**
     * Array bubble sorting method.
     *
     * @param array - simple array.
     * @return - sorting array.
     */
    public int[] sort(int[] array) {
        for (int i = 0; i <= array.length - 1; i++) {
            for (int j = i; j <= array.length - 1; j++) {
                if (array[j] < array[i]) {
                    int temp = array[i];
                    array[i] = array[j];
                    array[j] = temp;
                }
            }

        }
        return array;
    }
}
