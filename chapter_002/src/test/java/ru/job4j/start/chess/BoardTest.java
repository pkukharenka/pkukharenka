package ru.job4j.start.chess;

import org.junit.Test;
import ru.job4j.start.chess.exceptions.FigureNotFoundException;
import ru.job4j.start.chess.exceptions.ImpossibleMoveException;
import ru.job4j.start.chess.exceptions.OccupiedWayException;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

/**
 * Тесты для проверки логики работы шахматной игры.
 *
 * @author Pyotr Kukharenka
 * @since 05.12.2017
 */
public class BoardTest {
    /**
     * Тест для проверки работы программы с валидными значениями ячеек
     * Слон находится в ячейке 3-1 и передвигается в ячейку 6-4
     */
    @Test
    public void whenElephantValidMoveThenTrue() {
        Board board = new Board();
        board.init();
        Cell source = new Cell(3, 1);
        Cell dest = new Cell(6, 4);
        assertThat(board.move(source, dest), is(true));
    }

    /**
     * Тест возвращает false при отстутсвии в ячейке фигуры.
     *
     * @throws FigureNotFoundException
     */
    @Test
    public void whenFigureNotFoundThenFalse() throws FigureNotFoundException {
        Board board = new Board();
        board.init();
        Cell source = new Cell(3, 2);
        Cell dest = new Cell(6, 4);
        assertThat(board.move(source, dest), is(false));
    }

    /**
     * Тест возвращает false при попытке неправильного движения фигуры.
     *
     * @throws ImpossibleMoveException
     */
    @Test
    public void whenElephantInValidMoveThenFalse() throws ImpossibleMoveException {
        Board board = new Board();
        board.init();
        Cell source = new Cell(3, 1);
        Cell dest = new Cell(6, 5);
        assertThat(board.move(source, dest), is(false));
    }

    /**
     * Тест возвращает false в случае если на пути движения фигуры имеется другая фигура.
     *
     * @throws OccupiedWayException
     */
    @Test
    public void whenElephantValidMoveButOnWayAnotherFigureThenFalse() throws OccupiedWayException {
        Board board = new Board();
        board.init();
        Cell source = new Cell(3, 1);
        Cell dest = new Cell(8, 6);
        assertThat(board.move(source, dest), is(false));
    }
}