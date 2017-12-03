package ru.job4j.start.tracker.io;

import java.util.Scanner;

/**
 * Класс консольного взаимодействия с пользователем, реализующий интерфейс Input.
 *
 * @author Pyotr Kukharenka
 * @since 01.12.2017
 */

public class ConsoleInput implements Input {
    /**
     * Сканер для работы с пользователем через консоль.
     */
    private Scanner scanner = new Scanner(System.in);

    /**
     * Возвращает ответ введенный пользователем в консоли.
     *
     * @param question - вопрос программы.
     * @return - ответ пользователя.
     */
    @Override
    public String ask(String question) {
        System.out.println(question);
        return this.scanner.nextLine();
    }
}
