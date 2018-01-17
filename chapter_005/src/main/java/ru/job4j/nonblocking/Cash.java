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
        int oldVersion = cash.get(user.getId()).getVersion();
        if (oldVersion == user.getVersion()) {
            user.setVersion(oldVersion + 1);
            cash.computeIfPresent(user.getId(), (k, v) -> user);
            return true;
        } else {
            throw new RuntimeException("Кто-то изменил данные");
        }

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
