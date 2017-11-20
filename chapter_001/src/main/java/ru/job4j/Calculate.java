package ru.job4j;

/**
 * It's a program that prints an expression "Hello world." to the console.
 *@author Pyotr Kukharenka
 *@since 19.11.2017
 */

public class Calculate {

	/**
	 * Method that prints an expression like "Echo, echo, echo...."
	 * @param name - name to complete expression.
	 * @return string expression.
	 */
	public String echo(String name) {
		return "Echo, echo, echo : " + name;
	}

	/**
	 *It's a main method that prints an expression.
	 *@param args - array of strings.
	 */

	public static void main(String[] args) {

		System.out.println("Hello world.");
	}
}

