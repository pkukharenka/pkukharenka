package ru.job4j.chess.exceptions;

/**
 * Выбрасывается в случаях, когда требуемой фигуры не существует.
 *
 * @author Pyotr Kukharenka
 * @since 05.12.2017
 */

public class FigureNotFoundException extends Exception {
    /**
     * Конструктор, с сообщением пользователю.
     *
     * @param message - сообщение.
     */
    public FigureNotFoundException(String message) {
        super(message);
    }
}
