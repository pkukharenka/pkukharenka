package ru.job4j.calculator;

/**
 * Simple calculator (add, subtract, multiple, div)/
 *
 * @author Pyotr Kukharenka
 * @since 20.11.2017
 */

public class Calculator {
    private double result;

    /**
     * Method that adds two double arguments.
     *
     * @param first  - first argument.
     * @param second - second argument.
     */

    public void add(double first, double second) {
        this.result = first + second;
    }

    /**
     * Method that subtract two double arguments.
     *
     * @param first  - first argument.
     * @param second - second argument.
     */

    public void subtract(double first, double second) {
        this.result = first - second;
    }

    /**
     * Method that multiple two double arguments.
     *
     * @param first  - first argument.
     * @param second - second argument.
     */
    public void multiple(double first, double second) {
        this.result = first * second;
    }

    /**
     * Method that divs two double arguments.
     *
     * @param first  - first argument.
     * @param second - second argument.
     */
    public void div(double first, double second) {
        if (second == 0) {
            throw new ArithmeticException("You try div by zero!");
        } else {
            this.result = first / second;
        }
    }

    /**
     * Method that show result.
     *
     * @return result.
     */
    public double getResult() {
        return this.result;
    }
}
