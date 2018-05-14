package ru.job4j.elevator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ConcurrentLinkedDeque;

/**
 * Имитация работы лифта
 *
 * @author Pyotr Kukharenka
 * @since 31.01.2018
 */

public class Elevator {
    /**
     * Лоогер
     */
    private static final Logger LOG = LoggerFactory.getLogger(Elevator.class);
    /**
     * Число этажей в подъезде
     */
    private final int stageCount;
    /**
     * Текущее положение лифта.
     */
    private volatile int currentStage;
    /**
     * Высота этажа
     */
    private final int height;
    /**
     * Скорость движения лифта
     */
    private final int speed;
    /**
     * Время между открытие и закрытием дверей лифта.
     */
    private final int time;
    /**
     * Очередь для даижения лифта по этажамю
     */
    private final ConcurrentLinkedDeque<Integer> queue = new ConcurrentLinkedDeque<>();

    /**
     * Конструктор
     *
     * @param stageCount   - Число этажей в подъезде
     * @param currentStage - Текущее положение лифта.
     * @param height       - Высота этажа
     * @param speed        - Скорость движения лифта
     * @param time         - Время между открытие и закрытием дверей лифта.
     */
    public Elevator(int stageCount, int currentStage, int height, int speed, int time) {
        this.stageCount = stageCount;
        this.currentStage = currentStage;
        this.height = height;
        this.speed = speed;
        this.time = time;
    }

    /**
     * Метод открытия и закрытия дверей лифта с учетом времени
     *
     * @throws InterruptedException - выбрасывается в случае ошибки в работе
     *                              метода sleep.
     */
    public void door() throws InterruptedException {
        LOG.info("Лифт открыл двери. Ожидаю нажатия кнопки нужного эатажа.");
        Thread.sleep(this.time);
        LOG.info("Лифт закрыл двери");

    }

    /**
     * Метод имитации двмжения лифта.
     * 1. Если лифт едет вверх используем возрастающий цикл
     * 2. Если лифт едет вниз, используем убывающий цикл
     * Время за которое лифт проезжает один этаж равно высоте/скорость
     *
     * @param current - текущее положение лифта
     * @param target  - этаж, на который необходимо приехать
     * @throws InterruptedException - выбрасывается в случае ошибки в работе
     *                              метода sleep.
     */
    public void move(int current, int target) throws InterruptedException {
        if (current <= target) {
            for (int i = current; i < target; i++) {
                Thread.sleep(this.height * 1000 / this.speed);
                LOG.info((String.format("Лифт проехал %s этаж", i)));
            }
        } else {
            for (int i = current; i > target; i--) {
                Thread.sleep(this.height * 1000 / this.speed);
                LOG.info((String.format("Лифт проехал %s этаж", i)));
            }
        }
        this.currentStage = target;
        LOG.info((String.format("Лифт приехал на вызванный этаж - %s", target)));
    }

    /**
     * Запись очередного этажа в очередь
     *
     * @param number - номер этажа
     */
    public void push(int number) {
        this.queue.push(number);
    }

    /**
     * Извлечение из очереди очередного этажа
     *
     * @return - номер этажа
     */
    public int poll() {
        return this.queue.poll();
    }

    /**
     * Возвращает true если в очереди есть этажи для
     * движения лифта
     *
     * @return - true если очередь не пуста
     */
    public boolean hasTask() {
        return !this.queue.isEmpty();
    }

    //Геттеры
    public int getCurrentStage() {
        return currentStage;
    }

    public int getStageCount() {
        return stageCount;
    }

    /**
     * Запуск программы
     *
     * @param args - аргументы
     */
    public static void main(String[] args) {
        Elevator elevator = new Elevator(20, 3, 3, 3, 5000);
        new MoverThread(elevator).start();
        new HandlerThread(elevator).start();
    }
}