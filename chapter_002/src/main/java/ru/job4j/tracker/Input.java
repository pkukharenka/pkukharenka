package ru.job4j.tracker;

/**
 * Базовый интерфейс для системы ввода/вывода программы tracker.
 *
 * @author Pyotr Kukharenka
 * @since 01.12.2017
 */
public interface Input {
    /**
     * Метод, возвращающий ответ пользователя.
     *
     * @param question - вопрос программы.
     * @return - ответ пользователя.
     */
    String ask(String question);
}
