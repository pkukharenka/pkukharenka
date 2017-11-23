package ru.job4j.array;

/**
 * Turn array class.
 *
 * @author Pyotr Kukharenka
 * @since 23.11.2017
 */
public class Turn {

    /**
     * Method that turn array.
     *
     * @param array - simple array.
     * @return - turn array.
     */
    public int[] back(int[] array) {
        for (int i = 0; i < array.length - i; i++) {
            int temp = array[i];
            array[i] = array[array.length - i - 1];
            array[array.length - i - 1] = temp;

        }
        return array;
    }

}
