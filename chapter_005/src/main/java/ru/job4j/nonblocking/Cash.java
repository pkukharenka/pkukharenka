package ru.job4j.nonblocking;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Неблокирующий кэш для хранения пользователей
 *
 * @author Pyotr Kukharenka
 * @since 09.01.2018
 */

public class Cash {
    /**
     * Структура для хранения
     */
    private final ConcurrentHashMap<Integer, User> cash;

    /**
     * Инициализация хранилища.
     */
    public Cash() {
        this.cash = new ConcurrentHashMap<>(100);
    }

    /**
     * Добавление пользователя.
     *
     * @param user - новый пользователь.
     */
    public void add(User user) {
        this.cash.put(user.getId(), user);
    }

    /**
     * Обновление пользователя, неблокирующий алгоритм.
     *
     * @param user - обновленные данные.
     * @return - true, если пользователь обновлен.
     */
    public void update(User user) {
        AtomicInteger oldVersion = cash.get(user.getId()).getVersion();
        int newVersion;
        do {
            newVersion = oldVersion.incrementAndGet();
            this.cash.computeIfPresent(user.getId(), (k, v) -> user);
        } while (!oldVersion.compareAndSet(oldVersion.get(), newVersion));
    }

    /**
     * Удалеят пользователя по id.
     *
     * @param id - id пользователя.
     */
    public void delete(int id) {
        this.cash.remove(id);
    }

    /**
     * Возвращает пользователя по id.
     *
     * @param id - id пользователя.
     * @return - пользователя
     */
    public User get(int id) {
        return this.cash.get(id);
    }
}
