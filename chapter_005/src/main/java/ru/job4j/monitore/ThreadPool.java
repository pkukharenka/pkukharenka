package ru.job4j.monitore;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Реализация ThreadPool.
 *
 * @author Pyotr Kukharenka
 * @since 07.01.2018
 */
@ThreadSafe
public class ThreadPool {
    /**
     * Очередь для задач, ожидающих выполнения.
     */
    @GuardedBy("queue")
    private final Queue<Runnable> queue;

    /**
     * Конструктор.
     */
    public ThreadPool() {
        this.queue = new LinkedList<>();

    }

    /**
     * Добавляет новую задачу в очередь и будит потоки.
     *
     * @param work - задача для выполнения.
     */
    public void add(Work work) {
        synchronized (this.queue) {
            this.queue.offer(work);
            this.queue.notifyAll();
        }
    }

    /**
     * Инициализации потоков.
     *
     * @param threadNumb - количество потоков.
     */
    public void start(int threadNumb) {
        for (int count = 0; count < threadNumb; count++) {
            new Thread(new Worker()).start();
        }
    }

    /**
     * Класс описывает работу потока из пула потоков. Пока
     * очередь с задачами пуста, потоки ожидают добаления
     * новой задачи. В противном случае извлекает задачу
     * из очереди и выполняют ее.
     */
    class Worker implements Runnable {

        @Override
        public void run() {
            while (true) {
                synchronized (queue) {
                    while (queue.isEmpty()) {
                        try {
                            queue.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                    queue.poll().run();
                    System.out.println(String.format("Поток № %s взял в работу задачу. Осталось - %s",
                            Thread.currentThread().getName(), queue.size()));
                }
            }
        }
    }

    /**
     * Запуск программы, создаем задачи и заполняем очередь.
     *
     * @param args - параметры запуска.
     */
    public static void main(String[] args) {
        ThreadPool pool = new ThreadPool();
        pool.start(4);
        for (int i = 0; i < 200000; i++) {
            pool.add(new Work());
            System.out.println("Добавлена новая работа № " + i);
        }

    }
}

/**
 * Простой класс, демонстрирующий выполнение работы.
 */
class Work implements Runnable {

    @Override
    public void run() {
        System.out.println("Отработано");
    }
}
