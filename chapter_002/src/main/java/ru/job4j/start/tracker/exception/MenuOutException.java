package ru.job4j.start.tracker.exception;

/**
 * Выбрасывается для индикации того, что выбор пользователя
 * находится вне диапозона допустимых значений
 *
 * @author Pyotr Kukharenka
 * @since 03.12.2017
 */

public class MenuOutException extends RuntimeException {
    /**
     * Конструктор
     * @param msg - сообщение пользователю.
     */
    public MenuOutException(String msg) {
        super(msg);
    }
}
