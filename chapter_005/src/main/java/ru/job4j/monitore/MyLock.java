package ru.job4j.monitore;

import java.util.concurrent.atomic.AtomicBoolean;

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
    private AtomicBoolean state;
    private Thread owner;

    /**
     * Конструктор, устанавливающий начальное состояние блокировки.
     */
    public MyLock() {
        this.state = new AtomicBoolean(false);
        this.owner = null;
    }

    /**
     * Установка блокировки, если значение состояние 0, то
     * меняем его на 1.
     */
    public void lock() {
        if (state.compareAndSet(false, true)) {
            this.owner = Thread.currentThread();
        } else {
            synchronized (this) {
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void unlock() {
        final Thread current = Thread.currentThread();
        if (this.owner == current) {
            this.owner = null;
            this.state.compareAndSet(true, false);
            synchronized (this) {
                this.notify();
            }
        }
    }
}
