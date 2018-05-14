package ru.job4j.tracker.io;

/**
 * Базовый интерфейс для системы ввода/вывода программы tracker.
 *
 * @author Pyotr Kukharenka
 * @since 01.12.2017
 */
public interface Input {
    /**
     * Метод, возвращающий ответ пользователя в строковом виде.
     *
     * @param question - вопрос программы.
     * @return - ответ пользователя.
     */
    String ask(String question);

    /**
     * Метод, возвращающий ответ пользователя в виде целого числа.
     * @param question - вопрос трекера
     * @param range - диапозон чисел для корректной работы программы.
     * @return - ответ пользователя.
     */
    int ask(String question, int range);
}
