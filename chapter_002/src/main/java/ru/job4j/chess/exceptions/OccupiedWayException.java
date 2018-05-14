package ru.job4j.chess.exceptions;

/**
 * Выбрасывается в случаях, когда фигура не может совершить
 * движение в место назначения по причине того, что на пути следования
 * стоит другая фигура.
 *
 * @author Pyotr Kukharenka
 * @since 05.12.2017
 */

public class OccupiedWayException extends Exception {
    /**
     * Конструктор, с сообщением пользователю.
     *
     * @param message - сообщение.
     */
    public OccupiedWayException(String message) {
        super(message);
    }
}
