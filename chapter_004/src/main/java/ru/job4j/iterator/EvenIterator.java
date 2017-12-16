package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Итератор по массиву, возвращающий только четные числа.
 *
 * @author Pyotr Kukharenka
 * @since 16.12.2017
 */

public class EvenIterator implements Iterator<Integer> {
    /**
     * Позиция указателя в массиве
     */
    private int pos;
    /**
     * Массив с числами
     */
    private final int[] numbers;

    /**
     * Конструктор для инициализации массива
     *
     * @param numbers - массив
     */
    public EvenIterator(int[] numbers) {
        this.numbers = numbers;
    }

    /**
     * Метод проверяет есть ли в массиве следующее четное значение
     *
     * @return - true если значение есть.
     */
    @Override
    public boolean hasNext() {
        return this.exist() >= 0;
    }

    /**
     * Метод возвращает либо очередное четное значение, либо
     * выкидывает Exception о его отсутствии
     *
     * @return - очередной четный элемент.
     * @throws NoSuchElementException - выбрасывается если больше четных элементов
     *                                нет
     */
    @Override
    public Integer next() throws NoSuchElementException {
        if (this.hasNext()) {
            this.pos = this.exist() + 1;
            return this.numbers[this.pos - 1];
        } else {
            throw new NoSuchElementException("Больше нет четных элементов");
        }
    }

    /**
     * Метод проводит поиск ячейки в которой находится четное число.
     * При это каждый последующий поиск элемента будет уменьшать
     * количество итераций цикла на pos. Если очередное четное число
     * найдено не будет метод вернет -1.
     *
     * @return - индекс ячейки в массиве где находится четное число.
     */
    private Integer exist() {
        int value = -1;
        for (int index = this.pos; index < this.numbers.length; index++) {
            if (this.numbers[index] % 2 == 0) {
                value = index;
                break;
            }
        }
        return value;
    }
}
