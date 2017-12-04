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
