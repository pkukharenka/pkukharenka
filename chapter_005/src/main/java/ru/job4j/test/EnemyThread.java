package ru.job4j.test;

import ru.job4j.test.model.Model;

import java.util.*;
import java.util.function.Function;

/**
 * Класс осуществляющий автоматическое движение
 * чудовищ
 *
 * @author Pyotr Kukharenka
 * @since 23.01.2018
 */

public class EnemyThread extends Thread {
    /**
     * Класс игровое поле.
     */
    private final Bomber bomber;
    /**
     * Класс модели.
     */
    private final Model model;

    private final Map<Integer, Function<Integer, Boolean>> dispatch = new HashMap<>();

    /**
     * Конструктор для инициализации.
     *
     * @param bomber - игровое поле.
     * @param model  - модель
     */
    public EnemyThread(Bomber bomber, Model model) {
        this.bomber = bomber;
        this.model = model;
    }

    private EnemyThread init() {
        this.dispatch.put(1, this.bomber.moveLeft(this.model));
        this.dispatch.put(2, this.bomber.moveRight(this.model));
        this.dispatch.put(3, this.bomber.moveUp(this.model));
        this.dispatch.put(4, this.bomber.moveDown(this.model));
        return this;
    }

    private boolean go(final int number) {
        return this.dispatch.get(number).apply(number);
    }

    /**
     * Метод реализующий автоматическое рандомное движение
     * моделей. Для обеспечения рандомности используется
     * список с цифрами от 1 до 4, каждая цифра характерезует
     * движение влево, вправо, вверх или вниз. Перед каждым
     * движением перемешиваем список для рандомности изъятия
     * значений. Далее если движение под номером из списка
     * возвращает true выходим из цикла и повторяем снова.
     */
    @Override
    public void run() {
        List<Integer> list = new ArrayList<>();
        for (int i = 1; i < 5; i++) {
            list.add(i);
        }
        while (true) {
            Collections.shuffle(list);
            for (Integer i : list) {
                if (this.init().go(i)) {
                    break;
                }
                System.out.println(String.format("Ходить некуда - %s", this.model.getName()));
            }
        }
    }
}


