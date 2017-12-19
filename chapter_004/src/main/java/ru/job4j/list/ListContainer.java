package ru.job4j.list;

/**
 * Интерфейс описывающий общее поведение типового контейнера
 * основанного на массивах.
 * Наследут класс Iterable для возможности перебора элементов
 * контейнера.
 *
 * @author Pyotr Kukharenka
 * @see Iterable
 * @since 18.12.2017
 */
public interface ListContainer<E> extends Iterable<E> {
    /**
     * Добавление нового элемента в контейнер
     *
     * @param value - значение элемента
     */
    void add(E value);

    /**
     * Возвращает значение элемента по указанному индексу
     *
     * @param index - индекс ячейки контейнера.
     * @return - значение элемента.
     */
    E get(int index);

    /**
     * Возвращает размер контейнера.
     *
     * @return - размер контейнеар.
     */
    int size();
}
