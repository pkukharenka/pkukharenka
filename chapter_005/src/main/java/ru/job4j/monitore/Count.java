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
    private final MyLock lock = new MyLock();
    @GuardedBy("lock")
    private int value;

    /**
     * Синхронизированный метод. Увеличивает значение на 1.
     */
    public void increment() {
        this.lock.lock();
        this.value++;
        this.lock.unlock();
    }

    /**
     * Синхронизированный метод. Возвращет значение.
     *
     * @return - значение
     */
    public int get() {
        int res = 0;
        this.lock.lock();
        res = this.value;
        this.lock.unlock();
        return res;
    }
}
