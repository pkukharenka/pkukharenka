package ru.job4j.monitore;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

/**
 * Потокобезопасный класс счетчик.
 *
 * @author Pyotr Kukharenka
 * @since 04.01.2018
 */
@ThreadSafe
public class Count {

    @GuardedBy("this")
    private int value;

    /**
     * Синхронизированный метод. Увеличивает значение на 1.
     */
    public synchronized void increment() {
        this.value++;
    }

    /**
     * Синхронизированный метод. Возвращет значение.
     *
     * @return - значение
     */
    public synchronized int get() {
        return this.value;
    }

}
