package ru.job4j.nonblocking;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class CashTest {

    public class Work extends Thread {
        private final Cash cash;
        private final User user;

        public Work(Cash cash, User user) {
            this.cash = cash;
            this.user = user;
        }

        @Override
        public void run() {
            this.cash.update(this.user);
        }
    }

    @Test
    public void whenThen() {
        Cash cash = new Cash();
        cash.add(new User(1, "ivan"));
        cash.add(new User(2, "petr"));

        User updated1 = new User(1, "anna");
        User updated2 = new User(1, "denis");

        Thread th1 = new Work(cash, updated1);
        Thread th2 = new Work(cash, updated2);
        th1.start();
        th2.start();
        try {
            th1.join();
            th2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        assertThat(cash.get(1).getName(), is("denis"));
    }
}