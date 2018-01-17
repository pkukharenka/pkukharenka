package ru.job4j.nonblocking;

/**
 * Класс для запуска программы
 *
 * @author Pyotr Kukharenka
 * @since 17.01.2018
 */

public class Runner {
    public static void main(String[] args) {
        Cash cash = new Cash();
        cash.add(new User(1, "ivan"));

        Thread th1 = new Work(cash,"artem");
        Thread th2 = new Work(cash,"pyotr");
        th1.start();
        th2.start();
        try {
            th1.join();
            th2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class Work extends Thread {
    private final Cash cash;
    private final String name;

    public Work(Cash cash, String name) {
        this.cash = cash;
        this.name = name;
    }

    @Override
    public void run() {
        User user = this.cash.get(1);
        user.setName(name);
        this.cash.update(user);
    }
}
