package ru.job4j.test;

import java.util.*;

/**
 * Программа для поиска кратчайшего пути лягушки до точки назначения
 * с учетом наличия препятствий ввиде деревьев, в которые лягушка
 * не может прыгнуть.
 *
 * @author Pyotr Kukharenka
 * @since 02.01.2018
 */

public class Frog {
    /**
     * Массив, представляющий собой поле в котором
     * лягушка осуществляет передвижение.
     */
    private int[][] map;

    /**
     * Инициализация поля размером 10х16.
     */
    public Frog() {
        this.map = new int[10][16];
    }

    /**
     * Возвращает коллекцию с возможными прыжками лягушки. Лягушка осуществляет
     * прыжки только по часовой стрелке в 5 направлениях.
     *
     * @return - коллекцию с возможными прыжками лягушки.
     */
    private List<Point> moves() {
        List<Point> moves = new ArrayList<>();
        moves.add(new Point(2, 1));
        moves.add(new Point(1, 2));
        moves.add(new Point(0, 3));
        moves.add(new Point(-1, 2));
        moves.add(new Point(-2, 1));
        return moves;
    }

    /**
     * Возвращает точку назначения.
     * Пройденный путь является кратчайшим из возможных. Поиск кратчайшего
     * пути реализован за счет поиска в ширину (BFS). В основе лежит
     * очередь, из которой в порядке добавления извлекаются возможные
     * точки куда лягушка может прыгнуть.
     * Если очередная точка является точкой назначения, выходим из цикла.
     * Если крайняя извлеченная точка не является конечной, значит
     * точка назначения недостижима.
     *
     * @param start  - точка начала пути.
     * @param finish - точка назначения
     * @param trees  - список точек, где находятся деревья.
     * @return - возвращает точку назначения.
     */
    public Point jump(Point start, Point finish, List<Point> trees) {
        Deque<Point> queue = new LinkedList<>();
        queue.offer(start);
        Point p = null;
        while (!queue.isEmpty()) {
            p = queue.poll();
            if (p.equals(finish)) {
                break;
            }
            for (Point move : this.moves()) {
                Point newPoint = null;

                //Т.к. поля по условию задачи является кргуом (т.е. оно замкнуто по длинне),
                //а массив является ограниченным, обеспечиваем, чтобы мы не вышли за пределы
                //массива по длинне, а как бы перескочили в его начало.
                if (p.getY() + move.getY() > this.map[0].length) {
                    newPoint = new Point(p.getX() + move.getX(), p.getY() + move.getY() - this.map[0].length, p);
                } else {
                    newPoint = new Point(p.getX() + move.getX(), p.getY() + move.getY(), p);
                }

                //Проверяем, что точка в которую лягушка может прыгнуть не выходит
                //за пределы массива, что данная точка не содержит дерево и что
                //в данной точке мы еще не были (т.е. значение ячейки массива = 0).
                if (newPoint.getX() <= 10 && newPoint.getX() >= 1 && !trees.contains(newPoint) && this.map[newPoint.getX() - 1][newPoint.getY() - 1] == 0) {
                    this.map[newPoint.getX() - 1][newPoint.getY() - 1] += 1;
                    queue.offer(newPoint);
                }
            }
        }
        return p;
    }

    /**
     * Возвращает список пройденных точек до точки назначения.
     * Проходим циклом по указателям на предыдущую точку,
     * тем самым возращаем путь пройденный до конечной точки.
     * Т.к. элементы в список добавляются в обратном
     * порядке делаем reverse с помощью класса Collections.
     *
     * @param start  - точка начала пути.
     * @param finish - точка назначения
     * @param trees  - список точек, где находятся деревья.
     * @return - возвращает список пройденных точек до точки назначения.
     */
    public List<Point> findWay(Point start, Point finish, List<Point> trees) {
        Point target = this.jump(start, finish, trees);
        List<Point> steps = new ArrayList<>();
        if (!finish.equals(target)) {
            System.out.println("Я не могу добраться в точку назначения.");
        } else {
            while (target != start) {
                steps.add(target);
                target = target.getPrev();
            }
            Collections.reverse(steps);
        }
        return steps;
    }

    /**
     * Возвращает количество пройденный точек до точки назначения.
     *
     * @param steps - список пройденных точек.
     * @return - возвращает количество пройденный точек до точки назначения.
     */
    public int stepsCount(List<Point> steps) {
        return steps.size() - 1;
    }

    /**
     * Работы программы.
     *
     * @param args - аргументы.
     */
    public static void main(String[] args) {
        Frog frog = new Frog();
        Point start = new Point(7, 11);
        Point finish = new Point(10, 9);
        List<Point> trees = new ArrayList<>();
        trees.add(new Point(9, 14));
        trees.add(new Point(8, 5));
        List<Point> steps = frog.findWay(start, finish, trees);
        System.out.printf("Минимальное количество шагов до конечной точки составляет: %s\n", frog.stepsCount(steps));
        for (Point step : steps) {
            System.out.println(step);
        }
    }
}

/**
 * Класс, описывающий точку, пройденную лягушкой до точки
 * назначения.
 */
class Point {
    /**
     * Положение точки по высоте.
     */
    private int x;
    /**
     * Положение точки по длине.
     */
    private int y;
    /**
     * Указатель на точку с которой перешли
     * в данную точку.
     */
    private Point prev;

    /**
     * Конструктор со всеми поялми для инициализации точки
     * при поиске кратчайшего пути.
     *
     * @param x    - положение точки по высоте.
     * @param y    - положение точки по длине.
     * @param prev - указатель на точку с которой перешли
     *             в данную точку.
     */
    public Point(int x, int y, Point prev) {
        this.x = x;
        this.y = y;
        this.prev = prev;
    }

    /**
     * Конструктор для инициализации точки
     * при тестировании и запуске программы.
     *
     * @param x - положение точки по высоте.
     * @param y - положение точки по длине.
     */
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    //Геттеры.
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Point getPrev() {
        return prev;
    }
//Equals, hashCode, toString
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Point point = (Point) o;

        if (x != point.x) {
            return false;
        }
        return y == point.y;
    }

    @Override
    public int hashCode() {
        int result = x;
        result = 31 * result + y;
        return result;
    }

    @Override
    public String toString() {
        return "Point{"
                + "x=" + x
                + ", y=" + y
                + '}';
    }
}