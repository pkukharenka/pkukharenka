package ru.job4j.elevator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Поток реализующий движение лифта в реальном времени
 *
 * @author Pyotr Kukharenka
 * @since 31.01.2018
 */

public class MoverThread extends Thread {
    /**
     * Логгер
     */
    private static final Logger LOG = LoggerFactory.getLogger(MoverThread.class);
    /**
     * Лифт
     */
    private final Elevator elevator;

    /**
     * Конструктор
     *
     * @param elevator - лифт
     */
    public MoverThread(Elevator elevator) {
        this.elevator = elevator;
    }

    /**
     * Логика работы:
     * 1. Если у лифта есть задачи в очереди -> извлекаем
     * 2. Если этаж вызова является этажом текущего положения лифта
     * просто открываем двери и ждем ввода этажа назначения
     * 3. Если этаж вызова отличен -> перемещаем лифт на этаж
     * вызова и ждем.
     */
    @Override
    public void run() {
        LOG.info(String.format("Лифт находится на %s этаже", this.elevator.getCurrentStage()));
        while (true) {
            if (this.elevator.hasTask()) {
                int nextStage = this.elevator.poll();
                if (this.elevator.getCurrentStage() == nextStage) {
                    try {
                        this.elevator.door();
                    } catch (InterruptedException e) {
                        LOG.error(e.getMessage(), e);
                    }
                } else {
                    try {
                        this.elevator.move(this.elevator.getCurrentStage(), nextStage);
                        this.elevator.door();
                    } catch (InterruptedException e) {
                        LOG.error(e.getMessage(), e);
                    }
                }
            }
        }
    }

}
