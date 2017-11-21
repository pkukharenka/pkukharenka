package ru.job4j.condition;

/**
 * Calculating triangle area.
 *
 * @author Pyotr Kukharenka
 * @since 21.11.2017
 */

public class Triangle {
    private Point a;
    private Point b;
    private Point c;

    /**
     * Constructor.
     *
     * @param a - first point.
     * @param b - second point.
     * @param c - third point.
     */
    public Triangle(Point a, Point b, Point c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    /**
     * Distance from left point to right.
     *
     * @param left  - left point.
     * @param right - right point.
     * @return distance from left to right.
     */
    public double distance(final Point left, final Point right) {
        return left.distanceTo(right);
    }

    /**
     * Perimetr of a triangle.
     *
     * @param ab - distance from a to b.
     * @param ac - distance from a to c.
     * @param bc - distance from b to c.
     * @return perimetr
     */
    public double perimetr(final double ab, final double ac, final double bc) {
        return (ab + ac + bc) / 2;
    }

    /**
     * Area of a triangle.
     *
     * @return area if triangle exists, or -1.
     */
    public double area() {
        double result = -1;
        final double ab = this.distance(this.a, this.b);
        final double ac = this.distance(this.a, this.c);
        final double bc = this.distance(this.b, this.c);
        final double p = this.perimetr(ab, ac, bc);
        if (this.exists(ab, ac, bc)) {
            result = Math.sqrt(p * (p - ab) * (p - ac) * (p - bc));
        }
        return result;
    }

    /**
     * Verification of the existence of a triangle.
     * If one of the parties is less than the sum of the other two, then
     * triangle exists.
     *
     * @param ab - distance from a to b.
     * @param ac - distance from a to c.
     * @param bc - distance from b to c.
     * @return - true if triangle exists.
     */

    private boolean exists(final double ab, final double ac, final double bc) {
        boolean res = true;
        if (ab >= (ac + bc) || ac >= (ab + bc) || bc >= (ab + ac)) {
            res = false;
        }
        return res;
    }
}
