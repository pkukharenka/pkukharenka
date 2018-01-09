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
        cash.put(user.getId(), user);
    }

    /**
     * Обновление пользователя, неблокирующий алгоритм.
     *
     * @param user - обновленные данные.
     * @return - true, если пользователь обновлен.
     */
    public boolean update(User user) {
        boolean isUpdate = false;
        AtomicInteger version = user.getVersion();
        int next = version.get() + 1;
        if (version.compareAndSet(version.get(), next)) {
            cash.computeIfPresent(user.getId(), (k, v) -> user);
            isUpdate = true;
        }
        return isUpdate;
    }

    /**
     * Удалеят пользователя по id.
     *
     * @param id - id пользователя.
     */
    public void delete(int id) {
        cash.remove(id);
    }

    /**
     * Возвращает пользователя по id.
     *
     * @param id - id пользователя.
     * @return - пользователя
     */
    public User get(int id) {
        return cash.get(id);
    }
}
