package ru.job4j.machine;

import java.util.Arrays;

/**
 * Программа для выдачи сдачи автоматом. В автомате
 * установлены следующие номиналы монет (1, 2, 5, 10). При этом
 * мы принимаем, что массив с номиналами отсортирован по убыванию.
 *
 * @author Pyotr Kukharenka
 * @since 08.12.2017
 */

public class Automat {
    /**
     * Номиналы монет в автомате.
     */
    private int[] moneys = {10, 5, 2, 1};

    /**
     * Метод возвращает массив с номиналами монет, которые
     * необходимо выдать в качестве сдачи, при этом выдается
     * минимальное колиечство монет.
     *
     * @param value - номинал, который внес пользователь.
     * @param price - цена купленного твоара.
     * @return - массив с номиналами монет.
     */
    public int[] changes(int value, int price) {
        int transfer = value - price;
        return this.calc(transfer);
    }

    /**
     * Метод производит расчет номиналов, которые необходимо
     * выдать пользователю с учетом наименьшего количества
     * монет. Логика заключается в вычитании поочередно
     * номиналов монет начиная с большего и сравнения его с нулем.
     * В случае если сдача равна 0 массив сформирован. Возвращаем
     * обрезанный массив за счет счетчика count.
     *
     * @param transfer - сдача, которую необходимо обсчитать
     * @return - массив номиналов.
     */
    private int[] calc(int transfer) {
        int count = 0;
        int[] values = new int[transfer];
        for (int index = 0; index < values.length; index++) {
            for (int money : this.moneys) {
                if ((transfer - money) >= 0) {
                    values[index] = money;
                    transfer -= money;
                    count++;
                    break;
                }
            }
        }
        return Arrays.copyOf(values, count);
    }
}
