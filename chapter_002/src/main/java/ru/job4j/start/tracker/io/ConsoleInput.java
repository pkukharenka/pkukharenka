package ru.job4j.start.tracker.io;

import ru.job4j.start.tracker.exception.MenuOutException;

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

    /**
     * Метод проверяет валидность введенного пользователем
     * значения и возвращает это значение.
     * В случае ввода пользователем значения отличного от диапозона
     * чисел @param range метод выбрасывает MenuOutException.
     *
     * @param question - вопрос трекера
     * @param range    - диапозон чисел для корректной работы программы.
     * @return - ответ пользователя.
     */
    @Override
    public int ask(String question, int[] range) {
        int key = Integer.valueOf(this.ask(question));
        boolean exists = false;
        for (int value : range) {
            if (value == key) {
                exists = true;
                break;
            }
        }
        if (exists) {
            return key;
        } else {
            throw new MenuOutException("Необходимо ввести числовое значение.");
        }
    }
}
