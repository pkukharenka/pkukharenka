package ru.job4j.start.chess;

import ru.job4j.start.chess.exceptions.FigureNotFoundException;
import ru.job4j.start.chess.exceptions.ImpossibleMoveException;
import ru.job4j.start.chess.exceptions.OccupiedWayException;

/**
 * Класс инициализирует шахматную доску с начальным положением фигур
 * и производит их движение в соответствии с указанными ячейками.
 *
 * @author Pyotr Kukharenka
 * @since 05.12.2017
 */

public class Board {

    /**
     * Двумерный массив, представляющий собой шахматную доску с фигурами,
     * где ячейки без фигур равны null.
     */
    private Figure[][] figures = new Figure[8][8];

    /**
     * Метод, инициализирующий начальнле положение всех фигур.
     */
    public void init() {
        this.figures[0][2] = new Elephant(new Cell(3, 1));
        this.figures[4][6] = new Elephant(new Cell(7, 5));

    }

    /**
     * Метод реализует движение фигуры по шахматной доске. В первую очередь
     * проводится проверка на существовование фигуры в ячейке source. В случае ее
     * существования производится движение фигуры с проверкой наличия на пути движения
     * других фигур. В случае выполнения всех условий позиция фигуры перезаписывается на
     * dest.
     *
     * @param source - начальная точка фигуры.
     * @param dest   - конечная точка фигуры
     * @return возвращает true если все условия выполнены.
     */
    public boolean move(Cell source, Cell dest) {
        boolean flag = false;
        Figure figure = null;
        try {
            figure = this.checkFigure(source);
            this.checkOccupied(figure.way(source, dest));
            figure.clone(dest);
            flag = true;
        } catch (FigureNotFoundException fnfe) {
            System.err.println("В указанной ячейке нет фигуры, попробуйте выбрать другую ячейку.");
        } catch (ImpossibleMoveException ime) {
            System.err.println("Фигура так ходить не умеет, введите другую ячейку.");
        } catch (OccupiedWayException owe) {
            System.err.println("Не возможно совершить ход, на пути есть другие фигуры.");
        }
        return flag;
    }

    /**
     * Метод проверяет наличие фигуры в начальной ячейке.
     *
     * @param source - начальная ячейка.
     * @return - возвращает фигуру, находящуюся в ячейке.
     * @throws FigureNotFoundException- выбрасывается в случая когда в указанной
     *                                  ячейке отсутствует фигура.
     */
    private Figure checkFigure(Cell source) throws FigureNotFoundException {
        Figure figure = null;
        if (this.figures[source.getY() - 1][source.getX() - 1] != null) {
            figure = this.figures[source.getY() - 1][source.getX() - 1];
        } else {
            throw new FigureNotFoundException("В указанной ячейке нет фигуры, попробуйте выбрать другую ячейку.");
        }
        return figure;
    }

    /**
     * Метод проверяет наличие других фигур на пути движения фигуры.
     *
     * @param cells - массив ячеек, которые прошла фигура при своем движении.
     * @throws OccupiedWayException- выбрасывается в случая когда на пути движения фигуры
     *                               имеются другие фигуры, препятствующие движению
     */
    private void checkOccupied(Cell[] cells) throws OccupiedWayException {
        for (Cell cell : cells) {
            if (this.figures[cell.getY() - 1][cell.getX() - 1] != null) {
                throw new OccupiedWayException("Не возможно совершить ход, на пути есть другие фигуры.");
            }
        }
    }
}
