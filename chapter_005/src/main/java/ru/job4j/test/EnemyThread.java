package ru.job4j.test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
                if (i == 1 && this.bomber.moveLeft(this.model)) {
                    System.out.println(String.format("%s совершил движение влево в клетку %s - %s", this.model.getName(),
                            this.model.getX(), this.model.getY()));
                    break;
                } else if (i == 2 && this.bomber.moveRight(this.model)) {
                    System.out.println(String.format("%s совершил движение вправо в клетку %s - %s", this.model.getName(),
                            this.model.getX(), this.model.getY()));
                    break;
                } else if (i == 3 && this.bomber.moveUp(this.model)) {
                    System.out.println(String.format("%s совершил движение вверх в клетку %s - %s", this.model.getName(),
                            this.model.getX(), this.model.getY()));
                    break;
                } else if (i == 4 && this.bomber.moveDown(this.model)) {
                    System.out.println(String.format("%s совершил движение вниз в клетку %s - %s", this.model.getName(),
                            this.model.getX(), this.model.getY()));
                    break;
                } else {
                    System.out.println(String.format("Ходить некуда - %s", this.model.getName()));
                    break;
                }
            }
        }
    }

}
