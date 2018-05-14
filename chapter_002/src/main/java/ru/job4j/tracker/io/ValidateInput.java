package ru.job4j.tracker.io;

import ru.job4j.tracker.exception.MenuOutException;

/**
 * Класс, наследующий класс ConsoleInput. Позволяет проверить валидность
 * введеных пользователем значений и не допустить сбоя в работе программы
 *
 * @author Pyotr Kukharenka
 * @since 03.12.2017
 */

public class ValidateInput extends ConsoleInput {
    /**
     * Метод возвращает целочисленный ответ пользователя. В методе
     * перехватываются ошибки ввода значений:
     * NumberFormatException - введено что-то иное отличное от целого числа
     * пользовательский exception - MenuOutException - введено значение не из диапозона
     * чисел для корректной работы программы.
     *
     * @param question - вопрос программы.
     * @param range    - диапозон чисел для корректной работы программы
     * @return - ответ пользователя.
     */
    public int ask(String question, int range) {
        boolean valid = false;
        int key = 0;
        do {
            try {
                key = super.ask(question, range);
                valid = true;
            } catch (NumberFormatException nfe) {
                System.err.println("Введите корректное значение.");
            } catch (MenuOutException moe) {
                System.err.println("Введите значение из диапозона работы программы");
            }
        } while (!valid);
        return key;
    }
}
