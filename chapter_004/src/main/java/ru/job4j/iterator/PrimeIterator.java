package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Итератор массива по простым числам.
 *
 * @author Pyotr Kukharenka
 * @since 1
 */

public class PrimeIterator implements Iterator<Integer> {
    /**
     * Позиция указателя в массиве.
     */
    private int pos;
    /**
     * Массив целочисленных значений.
     */
    private int[] numbers;

    /**
     * Конструктор для инициализации масисива.
     *
     * @param numbers - масисв.
     */
    public PrimeIterator(int[] numbers) {
        this.numbers = numbers;
    }

    /**
     * Метод проверяет есть ли в массиве очередное
     * простое число. Если значение возвращаемое
     * методом checkPrime больше 0 значит значение есть.
     *
     * @return true - если значение есть, false - если нет.
     */
    @Override
    public boolean hasNext() {
        return this.checkPrime() >= 0;
    }

    /**
     * Метод возвращает очередное простое число массива. Во-первых
     * проверяем есть ли очередное простое число. Если есть находим
     * позицию этого элемента в массиве с помощью метода checkPrime
     * и возвращаем значение по этому индексу. При этом поиск следующих
     * элементов будет начинаться с позиции указателя.
     *
     * @return - очередное простое число.
     * @throws NoSuchElementException - выбрасывается если простых чисел нет.
     */
    @Override
    public Integer next() throws NoSuchElementException {
        if (this.hasNext()) {
            this.pos = this.checkPrime() + 1;
            return this.numbers[this.pos - 1];
        } else {
            throw new NoSuchElementException("Нет простых числе");
        }
    }

    /**
     * Метод проверки простоты числа. Если элемент массива равен 2 сразу
     * возвращаем его индекс и прерываем цикл. Если нет, то простое число
     * будет определятся по следующему правилу: Если у числа нет делителей без
     * остатка в диапозоне чисел от 2 до корня из этого числа, то оно простое.
     * Если найден хоть один делитель сразу выходим из цикла. Делаем проверку
     * если value отлично от -1 значит очередное простое число найдено выходим
     * из верхнего цикла.
     *
     * @return - индекс ячейки массива где находится простое число или -1 если
     * простых чисел нет.
     */
    private int checkPrime() {
        int value = -1;
        for (int index = this.pos; index < this.numbers.length; index++) {
            if (this.numbers[index] == 2) {
                value = index;
                break;
            }
            int res = (int) Math.ceil(Math.sqrt(this.numbers[index]));
            for (int number = 2; number <= res; number++) {
                if (this.numbers[index] % number != 0 && number == res) {
                    value = index;
                    break;
                } else if (this.numbers[index] % number == 0) {
                    break;
                }
            }
            if (value != -1) {
                break;
            }
        }
        return value;
    }
}
