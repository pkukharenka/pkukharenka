package ru.job4j.tree.exception;

/**
 * Ошибка отсутствия корневого элемента в древовидной структуре.
 *
 * @author Pyotr Kukharenka
 * @since 26.12.2017
 */

public class InvalidRootException extends RuntimeException {
    /**
     * Конструктор по умолчанию.
     */
    public InvalidRootException() {
    }

    /**
     * Конструктор, позволяющий передавать пользователю
     * информационную строку об ошибке.
     *
     * @param message - информация об ошибке.
     */
    public InvalidRootException(String message) {
        super(message);
    }
}
