package ru.job4j.start.tracker;

import ru.job4j.start.tracker.io.Input;

/**
 * Интерфейс для событий программы (добавление заявок, удаление, обновление и т.д.)
 *
 * @author Pyotr Kukharenka
 * @since 03.12.2017
 */
public interface UserAction {
    /**
     * Метод возвращает ключ действия программы, например: 0 - для добавления заявки
     * 1 - для показа всех заявок и т.д.
     *
     * @return ключ.
     */
    int key();

    /**
     * Метод производит действие.
     *
     * @param input   - ввод/вывод.
     * @param tracker - хранилище заявок
     */
    void execute(Input input, Tracker tracker);

    /**
     * Метод возвращает информацию о действие.
     *
     * @return - название действия.
     */
    String info();
}
