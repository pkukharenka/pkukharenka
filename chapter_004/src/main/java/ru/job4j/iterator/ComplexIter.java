package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Итератор по вложенным итераторам.
 *
 * @author Pyotr Kukharenka
 * @since 17.12.2017
 */

public class ComplexIter {
    /**
     * Метод приводящий итератор итераторов к итератору по всем значениям входящих
     * итераторов.
     *
     * @param it - итератор итераторов
     * @return - итератор по элементам вложенных итераторов
     */
    public Iterator<Integer> convert(Iterator<Iterator<Integer>> it) {
        return new Iterator<Integer>() {
            /**
             * Текущий итератор.
             */
            private Iterator<Integer> current = it.next();

            /**
             * Проверка наличия следующего итератора.
             * @return - true если есть очередной итератор
             */
            @Override
            public boolean hasNext() {
                boolean flag = false;
                if (this.current.hasNext()) {
                    flag = true;
                } else {
                    if (it.hasNext()) {
                        this.current = it.next();
                        flag = this.hasNext();
                    }
                }
                return flag;
            }

            /**
             * Метод возвращает очередное значение или выбрасывает exception
             * если значение больше нет. Работы выполняется в 2 этапа:
             * 1. Если текущий итератор имеет очередное значение возвращаем
             * это значение.
             * 2. Если у текущего итератора очередных значение нет, то меняем
             * указатель на следующий итератор и за счет рекурсии вызываем повторно
             * данный метод для очередного итератора и возвращаем очередное значение из
             * данного итератора.
             * @return очередное значение или exception.
             * @throws NoSuchElementException - выбрасывается если больше элементов
             *                                нет
             */
            @Override
            public Integer next() throws NoSuchElementException {
                int value;
                if (this.current.hasNext()) {
                    value = this.current.next();
                } else {
                    if (it.hasNext()) {
                        this.current = it.next();
                        value = this.next();
                    } else {
                        throw new NoSuchElementException("Больше нет элементов");
                    }
                }
                return value;
            }
        };
    }

}
