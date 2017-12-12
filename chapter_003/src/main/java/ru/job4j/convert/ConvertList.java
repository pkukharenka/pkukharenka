package ru.job4j.convert;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * Класс для конвертации коллекций в двумерный массив
 * и наооборот.
 *
 * @author Pyotr Kukharenka
 * @since 12.12.2017
 */

public class ConvertList {

    /**
     * Метод производит преобразование двумерного массива
     * в коллекцию ArrayList.
     *
     * @param array - входящий массив
     * @return коллекция.
     */
    public List<Integer> toList(int[][] array) {
        List<Integer> list = new ArrayList<>();
        for (int[] values : array) {
            for (int value : values) {
                list.add(value);
            }
        }
        return list;
    }

    /**
     * Метод производит преобразование коллекции в двумерный массив.
     * При этом, в качестве параметра в метод передается количество
     * строк создаваемого массива. В случае если количество элементов
     * в коллекции меньше необходимых для заполнения в массив, последние
     * элементы заполняются нулями. Определение количества столбцов массива
     * происходит путем деления количества элементов коллекции на число строк
     * с округлением в большую сторону.
     *
     * @param list - входящая колеекция.
     * @param rows - количество строк массива
     * @return двумерный массив.
     */
    public int[][] toArray(List<Integer> list, int rows) {
        int index = 0;
        //полуаем колиечство столбцов типа double для дальнейшего
        //преобразования в int с округлением до высшего целого
        double result = (double) list.size() / rows;
        int columns = (int) Math.ceil(result);
        int[][] array = new int[rows][columns];
        for (int row = 0; row < array.length; row++) {
            for (int column = 0; column < columns; column++) {
                if (index < list.size()) {
                    array[row][column] = list.get(index);
                } else {
                    array[row][column] = 0;
                }
                index++;
            }
        }
        return array;
    }

    /**
     * Метод преобразует коллекцию массивов в коллекцию
     * элементов типа Integer.
     *
     * @param list - коллекция массивов
     * @return коллекция элементов всех массивов.
     */
    public List<Integer> convert(List<int[]> list) {
        //Результирующая коллекция
        List<Integer> values = new ArrayList<>();
        //Итератор по входящей коллекции
        Iterator<int[]> it = list.iterator();
        //Цикл по входящей коллекции для перербора всех масисвов
        while (it.hasNext()) {
            //Цикл для перебора элементов массивов
            for (int number : it.next()) {
                values.add(number);

            }
        }
        return values;
    }

}
