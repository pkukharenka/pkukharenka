package ru.job4j.start.chess;

import ru.job4j.start.chess.exceptions.ImpossibleMoveException;

/**
 * Описание шахматной фигуры - СЛОН. Слон ходит по диагонали.
 *
 * @author Pyotr Kukharenka
 * @since 05.12.2017
 */

public class Elephant extends Figure {

    /**
     * Конструктор, инициализирующий фигуру с ее местоположением на доске.
     *
     * @param position - позиция фигуры.
     */
    public Elephant(Cell position) {
        super(position);
    }

    /**
     * Метод описывающий порядок движения слона. Возвращает массив с ячейками
     * которые слон прошел во время хода.
     *
     * @param source - начальная ячейка.
     * @param dest   - ячейка назначения.
     * @return - массив ячеек.
     * @throws ImpossibleMoveException- выбрасывается в случая когда фигура не может
     *                                  ходить таким образом по правилам игры
     */
    @Override
    Cell[] way(Cell source, Cell dest) throws ImpossibleMoveException {
        if (this.checkWay(source, dest)) {
            Cell[] cells = new Cell[this.cellSize(source, dest)];
            for (int index = 0; index < cells.length; index++) {
                if (source.getX() < dest.getX() || source.getY() < dest.getY()) {
                    cells[index] = new Cell(source.getX() + 1 + index, source.getY() + 1 + index);
                } else {
                    cells[index] = new Cell(source.getX() - 1 - index, source.getY() - 1 - index);
                }
            }
            return cells;
        } else {
            throw new ImpossibleMoveException("Фигура так ходить не умеет, введите другую ячейку.");
        }
    }

    /**
     * Метод проверяет правильность движения слона. Логика:
     * Слон ходит по диагонали, поэтому количество ячеек пройденные
     * по X должны равняться количеству по Y. Значения возводятся в квадрат
     * для того чтобы избежать отрицательных значений.
     * В случае если Х и Y равны и их значения не выходят за пределы доски, значит
     * движение валидно.
     *
     * @param source - начальная точка фигуры.
     * @param dest   - конечная точка фигуры.
     * @return - возвращает true если условие удовлетворено.
     */
    private boolean checkWay(Cell source, Cell dest) {
        boolean flag = false;
        double x = Math.pow((dest.getX() - source.getX()), 2);
        double y = Math.pow((dest.getY() - source.getY()), 2);
        if (x == y && dest.getY() <= 8 && dest.getY() > 0 && dest.getX() <= 8 && dest.getX() > 0) {
            flag = true;
        }
        return flag;
    }

    private int cellSize(Cell source, Cell dest) {
        return ((int) Math.sqrt(Math.pow(dest.getX() - source.getX(), 2)));
    }
}
