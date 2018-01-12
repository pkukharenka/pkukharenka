package ru.job4j.test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

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
     * Возвращает true если модель успещно совершила движение. Метод описывает
     * движение моделей по горизонтали (влево либо вправо).
     *
     * @param model - модель, осуществляющая движение
     * @param x     - положение в горизонтальной плоскости
     * @param y     - положение в вертикальной плоскости
     * @return - true если модель успещно совершила движение
     */
    public boolean moveX(final Model model, final int x, final int y) {
        boolean isMove = false;
        try {
            final int xSize = this.board[0].length - 1;
            if (x >= 0 && x < xSize && this.board[x][y].tryLock(500, TimeUnit.MILLISECONDS)) {
                if (this.board[model.getX()][y].isLocked()) {
                    this.board[model.getX()][y].unlock();
                }
                model.setX(x);
                isMove = true;
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return isMove;
    }

    /**
     * Возвращает true если модель успещно совершила движение. Метод описывает
     * движение моделей по вертикали (вверх либо вниз).
     *
     * @param model - модель, осуществляющая движение
     * @param x     - положение в горизонтальной плоскости
     * @param y     - положение в вертикальной плоскости
     * @return - true если модель успещно совершила движение
     */
    public boolean moveY(final Model model, final int x, final int y) {
        boolean isMove = false;
        try {
            final int ySize = this.board.length - 1;
            if (y >= 0 && y < ySize && this.board[x][y].tryLock(500, TimeUnit.MILLISECONDS)) {
                if (this.board[x][model.getY()].isLocked()) {
                    this.board[x][model.getY()].unlock();
                }
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
    public boolean moveLeft(final Model model) {
        return this.moveX(model, model.getX() - 1, model.getY());
    }

    /**
     * Возвращает true если модель успещно совершила движение вправо
     * на одну клетку.
     *
     * @param model - модель.
     * @return - true если модель успещно совершила движение
     */
    public boolean moveRight(final Model model) {
        return this.moveX(model, model.getX() + 1, model.getY());
    }

    /**
     * Возвращает true если модель успещно совершила движение вверх
     * на одну клетку.
     *
     * @param model - модель.
     * @return - true если модель успещно совершила движение
     */
    public boolean moveUp(final Model model) {
        return this.moveY(model, model.getX(), model.getY() + 1);
    }

    /**
     * Возвращает true если модель успещно совершила движение вниз
     * на одну клетку.
     *
     * @param model - модель.
     * @return - true если модель успещно совершила движение
     */
    public boolean moveDown(final Model model) {
        return this.moveY(model, model.getX(), model.getY() - 1);
    }

    /**
     * Запуск программы.
     *
     * @param args - аргументы
     */
    public static void main(String[] args) {
        Bomber bomber = new Bomber(10);
        Model hero = new Model("Герой", 0, 0);
        Model enemy = new Model("Чудовище", 5, 5);
        Thread th1 = new ModelMoveThread(bomber, hero);
        Thread th2 = new ModelMoveThread(bomber, enemy);
        th1.start();
        th2.start();
        try {
            th1.join();
            th2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}

/**
 * Класс осуществляющий движение моделей
 */
class ModelMoveThread extends Thread {
    /**
     * Класс игровое поле.
     */
    private final Bomber bomber;
    /**
     * Класс модели.
     */
    private final Model model;

    /**
     * Конструктор для инициализации.
     *
     * @param bomber - игровое поле.
     * @param model - модель
     */
    public ModelMoveThread(Bomber bomber, Model model) {
        this.bomber = bomber;
        this.model = model;
    }

    /**
     * Метод реализующий автоматическое рандомное движение
     * моделей. Для обеспечения рандомности используется
     * список с цифрами от 1 до 4, каждая цифра характерезует
     * движение влево, вправо, вверх или вниз. Перед каждым
     * движением перемешиваем список для рандомности изъятия
     * значений. Далее если движение под номером из списка
     * возвращает true выходим из цикла и повторяем снова.
     */
    @Override
    public void run() {
        List<Integer> list = new ArrayList<>();
        for (int i = 1; i < 5; i++) {
            list.add(i);
        }
        while (true) {
            Collections.shuffle(list);
            for (Integer i : list) {
                if (i == 1 && this.bomber.moveLeft(this.model)) {
                    System.out.println(String.format("%s совершил движение влево в клетку %s - %s", this.model.getName(),
                            this.model.getX(), this.model.getY()));
                    break;
                } else if (i == 2 && this.bomber.moveRight(this.model)) {
                    System.out.println(String.format("%s совершил движение вправо в клетку %s - %s", this.model.getName(),
                            this.model.getX(), this.model.getY()));
                    break;
                } else if (i == 3 && this.bomber.moveUp(this.model)) {
                    System.out.println(String.format("%s совершил движение вверх в клетку %s - %s", this.model.getName(),
                            this.model.getX(), this.model.getY()));
                    break;
                } else if (i == 4 && this.bomber.moveDown(this.model)) {
                    System.out.println(String.format("%s совершил движение вниз в клетку %s - %s", this.model.getName(),
                            this.model.getX(), this.model.getY()));
                    break;
                } else {
                    break;
                }
            }
        }
    }
}