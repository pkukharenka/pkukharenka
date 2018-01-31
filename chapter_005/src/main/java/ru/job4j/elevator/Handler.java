package ru.job4j.elevator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Scanner;

/**
 * Поток, мониторящий ввод в консоль значений
 *
 * @author Pyotr Kukharenka
 * @since 31.01.2018
 */

public class Handler extends Thread {
    /**
     * Логгер
     */
    private static final Logger LOG = LoggerFactory.getLogger(Handler.class);
    /**
     * Сканер
     */
    private final Scanner scanner = new Scanner(System.in);
    /**
     * Лифт
     */
    private final Elevator elevator;

    /**
     * Конструктор
     *
     * @param elevator - лифт
     */
    public Handler(Elevator elevator) {
        this.elevator = elevator;
    }

    /**
     * Если значение, считанное с консоли является числом в пределах
     * этажности подъезда - записываем его в очередь
     */
    @Override
    public void run() {
        while (true) {
            try {
                int number = Integer.valueOf(this.scanner.nextLine());
                if (number > 0 && number <= this.elevator.getStageCount()) {
                    this.elevator.push(number);
                }
            } catch (NumberFormatException e) {
                LOG.error(e.getMessage(), e);
            }

        }
    }
}
