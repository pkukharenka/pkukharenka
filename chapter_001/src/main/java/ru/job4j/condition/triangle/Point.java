package ru.job4j.condition.triangle;

/**
 * Class that describes the object point.
 *
 * @author Pyotr Kukharenka
 * @since 21.11.2017
 */

public class Point {
    private int x;
    private int y;

    /**
     * Constructor.
     *
     * @param x - coordinate x.
     * @param y - coordinate y.
     */

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Calculate distance from one point to second.
     *
     * @param that - second Point.
     * @return distance.
     */
    public double distanceTo(Point that) {
        return Math.sqrt(Math.pow(that.x - this.x, 2) + Math.pow(that.y - this.y, 2));
    }
}
