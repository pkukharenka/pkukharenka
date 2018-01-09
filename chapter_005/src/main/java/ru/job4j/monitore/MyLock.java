package ru.job4j.monitore;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Блокировка.
 *
 * @author Pyotr Kukharenka
 * @since 09.01.2018
 */

public class MyLock {
    /**
     * Сотояние блокировки.
     */
    private AtomicInteger state;

    /**
     * Конструктор, устанавливающий начальное состояние блокировки.
     */
    public MyLock() {
        this.state = new AtomicInteger(0);
    }

    /**
     * Установка блокировки, если значение состояние 0, то
     * меняем его на 1.
     */
    public void lock() {
        this.state.compareAndSet(0, 1);
    }
    /**
     * Снимаем блокировку, если значение состояние 1, то
     * меняем его на 0.
     */
    public void unlock() {
       this.state.compareAndSet(1, 0);
    }
}
