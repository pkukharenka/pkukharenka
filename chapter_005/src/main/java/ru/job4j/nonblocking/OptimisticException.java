package ru.job4j.nonblocking;

/**
 * Optimistic exception
 *
 * @author Pyotr Kukharenka
 * @since 11.01.2018
 */

public class OptimisticException extends RuntimeException {
    public OptimisticException(String message) {
        super(message);
    }
}
