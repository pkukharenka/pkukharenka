package ru.job4j.thread;

import java.util.concurrent.TimeUnit;

/**
 * Программа, демонстрирующая возможности прерывания
 * потоков.
 *
 * @author Pyotr Kukharenka
 * @since 03.01.2018
 */

public class Interrupt {
    /**
     * Временной ограничитель работы потока подсчета символов.
     */
    private int time;
    /**
     * Текст для подсчет символов.
     */
    private String text;
    /**
     * Счетчик для подсчета символов.
     */
    private int count;

    /**
     * Конструктор, инициалищирующий поля класса.
     *
     * @param time - временной ограничитель работы потока подсчета символов.
     * @param text - текст для подсчет символов.
     */
    public Interrupt(int time, String text) {
        this.time = time;
        this.text = text;
        this.count = 0;
    }

    /**
     * Класс таймер. Поток таймер запускает поток подсчета символов
     * По окончанию времени, поток подсчет прерывается путем установки
     * флага interrupt.
     */
    public class Time implements Runnable {

        @Override
        public void run() {
            Thread count = new Thread(new CountChar());
            count.start();
            try {
                TimeUnit.MILLISECONDS.sleep(time);
                count.interrupt();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Класс счетчик символов. В случае, если флаг прерывания поднят
     * выходим из цикла.
     */
    public class CountChar implements Runnable {

        @Override
        public void run() {
            char[] chars = text.toCharArray();
            for (char c : chars) {
                if (!Thread.interrupted()) {
                    count++;
                } else {
                    break;
                }
            }
            if (count < chars.length) {
                System.out.println(String.format("Поток прерван, посчитано символов %s из %s", count, chars.length));
            } else {
                System.out.println(String.format("Поток отработал полностью, количество символов в строке: %s", count));
            }
        }
    }
}

/**
 * Запуск программы.
 */
class Runner {
    public static void main(String[] args) {

        //Создаем строку в 600000 символов.
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 600000; i++) {
            sb.append("i");
        }

        Interrupt inter = new Interrupt(12, sb.toString());
        Thread time = new Thread(inter.new Time());
        time.start();
    }
}
