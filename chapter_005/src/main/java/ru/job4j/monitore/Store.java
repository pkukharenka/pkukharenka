package ru.job4j.monitore;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.Random;

/**
 * Релизация BoundedBlockingQueue и шаблона
 * Producer-Consumer
 *
 * @author Pyotr Kukharenka
 * @since 07.01.2018
 */
@ThreadSafe
public class Store {
    /**
     * Структура для хранения элементов.
     */
    @GuardedBy("this")
    private int[] data;
    /**
     * Количество элементов в очереди.
     */
    @GuardedBy("this")
    private int size;

    /**
     * Конструктор для инициализации массива на 5 элементов.
     */
    public Store() {
        this.data = new int[5];
        this.size = 0;
    }

    /**
     * Метод добавления элемента в очередь.
     *
     * @param value - значение для добавления
     */
    public synchronized void offer(int value) {
        this.data[size++] = value;
    }

    /**
     * Возвращает первый элемент очереди и удаляет его.
     *
     * @return - первый элемент очереди
     */
    public synchronized int poll() {
        int value = this.data[0];
        System.arraycopy(this.data, 1, this.data, 0, this.size - 1);
        this.data[size - 1] = 0;
        this.size--;
        return value;
    }

    /**
     * Возвращает количество элементов очереди.
     *
     * @return - количество элементов очереди.
     */
    public synchronized int getSize() {
        return size;
    }

    /**
     * Возвращает размер массива.
     *
     * @return - размер массива.
     */
    public synchronized int getDataLenght() {
        return this.data.length;
    }

    /**
     * Запуск программы.
     *
     * @param args - аргументы программы.
     */
    public static void main(String[] args) {
        Store store = new Store();
        Thread p = new Producer(store);
        Thread c1 = new Consumer(store);
        p.start();
        c1.start();
    }
}

/**
 * Поток продюссер. Добавляет элементы в очередь.
 */
class Producer extends Thread {
    /**
     * Класс хранилище.
     */
    private final Store store;
    /**
     * Генератор радомных чисел.
     */
    private final Random rn = new Random();

    /**
     * Конструктор.
     *
     * @param store - класс хранилище.
     */
    public Producer(Store store) {
        this.store = store;
    }

    /**
     * Логика работы потока. Пока очередь заполнена, поток ждет
     * освобождения местла для добавления нового элементы.
     * Если в очереди есть свободное место, добавляет элементы и
     * будит поток Consumer
     */
    @Override
    public void run() {
        while (true) {
            synchronized (this.store) {
                while (store.getSize() == store.getDataLenght()) {
                    System.out.println("Ждем освобождения очереди");
                    try {
                        this.store.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                int value = rn.nextInt(1000);
                this.store.offer(value);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(String.format("Добавлена задача № %s. Всего задач - %s",
                        value, store.getSize()));
                this.store.notify();
            }
        }

    }
}

/**
 * Поток Consumer, извлекает элементы из очереди.
 */
class Consumer extends Thread {
    /**
     * Класс хранилище
     */
    private final Store store;

    /**
     * Конструктор.
     *
     * @param store - класс хранилище.
     */
    public Consumer(Store store) {
        this.store = store;
    }

    /**
     * Пока очередь пуста ожидает добавления туда элемента. Если
     * в оереди есть элементы извлекает их и будит поток Producer
     * чтобы он добавил новый элемент на свободное место.
     */
    @Override
    public void run() {
        while (true) {
            synchronized (this.store) {
                while (this.store.getSize() == 0) {
                    System.out.println("Очередь пуста, ждем элементов");
                    try {
                        this.store.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println(String.format("Задача № %s отработана. Осталось задач: %s",
                        this.store.poll(), this.store.getSize()));
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                this.store.notify();
            }
        }
    }
}