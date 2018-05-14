package ru.job4j.nonblocking;

/**
 * Класс для запуска программы
 *
 * @author Pyotr Kukharenka
 * @since 17.01.2018
 */

public class Runner {
    public static void main(String[] args) {
        UserContainer userContainer = new UserContainer();
        userContainer.add(new User(1, "ivan"));

        Thread th1 = new Work(userContainer, "artem");
        Thread th2 = new Work(userContainer, "pyotr");
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
    private final UserContainer userContainer;
    private final String name;

    public Work(UserContainer userContainer, String name) {
        this.userContainer = userContainer;
        this.name = name;
    }

    @Override
    public void run() {
        User user = this.userContainer.get(1);
        user.setName(name);
        this.userContainer.update(user);
    }
}
