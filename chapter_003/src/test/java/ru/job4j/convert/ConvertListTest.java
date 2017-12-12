package ru.job4j.convert;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

/**
 * Тесты конвертаций.
 *
 * @author Pyotr Kukharenka
 * @since 12.12.2017
 */
public class ConvertListTest {

    /**
     * Тест проверяет конвертацию двумерного массива в коллекцию.
     * Размер коллекции должен быть 9.
     */
    @Test
    public void whenArrayConvertToListThenSize() {
        ConvertList convert = new ConvertList();
        int[][] array = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        assertThat(convert.toList(array).size(), is(9));
    }

    /**
     * Тест проверяет конвертацию коллекции в двумерный массив.
     * Сравниваются масисвы.
     */
    @Test
    public void whenListConvertToArrayThen() {
        ConvertList convert = new ConvertList();
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        list.add(6);
        list.add(7);
        list.add(8);
        int[][] exp = {{1, 2, 3}, {4, 5, 6}, {7, 8, 0}};
        assertThat(convert.toArray(list, 3), is(exp));
    }

    /**
     * Тест проверяет конвертацию коллекции массивов в
     * коллекцию элементов этих массивов.
     */
    @Test
    public void whenListOfArraysConvertToAnotherListThen() {
        ConvertList convert = new ConvertList();
        List<int[]> list = new ArrayList<>();
        list.add(new int[]{1, 2, 3, 4});
        list.add(new int[]{5, 6, 7, 8});
        list.add(new int[]{9, 10});
        assertThat(convert.convert(list).size(), is(10));
    }

}