package ru.job4j.thread;

/**
 * Программа для подсчета количества пробелов и
 * количества слов в строке.
 *
 * @author Pyotr Kukharenka
 * @since 03.01.2018
 */

public class Counter {
    /**
     * Текст для обработки.
     */
    private final String text;

    /**
     * Конструктор для инициализации текста.
     *
     * @param text - текст для обработки.
     */
    public Counter(String text) {
        this.text = text;
    }

    /**
     * Возвращает текст ввиде массива символов.
     *
     * @param text - текст для обработки.
     * @return - текст ввиде массива символов.
     */
    private char[] toChar(final String text) {
        return this.text.toCharArray();

    }

    /**
     * Возвращает поток, подсчитывающий количество пробелов в тексте.
     *
     * @return - поток, подсчитывающий количество пробелов в тексте.
     */
    private Thread calcSpace() {
        return new Thread(new Runnable() {
            @Override
            public void run() {
                int count = 0;
                for (char c : toChar(text)) {
                    if (c == 32) {
                        count++;
                    }
                }
                System.out.println(String.format("Количество пробелов: %s", count));
            }
        });
    }

    /**
     * Возвращает поток, подсчитывающий количество слов в тексте.
     *
     * @return - поток, подсчитывающий количество слов в тексте.
     */
    private Thread calcWord() {
        return new Thread(new Runnable() {
            @Override
            public void run() {
                char[] chars = toChar(text);
                int count = 0;
                for (int i = 1; i < chars.length; i++) {
                    if ((chars[i] < 65 || (chars[i] > 90 && chars[i] < 97) || chars[i] > 122)
                            && ((chars[i - 1] >= 65 && chars[i - 1] <= 90) || (chars[i - 1] >= 97 && chars[i - 1] <= 122))) {
                        count++;
                    }
                }
                System.out.println(String.format("Количество слов: %s", count));
            }
        });
    }

    /**
     * Запуск программы.
     *
     * @param args - входящие аргументы
     */
    public static void main(String[] args) {
        Counter counter = new Counter("Thank you for submitting your request.");
        System.out.println("Start");
        Thread tr1 = counter.calcSpace();
        Thread tr2 = counter.calcWord();
        tr1.start();
        tr2.start();
        try {
            tr1.join();
            tr2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Finish");
    }
}
