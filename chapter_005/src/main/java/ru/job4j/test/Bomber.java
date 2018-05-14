package ru.job4j.test;

import ru.job4j.test.model.Dest;
import ru.job4j.test.model.Model;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.Function;

/**
 * Игра бомбермэн.
 *
 * @author Pyotr Kukharenka
 * @since 12.01.2018
 */

public class Bomber {
    /**
     * Имитация поля для движения моделей.
     */
    private final ReentrantLock[][] board;

    /**
     * Инициализация игрового поля размерлм size*size.
     *
     * @param size - размер поля.
     */
    public Bomber(final int size) {
        this.board = new ReentrantLock[size][size];
        for (ReentrantLock[] reentrantLocks : board) {
            for (int i = 0; i < size; i++) {
                reentrantLocks[i] = new ReentrantLock();
            }
        }
    }

    /**
     * Метод для инициализации игры. Создает поток героя и потоки
     * чудовищ, согласно введенного количества. Также расставляет
     * в рандомном порядке блоки по полю игры.
     *
     * @param bomber - поле для игры.
     * @param enemy  - количество чудовищ.
     * @param blocks - количество блоков.
     */
    public void initGame(Bomber bomber, int enemy, int blocks) {
        Random rn = new Random();
        new HeroThread(bomber, new Model("hero", 0, 0)).start();
        for (int i = 0; i < blocks; i++) {
            int x = Math.abs(rn.nextInt(this.board.length - 1));
            int y = Math.abs(rn.nextInt(this.board.length - 1));
            if (!this.board[x][y].isLocked()) {
                this.board[x][y].lock();
            }
        }
        do {
            int x = Math.abs(rn.nextInt(this.board.length - 1));
            int y = Math.abs(rn.nextInt(this.board.length - 1));
            if (!this.board[x][y].isLocked()) {
                new EnemyThread(bomber, new Model("Enemy" + enemy, x, y)).start();
                enemy--;
            }
        } while (enemy != 0);
    }

    /**
     * Возвращает true если модель успещно совершила движение. Метод описывает
     * движение моделей по горизонтали (влево либо вправо).
     *
     * @param model - модель, осуществляющая движение
     * @param dest  - точка куда модель совершает движение
     * @return - true если модель успещно совершила движение
     */
    private boolean move(final Model model, final Dest dest) {
        boolean isMove = false;
        try {
            final int xSize = this.board[0].length - 1;
            final int ySize = this.board.length - 1;
            final int x = dest.getX();
            final int y = dest.getY();
            if (x >= 0 && x < xSize && y >= 0 && y < ySize && this.board[x][y].tryLock(5000, TimeUnit.MILLISECONDS)) {
                if (this.board[model.getX()][model.getY()].isLocked()) {
                    this.board[model.getX()][model.getY()].unlock();
                }
                model.setX(x);
                model.setY(y);
                isMove = true;
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return isMove;
    }

    /**
     * Возвращает true если модель успещно совершила движение влево
     * на одну клетку.
     *
     * @param model - модель.
     * @return - true если модель успещно совершила движение
     */
    public Function<Integer, Boolean> moveLeft(final Model model) {
        return number -> {
            final boolean res = this.move(model, new Dest(model.getX() - 1, model.getY()));
            if (res) {
                this.msg("влево", model);
            }
            return res;
        };
    }

    /**
     * Возвращает true если модель успещно совершила движение вправо
     * на одну клетку.
     *
     * @param model - модель.
     * @return - true если модель успещно совершила движение
     */
    public Function<Integer, Boolean> moveRight(final Model model) {
        return number -> {
            final boolean res = this.move(model, new Dest(model.getX() + 1, model.getY()));
            if (res) {
                this.msg("вправо", model);
            }
            return res;
        };
    }

    /**
     * Возвращает true если модель успещно совершила движение вверх
     * на одну клетку.
     *
     * @param model - модель.
     * @return - true если модель успещно совершила движение
     */
    public Function<Integer, Boolean> moveUp(final Model model) {
        return number -> {
            final boolean res = this.move(model, new Dest(model.getX(), model.getY() + 1));
            if (res) {
                this.msg("вверх", model);
            }
            return res;
        };
    }

    /**
     * Возвращает true если модель успещно совершила движение вниз
     * на одну клетку.
     *
     * @param model - модель.
     * @return - true если модель успещно совершила движение
     */
    public Function<Integer, Boolean> moveDown(final Model model) {
        return number -> {
            final boolean res = this.move(model, new Dest(model.getX(), model.getY() - 1));
            if (res) {
                this.msg("вниз", model);
            }
            return res;
        };
    }

    private void msg(final String side, final Model model) {
        System.out.println(String.format("%s совершил движение %s в клетку %s - %s", model.getName(), side,
                model.getX(), model.getY()));
    }

    /**
     * Запуск программы.
     *
     * @param args - аргументы
     */
    public static void main(String[] args) {
        Bomber bomber = new Bomber(10);
        bomber.initGame(bomber, 2, 5);
    }
}
