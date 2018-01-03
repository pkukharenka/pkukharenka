package ru.job4j.thread;

/**
 * Программа для подсчета количества пробелов и
 * количества слов в строке.
 *
 * @author Pyotr Kukharenka
 * @since 03.01.2018
 */

public class Counter {

    public static void main(String[] args) {
        String value = "Thank you for submitting your request.";
        char[] chars = value.toCharArray();

        //Поток для подсчета количества пробелов в строке.
        new Thread() {
            @Override
            public void run() {
                int count = 0;
                for (char c : chars) {
                    if (c == 32) {
                        count++;
                    }
                }
                System.out.println(String.format("Количество пробелов: %s", count));
            }
        }.start();

        //Поток для подсчета количества слов в строке.
        new Thread() {
            @Override
            public void run() {
                int count = 0;
                for (int i = 1; i < chars.length; i++) {
                    if ((chars[i] < 65 || (chars[i] > 90 && chars[i] < 97) || chars[i] > 122) &&
                            ((chars[i - 1] >= 65 && chars[i - 1] <= 90) || (chars[i - 1] >= 97 && chars[i - 1] <= 122))) {
                        count++;
                    }
                }
                System.out.println(String.format("Количество слов: %s", count));
            }
        }.start();

    }
}
