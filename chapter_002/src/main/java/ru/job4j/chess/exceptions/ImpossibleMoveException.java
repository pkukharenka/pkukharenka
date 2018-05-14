package ru.job4j.chess.exceptions;

/**
 * Выбрасывается в случаях, когда фигура не может совершить
 * движение в место назначения по причине того, что данная фигура
 * так не ходит по правилам игры.
 *
 * @author Pyotr Kukharenka
 * @since 05.12.2017
 */

public class ImpossibleMoveException extends Exception {
    /**
     * Конструктор, с сообщением пользователю.
     *
     * @param message - сообщение.
     */
    public ImpossibleMoveException(String message) {
        super(message);
    }
}
