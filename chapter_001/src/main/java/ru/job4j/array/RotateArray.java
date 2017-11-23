package ru.job4j.array;

/**
 * Rotate array class.
 *
 * @author Pyotr Kukharenka
 * @since 23.11.2017
 */

public class RotateArray {

    /**
     * Rotate array method.
     * @param array - simple array.
     * @return - rotate array.
     */
    public int[][] rotate(int[][] array) {
        int[][] temp = new int[array[0].length][array.length];
        for (int i = 0; i <= array.length - 1; i++) { //rows
            for (int j = 0; j <= array.length - 1; j++) { //columns
               temp[j][i] = array[array.length - i - 1][j];

            }
        }
        return temp;
    }
}
