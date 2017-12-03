package ru.job4j.start.tracker.io;

import ru.job4j.start.tracker.exception.MenuOutException;

/**
 * Класс, имитирующий работу системы ввода/вывода.
 *
 * @author Pyotr Kukharenka
 * @since 03.12.2017
 */

public class StubInput implements Input {
    /**
     * Массив ответов пользователя.
     */
    private String[] answers;
    /**
     * Позиция массива ответов, для последовательного ввода значений.
     */
    private int position = 0;

    /**
     * Конструктор, инициализирующий массив с ответами пользователя.
     *
     * @param answers
     */
    public StubInput(String[] answers) {
        this.answers = answers;
    }

    /**
     * Метод возвращающий ответ пользователя из массива.
     *
     * @param question - вопрос программы.
     * @return - овтет пользователя из массива.
     */
    @Override
    public String ask(String question) {
        return this.answers[this.position++];
    }

    /**
     * Метод возвращающий овтет пользователя с проверкой на валидность
     * введенного значения.
     *
     * @param question - вопрос трекера
     * @param range    - диапозон чисел для корректной работы программы.
     * @return - ответ пользователя.
     */
    @Override
    public int ask(String question, int[] range) {
        boolean exists = false;
        int key = 0;
        do {
            try {
                key = Integer.valueOf(this.ask(question));
                for (int value : range) {
                    if (value == key) {
                        exists = true;
                        break;
                    }
                }
            } catch (NumberFormatException nfe) {
                System.err.println("Введите корректное значение.");
            } catch (MenuOutException moe) {
                System.err.println("Введите значение из диапозона работы программы");
            }
        } while (!exists);
        return key;
    }

}
