package ru.job4j.start.chess;

import ru.job4j.start.chess.exceptions.ImpossibleMoveException;

/**
 * Асбтрактный класс, описывающий общее поведение шахматных
 * фигур
 *
 * @author Pyotr Kukharenka
 * @since 05.12.2017
 */

public abstract class Figure {
    /**
     * Позиция фигуры нашахматной доске.
     */
    private Cell position;

    /**
     * Конструктор, инициализирующий фигуру с ее местоположением на доске.
     *
     * @param position - позиция фигуры.
     */
    public Figure(Cell position) {
        this.position = position;
    }

    /**
     * Абстрактный метод, описывающий движение фигуры по шпхматной доке.
     * Если фигура не может пойти в место назначения (dest) метод должен
     * выбросить Exception.
     *
     * @param source - начальная ячейка.
     * @param dest   - ячейка назначения.
     * @return - массив ячеек, которые прошла фигура до места назначения.
     * @throws ImpossibleMoveException - выбрасывается в случая когда фигура не может
     *                                 ходить таким образом по правилам игры
     */
    abstract Cell[] way(Cell source, Cell dest) throws ImpossibleMoveException;

    /**
     * Метод производит перезапись положения фигуры на шахматной доске при
     * соблюдении всех условий программы.
     *
     * @param dest - точка куда фигура совершила ход.
     */
    public void clone(Cell dest) {
        this.position = dest;
    }
}
